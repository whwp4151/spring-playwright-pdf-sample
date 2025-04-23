package com.playwright.project.pdf.application.command;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GeneratePdfCommand {
    private String url;
    private Integer width;
    private Integer height;

    @Builder
    public GeneratePdfCommand(String url, Integer width, Integer height) {
        Assert.notNull(url, "url is required.");
        Assert.notNull(width, "width is required.");
        Assert.notNull(height, "height is required.");

        this.url = url;
        this.width = width;
        this.height = height;
    }
}
