package org.local.abhi.main.ingestion.utils;

import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.local.abhi.main.commons.utils.GlobalUtils;
import org.local.abhi.main.ingestion.model.IngestionResponse;
import org.springframework.ai.document.Document;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.core.io.InputStreamResource;
import org.springframework.ai.reader.tika.TikaDocumentReader;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.local.abhi.main.commons.constants.Constants.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataIngestionComponent {

    @Value("${document-tokenization.chunk-size}")
    int chunkSize;
    @Value("${document-tokenization.min-chunk-size-chars}")
    int minChunkSizeChars;
    @Value("${document-tokenization.min-chunk-length-to-be-embedded}")
    int minChunkLenToBeEmbedded;
    @Value("${document-tokenization.min-num-chunks}")
    int minNumChunks;
    @Value("${document-tokenization.keep-separator}")
    boolean keepSeparator;

    private final VectorStore vectorStore;

    public ResponseEntity<IngestionResponse> ingestPdfFile (
            @Nonnull MultipartFile file,
            String userName
    ) {
        final var fileName = file.getOriginalFilename();
        final var fileSize = file.getSize();
        log.info("{}: Starting processing for document: {}",
                getClass().getSimpleName(), fileName);

        try {
            List<Document> splitDocuments = GlobalUtils.getDocuments(
                    file, userName, chunkSize, minChunkSizeChars, minChunkLenToBeEmbedded, minNumChunks, keepSeparator
            );

            log.info("Generated {} text chunks. Syncing mathematical vector arrays to DB...", splitDocuments.size());

            this.vectorStore.accept(splitDocuments);

            log.info("Document successfully indexed inside the vector store.");
            return GlobalUtils.ingestionResponseFormulation(
                    HttpStatus.CREATED, fileName, fileSize, SUCCESSFUL_INGESTION.formatted(fileName)
            );
        } catch (IOException e) {
            log.error("{}: Error while pre processing file with error - {}", getClass().getSimpleName(), e.getMessage());
            return GlobalUtils.ingestionResponseFormulation(
                    HttpStatus.INTERNAL_SERVER_ERROR, fileName, fileSize, FAILURE_PRE_PROCESSING_PDF.formatted(fileName)
            );
        } catch (Exception e) {
            log.error("{}: Error while ingesting file with error - {}", getClass().getSimpleName(), e.getMessage());
            return GlobalUtils.ingestionResponseFormulation(
                    HttpStatus.INTERNAL_SERVER_ERROR, fileName, fileSize, FAILURE_INGESTING_PDF.formatted(fileName)
            );
        }
    }

}
