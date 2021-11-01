package com.example.corporate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    public static final String EXTRA_MESSAGE = "com.example.corporate.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("Home");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.topbar,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Profile:
                startActivity(new Intent(this, ProfileActivity.class));
                return(true);
            case R.id.About:
                startActivity(new Intent(this, aboutActivity.class));
                return(true);
        }

        return super.onOptionsItemSelected(item);
    }

    /** Called when the user taps the Search button */
    public void openSearchResults(View view) {
        Intent intent = new Intent(this, searchActivity.class);
        EditText searchField = (EditText) findViewById(R.id.searchField);
        String searchQuery = searchField.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, searchQuery);
        startActivity(intent);
    }

    /** Called when the user taps the About button */
    public void openAboutPage(View view) {
        Intent intent = new Intent(this, aboutActivity.class);
        startActivity(intent);
    }
}