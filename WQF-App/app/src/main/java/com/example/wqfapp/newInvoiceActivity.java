package com.example.wqfapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class newInvoiceActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_invoice_view);
    }

    public void goBack (View view){
        onBackPressed();
    }

    public void done (View view) {
        finish();
    }
}
