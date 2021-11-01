package com.example.corporate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class searchActivity extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final String TAG = "searchActivity";
    private static final String KEY_NAME = "content";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        this.setTitle("Search");

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String searchQuery = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.searchField2);
        textView.setText(searchQuery);
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

    public void search(View view) {

    }

    public void addSuggestion(View view) {
        EditText suggestionEditText = (EditText)findViewById(R.id.suggestField);
        String suggestionContent = suggestionEditText.getText().toString();

        if (suggestionContent.isEmpty()) {
            Toast.makeText(searchActivity.this, "You did not write a suggestion!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a suggestion to be added
        Map<String, Object> suggestion = new HashMap<>();
        suggestion.put(KEY_NAME, suggestionContent);

        // Add suggestion to the database
        db.collection("Suggestions").document().set(suggestion)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(searchActivity.this, "Suggestion submitted!", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(searchActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, e.toString());
                    }
                });

        // Clear the user's suggestion text from the EditText field
        suggestionEditText.getText().clear();
    }
}