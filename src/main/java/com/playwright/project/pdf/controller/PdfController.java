package com.playwright.project.pdf.controller;

import com.playwright.project.pdf.application.command.GeneratePdfCommand;
import com.playwright.project.pdf.application.usecase.PdfGenerationUseCase;
import com.playwright.project.pdf.controller.dto.GeneratePdfInDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "PDF", description = "PDF 관련 API")
@RestController
@RequestMapping("/api/pdf")
@RequiredArgsConstructor
public class PdfController {

    private final PdfGenerationUseCase pdfGenerationUseCase;

    @Operation(summary = "PDF 생성", description = "PDF를 생성합니다.")
    @PostMapping(value = "/generate", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<Resource> generate(@RequestBody GeneratePdfInDto dto) {
        GeneratePdfCommand command = GeneratePdfCommand.builder()
                .url(dto.getUrl())
                .width(dto.getWidth())
                .height(dto.getHeight())
                .build();

        String filename = "output.pdf";
        byte[] pdfBytes = pdfGenerationUseCase.generatePdf(command);

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(pdfBytes);
        InputStreamResource resource = new InputStreamResource(byteArrayInputStream);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition.attachment()
                .filename(filename, StandardCharsets.UTF_8)
                .build());
        headers.setContentLength(pdfBytes.length);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }

}
