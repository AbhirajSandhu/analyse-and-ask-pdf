package org.local.abhi.main.commons.constants;

public final class Constants {

    public static final String REQUEST_PATH = "/analyse-and-ask/v1";
    public static final String GLOBAL_UTILS = "GlobalUtils.class";
    public static final String SOURCE_FILE_NAME = "SOURCE_FILE_NAME";
    public static final String INGESTED_BY = "INGESTED_BY";

    // Success Messages
    public static final String SUCCESSFUL_INGESTION = "File - %s ingested successfully.";

    // Error Messages
    public static final String FAILURE_PRE_PROCESSING_PDF = "Failure in processing & tokenization step while ingesting file - %s";
    public static final String FAILURE_INGESTING_PDF = "Failure in ingesting file - %s";
}
