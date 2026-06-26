package org.local.abhi.ingestion.service;

import lombok.RequiredArgsConstructor;
import org.local.abhi.ingestion.utils.DataIngestionComponent;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DataIngestionService {

    // for creating abstraction layer
    // do not want controller to directly access the logic
    private final DataIngestionComponent ingestionComponent;


}
