import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class PdfGenerator {

    private static final float PAGE_WIDTH =
            PDRectangle.A4.getWidth();

    private static final float PAGE_HEIGHT =
            PDRectangle.A4.getHeight();

    private static final float MARGIN = 45;

    private static final float CONTENT_WIDTH =
            PAGE_WIDTH - 2 * MARGIN;

    private static final Color DARK_BLUE =
            new Color(30, 49, 71);

    private static final Color ACCENT =
            new Color(45, 112, 150);

    private static final Color TEXT_COLOR =
            new Color(55, 62, 70);

    private static final Color GRAY =
            new Color(115, 123, 132);

    public void generateResume(
            Resume resume,
            String outputPath,
            String photoPath) throws IOException {

        File outputFile = new File(outputPath);
        File photoFile = new File(photoPath);

        if (!photoFile.isFile()) {
            throw new FileNotFoundException(
                    "Profil fotografi bulunamadi: "
                            + photoFile.getAbsolutePath()
            );
        }

        File parent = outputFile.getAbsoluteFile().getParentFile();

        if (parent != null && !parent.exists() && !parent.mkdirs()) {
            throw new IOException("Cikti klasoru olusturulamadi.");
        }

        try (PDDocument document = new PDDocument()) {

            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            PDFont regularFont = loadFont(document, false);
            PDFont boldFont = loadFont(document, true);

            try (PDPageContentStream stream =
                         new PDPageContentStream(document, page)) {

                drawHeader(
                        document,
                        stream,
                        resume,
                        photoFile,
                        regularFont,
                        boldFont
                );

                float y = drawSectionHeading(
                        stream, boldFont, "PROFIL", 675
                );

                y = drawWrappedText(
                        stream,
                        regularFont,
                        resume.getSummary(),
                        MARGIN,
                        y,
                        CONTENT_WIDTH,
                        13,
                        9.5f,
                        TEXT_COLOR
                );

                y -= 10;

                y = drawSectionHeading(
                        stream, boldFont, "IS DENEYIMI", y
                );

                List<JobExperience> experiences =
                        resume.getExperiences();

                for (JobExperience experience : experiences) {
                    y = drawExperience(
                            stream,
                            experience,
                            regularFont,
                            boldFont,
                            y
                    );
                }

                y -= 2;

                y = drawSectionHeading(
                        stream, boldFont, "EGITIM", y
                );

                y = drawWrappedText(
                        stream,
                        regularFont,
                        resume.getEducation(),
                        MARGIN,
                        y,
                        CONTENT_WIDTH,
                        13,
                        9.5f,
                        TEXT_COLOR
                );

                y -= 7;

                y = drawSectionHeading(
                        stream, boldFont, "TEKNIK BECERILER", y
                );

                y = drawWrappedText(
                        stream,
                        regularFont,
                        resume.getSkills(),
                        MARGIN,
                        y,
                        CONTENT_WIDTH,
                        13,
                        9.5f,
                        TEXT_COLOR
                );

                y -= 7;

                y = drawSectionHeading(
                        stream, boldFont, "DILLER", y
                );

                drawWrappedText(
                        stream,
                        regularFont,
                        resume.getLanguages(),
                        MARGIN,
                        y,
                        CONTENT_WIDTH,
                        13,
                        9.5f,
                        TEXT_COLOR
                );

                drawFooter(stream, regularFont);
            }

            document.save(outputFile);
        }
    }

    private PDFont loadFont(
            PDDocument document,
            boolean bold) throws IOException {

        String fontName = bold
                ? "DejaVuSans-Bold.ttf"
                : "DejaVuSans.ttf";

        File bundledFont = new File(
                "src/main/resources/fonts/" + fontName
        );

        if (bundledFont.isFile()) {
            return PDType0Font.load(document, bundledFont);
        }

        File windowsFont = new File(
                bold
                        ? "C:/Windows/Fonts/arialbd.ttf"
                        : "C:/Windows/Fonts/arial.ttf"
        );

        if (windowsFont.isFile()) {
            return PDType0Font.load(document, windowsFont);
        }

        File regularArial = new File("C:/Windows/Fonts/arial.ttf");

        if (regularArial.isFile()) {
            return PDType0Font.load(document, regularArial);
        }

        throw new FileNotFoundException(
                "Turkce karakterleri destekleyen bir TTF font bulunamadi. "
                        + "Arial veya DejaVu Sans yazı tipini kullanin."
        );
    }

    private void drawHeader(
            PDDocument document,
            PDPageContentStream stream,
            Resume resume,
            File photoFile,
            PDFont regularFont,
            PDFont boldFont) throws IOException {

        stream.setNonStrokingColor(DARK_BLUE);

        stream.addRect(
                0,
                PAGE_HEIGHT - 142,
                PAGE_WIDTH,
                142
        );

        stream.fill();

        drawText(
                stream, boldFont, 21,
                MARGIN, 790,
                resume.getFullName(),
                Color.WHITE
        );

        drawText(
                stream, regularFont, 12,
                MARGIN, 765,
                resume.getJobTitle(),
                new Color(195, 222, 239)
        );

        drawText(
                stream, regularFont, 9,
                MARGIN, 741,
                resume.getEmail(),
                Color.WHITE
        );

        drawText(
                stream, regularFont, 9,
                MARGIN, 725,
                resume.getPhone() + " | " + resume.getLocation(),
                Color.WHITE
        );

        PDImageXObject portrait =
                PDImageXObject.createFromFileByContent(
                        photoFile, document
                );

        stream.drawImage(
                portrait,
                PAGE_WIDTH - MARGIN - 82,
                PAGE_HEIGHT - 132,
                82,
                96
        );
    }

    private float drawSectionHeading(
            PDPageContentStream stream,
            PDFont boldFont,
            String title,
            float y) throws IOException {

        drawText(
                stream,
                boldFont,
                11,
                MARGIN,
                y,
                title,
                DARK_BLUE
        );

        stream.setStrokingColor(ACCENT);
        stream.setLineWidth(1);

        stream.moveTo(MARGIN, y - 7);
        stream.lineTo(PAGE_WIDTH - MARGIN, y - 7);
        stream.stroke();

        return y - 22;
    }

    private float drawExperience(
            PDPageContentStream stream,
            JobExperience experience,
            PDFont regularFont,
            PDFont boldFont,
            float y) throws IOException {

        drawText(
                stream,
                boldFont,
                10,
                MARGIN,
                y,
                experience.getPosition()
                        + " | " + experience.getCompany(),
                DARK_BLUE
        );

        y -= 14;

        drawText(
                stream,
                regularFont,
                9,
                MARGIN,
                y,
                experience.getPeriod(),
                GRAY
        );

        y -= 13;

        y = drawWrappedText(
                stream,
                regularFont,
                experience.getDescription(),
                MARGIN,
                y,
                CONTENT_WIDTH,
                11,
                9,
                TEXT_COLOR
        );

        return y - 8;
    }

    private float drawWrappedText(
            PDPageContentStream stream,
            PDFont font,
            String text,
            float x,
            float y,
            float maxWidth,
            float leading,
            float fontSize,
            Color color) throws IOException {

        if (text == null || text.isBlank()) {
            return y;
        }

        String[] words = text.trim().split("\\s+");
        StringBuilder currentLine = new StringBuilder();

        for (String word : words) {

            String candidate = currentLine.length() == 0
                    ? word
                    : currentLine + " " + word;

            float textWidth =
                    font.getStringWidth(candidate) * fontSize / 1000f;

            if (textWidth > maxWidth && currentLine.length() > 0) {

                drawText(
                        stream,
                        font,
                        fontSize,
                        x,
                        y,
                        currentLine.toString(),
                        color
                );

                y -= leading;
                currentLine = new StringBuilder(word);

            } else {
                currentLine = new StringBuilder(candidate);
            }
        }

        if (currentLine.length() > 0) {
            drawText(
                    stream,
                    font,
                    fontSize,
                    x,
                    y,
                    currentLine.toString(),
                    color
            );

            y -= leading;
        }

        return y;
    }

    private void drawText(
            PDPageContentStream stream,
            PDFont font,
            float fontSize,
            float x,
            float y,
            String text,
            Color color) throws IOException {

        stream.beginText();
        stream.setFont(font, fontSize);
        stream.setNonStrokingColor(color);
        stream.newLineAtOffset(x, y);
        stream.showText(text);
        stream.endText();
    }

    private void drawFooter(
            PDPageContentStream stream,
            PDFont regularFont) throws IOException {

        stream.setStrokingColor(
                new Color(220, 225, 230)
        );

        stream.setLineWidth(0.7f);
        stream.moveTo(MARGIN, 48);
        stream.lineTo(PAGE_WIDTH - MARGIN, 48);
        stream.stroke();

        drawText(
                stream,
                regularFont,
                8,
                MARGIN,
                33,
                "Ornek ozgecmis - Bilgiler egitim amaciyla hazirlanmistir.",
                GRAY
        );
    }
}