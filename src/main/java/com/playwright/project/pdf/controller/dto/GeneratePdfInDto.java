package com.playwright.project.pdf.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GeneratePdfInDto {

    @Schema(description = "PDF 생성할 URL", example = "https://playwright.dev/")
    private String url;

    @Schema(description = "PDF 생성할 너비", example = "1920")
    private Integer width;

    @Schema(description = "PDF 생성할 높이", example = "1080")
    private Integer height;

}
