package com.uskillshare.pdfcreate;

import androidx.appcompat.app.AppCompatActivity;

import com.uskillshare.pdfcreate.PdfCreate;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    PdfCreate pdf = new PdfCreate();
    pdf.creating();
  }
}
