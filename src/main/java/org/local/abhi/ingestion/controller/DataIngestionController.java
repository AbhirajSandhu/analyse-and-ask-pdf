package org.local.abhi.ingestion.controller;

import org.local.abhi.ingestion.model.IngestionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

import static org.local.abhi.commons.Constants.REQUEST_PATH;


@RestController
@RequestMapping(REQUEST_PATH)
public class DataIngestionController {

    @PostMapping("/upload-and-analyse")
    public ResponseEntity<IngestionResponse> ingestPdf(
            @RequestParam(required = false) MultipartFile pdfFile,
            @RequestHeader Map<String, String> requestHeaders
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(IngestionResponse.builder().message("Successfully Uploaded!!!").build());
    }

    @GetMapping("/ask-the-bot")
    public ResponseEntity<String> askFromPdf(
            @RequestBody(required = false) Map<String, String> reqBody
    ) {
        return ResponseEntity.status(HttpStatus.OK).body("Response!!!");
    }
}
