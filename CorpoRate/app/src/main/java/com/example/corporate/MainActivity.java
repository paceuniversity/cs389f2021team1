package com.example.corporate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;

public class MainActivity extends AppCompatActivity {
    private SearchView search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search = findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                openSearchResults();

            }
        });
    }
    public void openSearchResults(){
        Intent intent = new Intent(this, searchActivity.class);
        startActivity(intent);
    }
}