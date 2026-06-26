package org.local.abhi.ingestion.model;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.Builder;

@Builder
public class IngestionResponse {
    @Nullable
    private String fileMetadata;
    @Nullable
    private String fileSize;
    @Nonnull
    private String message;
}
