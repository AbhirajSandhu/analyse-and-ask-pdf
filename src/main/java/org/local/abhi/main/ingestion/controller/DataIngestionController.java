package org.local.abhi.main.ingestion.controller;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.local.abhi.main.ingestion.service.DataIngestionService;
import org.local.abhi.main.ingestion.model.IngestionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

import static org.local.abhi.main.commons.constants.Constants.REQUEST_PATH;


@Slf4j
@RestController
@RequestMapping(REQUEST_PATH)
@RequiredArgsConstructor
public class DataIngestionController {

    private final DataIngestionService dataIngestionService;

    @PostMapping("/upload-and-analyse")
    public ResponseEntity<IngestionResponse> ingestPdf(
            @RequestParam @Nonnull MultipartFile file,
            @RequestHeader Map<String, String> requestHeaders
    ) {
        // TODO: Extract user details from auth token / identity context
        return dataIngestionService.processPdfFile(file, "local-user");
    }

    @GetMapping("/ask-the-bot")
    public ResponseEntity<String> askFromPdf(
            @RequestBody(required = false) Map<String, String> reqBody
    ) {
        return ResponseEntity.status(HttpStatus.OK).body("Response!!!");
    }
}
