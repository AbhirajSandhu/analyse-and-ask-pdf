package org.local.abhi.main.commons.utils;

import io.micrometer.context.Nullable;
import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;
import org.local.abhi.main.ingestion.model.IngestionResponse;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.local.abhi.main.commons.constants.Constants.*;

@Slf4j
public final class GlobalUtils {

    @Nonnull
    public static List<Document> getDocuments(
            @Nonnull MultipartFile file,
            String userName,
            int chunkSize,
            int minChunkSizeChars,
            int minChunkLenToBeEmbedded,
            int minNumChunks,
            boolean keepSeparator
    ) throws IOException {
        final var fileName = file.getOriginalFilename();
        log.info("{} - Document Tokenization in process for file - {}",
                GLOBAL_UTILS, fileName);

        InputStreamResource resource = new InputStreamResource(file.getInputStream());
        TikaDocumentReader reader = new TikaDocumentReader(resource);
        List<Document> rawDocuments = reader.get();

        TokenTextSplitter splitter = new TokenTextSplitter(
                chunkSize, minChunkSizeChars, minChunkLenToBeEmbedded, minNumChunks, keepSeparator
        );
        List<Document> splitDocuments = splitter.apply(rawDocuments);

        for (Document doc : splitDocuments) {
            Map<String, Object> metadata = doc.getMetadata();
            metadata.put(SOURCE_FILE_NAME, fileName);
            metadata.put(INGESTED_BY, userName);
        }
        log.info("{} - Document Tokenization completed for file - {}",
                GLOBAL_UTILS, fileName);
        return splitDocuments;
    }

    public static ResponseEntity<IngestionResponse> ingestionResponseFormulation(
            @Nonnull HttpStatus httpStatus,
            @Nonnull String fileName,
            long fileSize,
            @Nonnull String message
    ) {
        return ResponseEntity.status(httpStatus).body(
                IngestionResponse.builder()
                        .fileMetadata(fileName)
                        .fileSize(fileSize)
                        .message(message)
                        .build()
        );
    }
}
