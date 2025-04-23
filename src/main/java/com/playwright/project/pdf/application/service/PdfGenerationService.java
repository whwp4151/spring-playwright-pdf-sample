package com.playwright.project.pdf.application.service;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.LoadState;
import com.playwright.project.pdf.application.command.GeneratePdfCommand;
import com.playwright.project.pdf.application.usecase.PdfGenerationUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PdfGenerationService implements PdfGenerationUseCase {

    @Override
    public byte[] generatePdf(GeneratePdfCommand command) {
        long startTime = System.nanoTime();
        try (
                Playwright playwright = Playwright.create();
                Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chromium"));
                BrowserContext context = browser.newContext(new Browser.NewContextOptions()
                        .setViewportSize(command.getWidth(), 1080)
                        .setJavaScriptEnabled(true));
                Page page = context.newPage()
        ) {
            log.info("[PDF] Playwright 초기화 완료. ({} ms)", elapsed(startTime));

            long navStart = System.nanoTime();
            page.navigate(command.getUrl());
            log.info("[PDF] 페이지 네비게이션 완료. ({} ms)", elapsed(navStart));

            long loadStart = System.nanoTime();
            page.waitForLoadState(LoadState.DOMCONTENTLOADED);
            log.info("[PDF] 페이지 로드 완료. ({} ms)", elapsed(loadStart));

            page.waitForTimeout(1000);
            log.info("[PDF] waitForTimeout(1000) 완료");

            long pdfGenStart = System.nanoTime();
            byte[] pdfBytes = page.pdf(new Page.PdfOptions()
                    .setPrintBackground(true)
                    .setWidth(command.getWidth() + "px")
                    .setHeight(command.getHeight() + "px"));
            log.info("[PDF] PDF 생성 완료. ({} ms)", elapsed(pdfGenStart));

            return pdfBytes;
        }
    }

    private long elapsed(long startTime) {
        return (System.nanoTime() - startTime) / 1_000_000;
    }

}
