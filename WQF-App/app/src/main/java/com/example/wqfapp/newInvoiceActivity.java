package com.example.wqfapp;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class newInvoiceActivity extends Activity {

    //for database
    DatabaseHelper myDb;
    EditText editNotes,editDate,editMarkup, editInvoiceId, editDiscount;
    Button btnAddData;
    Button btnviewAll;
    Button btnDelete;
    Button btnviewUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_invoice_view);
        myDb = new DatabaseHelper(this);

        //for database
        editDiscount = (EditText) findViewById(R.id.discountPriceTextView);
        editNotes = (EditText)findViewById(R.id.editNotesTextView);
        editDate = (EditText)findViewById(R.id.editDateTextView);
        editMarkup = (EditText) findViewById(R.id.markupPriceTextView);
        editInvoiceId = (EditText) findViewById(R.id.estimateNoTextView);
        btnAddData = (Button)findViewById(R.id.doneButton);
        //to bue used in invoice list activity
        //btnviewAll = (Button)findViewById(R.id.button_viewAll);
        //btnviewUpdate = (Button)findViewById(R.id.button_update);
        //btnDelete = (Button)findViewById(R.id.button_delete);
        AddData();
        viewAll();
        UpdateData();
        DeleteData();
    }

    public void goBack (View view){
        onBackPressed();
    }

    public void done (View view) {
        finish();
    }

    //for database
    public void DeleteData() {
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v){
                        Integer deletedRows = myDb.deleteData(editInvoiceId.getText().toString());
                        if(deletedRows > 0 )
                            Toast.makeText(newInvoiceActivity.this,"Data Deleted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(newInvoiceActivity.this,"Data not Deleted",Toast.LENGTH_LONG).show();

                    }
                }
        );
    }

    public void UpdateData(){
        btnviewUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = myDb.updateData(editInvoiceId.getText().toString(),
                                editNotes.getText().toString(),
                                editDate.getText().toString(),
                                editMarkup.getText().toString(),
                                editDiscount.getText().toString());
                        if(isUpdate == true)
                            Toast.makeText(newInvoiceActivity.this,"Data Updated",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(newInvoiceActivity.this,"Data not Updated",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void AddData(){
        btnAddData.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertData(editNotes.getText().toString(),
                                editDate.getText().toString(),
                                editMarkup.getText().toString(),
                                editDiscount.getText().toString());
                        if(isInserted == true)
                            Toast.makeText(newInvoiceActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(newInvoiceActivity.this,"Data not Inserted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void viewAll() {
        btnviewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v){
                        Cursor res = myDb.getAllData();
                        if(res.getCount() == 0) {
                            // show message
                            showMessage("Error", "Nothing found");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Id :" + res.getString(0) + "\n");
                            buffer.append("Name :" + res.getString(1) + "\n");
                            buffer.append("Surname :" + res.getString(2) + "\n");
                            buffer.append("Marks :" + res.getString(3) + "\n\n");
                        }

                        // Show all data
                        showMessage("Data", buffer.toString());
                    }
                });

    }

    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
