package com.playwright.project.pdf.application.usecase;

import com.playwright.project.pdf.application.command.GeneratePdfCommand;

public interface PdfGenerationUseCase {
    byte[] generatePdf(GeneratePdfCommand command);
}
