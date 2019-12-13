package com.uskillshare.pdfcreate;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import android.graphics.Bitmap;
import android.os.Environment;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.Barcode39;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

//import net.glxn.qrgen.android.QRCode;
//import net.glxn.qrgen.core.image.ImageType;

public class PdfCreate {
  private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 22, Font.BOLD);
  private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
  private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
  static String user = "123456";
  static String owner = "12345";

  public void creating() {
    try {
      Document document = new Document(PageSize.A4);
      String newFolder = ".uskillshare";
      File f = new File(Environment.getExternalStorageDirectory(), newFolder);
      if (!f.exists()) {
        f.mkdirs();
      }

      String outPath = Environment.getExternalStorageDirectory() + newFolder+"/demo.pdf";
      // Create PDFWriter instance.
      PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(outPath));
      // Add password protection.
      pdfWriter.setEncryption(user.getBytes(), owner.getBytes(),
        PdfWriter.ALLOW_COPY | PdfWriter.ALLOW_PRINTING,
        PdfWriter.STANDARD_ENCRYPTION_128);
      document.open();
      addMetaData(document);
      addTitlePage(document);
      document.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // iText allows to add metadata to the PDF which can be viewed in your Adobe
  private static void addMetaData(Document document) {
    document.addTitle("uskillshare");
    document.addSubject("testing.");
  }

  private static void addTitlePage(Document document) throws DocumentException {
    Paragraph preface = new Paragraph();
    preface.add(new Paragraph("Sagar", catFont));
    document.add(preface);
    addEmptyLine(preface, 1);
    // part 1
    preface = new Paragraph();
    preface.add(new Paragraph("Part 1:", subFont));
    document.add(preface);
    BarcodeQRCode barcodeQRCode = new BarcodeQRCode("part1", 250, 250, null);
    Image codeQrImage = barcodeQRCode.getImage();
    codeQrImage.scaleAbsolute(250, 250);
    document.add(codeQrImage);
  }

  private static void addEmptyLine(Paragraph paragraph, int number) {
    for (int i = 0; i < number; i++) {
      paragraph.add(new Paragraph(" "));
    }
  }

}
