package org.local.abhi.main.ingestion.service;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.local.abhi.main.ingestion.model.IngestionResponse;
import org.local.abhi.main.ingestion.utils.DataIngestionComponent;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class DataIngestionService {

    private final DataIngestionComponent ingestionComponent;

    public ResponseEntity<IngestionResponse> processPdfFile(
            @Nonnull MultipartFile file,
            String userName
    ) {
        String fileName = file.getOriginalFilename() != null ? file.getOriginalFilename() : "";

        if (file.isEmpty() || fileName.isBlank() || !fileName.toLowerCase().endsWith(".pdf")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(
                            IngestionResponse.builder()
                                    .message("Invalid submission. Please provide a clean, populated PDF file.")
                                    .build()
                    );
        }

        return ingestionComponent.ingestPdfFile(file, userName);
    }
}
