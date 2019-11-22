package com.example.wqfapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_invoices:
                    mTextMessage.setText("Invoices");
                    return true;
                case R.id.navigation_clients:
                    mTextMessage.setText("Clients");
                    return true;
                case R.id.navigation_settings:
                    mTextMessage.setText("Settings");
                    return true;
            }
            return false;
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.invoice_list_view);

        recyclerView = findViewById(R.id.invoiceRecyclerView);
        recyclerView.setHasFixedSize(true); //may need to be changed
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        //mAdapter = new MyAdapter(myDataset);
        recyclerView.setAdapter(mAdapter);

        mTextMessage = findViewById(R.id.message);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //SearchView invoiceSearchView = findViewById(R.id.invoiceSearchView);

        ((EditText)((SearchView)findViewById(R.id.invoiceSearchView))
                .findViewById(((SearchView)findViewById(R.id.invoiceSearchView)).getContext()
                        .getResources().getIdentifier("android:id/search_src_text",
                                null, null))).setHintTextColor(Color.BLACK);
        }

}
