package org.local.abhi.main.ingestion.model;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class IngestionResponse {
    @Nullable
    private String fileMetadata;
    @Builder.Default
    private long fileSize = 0;
    @Nonnull
    private String message;
}
