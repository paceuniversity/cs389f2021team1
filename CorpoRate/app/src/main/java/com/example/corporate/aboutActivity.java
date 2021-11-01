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
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class aboutActivity extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final String TAG = "aboutActivity";
    private static final String KEY_NAME = "content";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        this.setTitle("About");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.topbar,menu);
        menu.getItem(2).setVisible(false);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Home:
                startActivity(new Intent(this, MainActivity.class));
                return(true);
            case R.id.Profile:
                startActivity(new Intent(this, ProfileActivity.class));
                return(true);
        }
        return super.onOptionsItemSelected(item);
    }

    public void addReport(View view) {
        EditText reportEditText = (EditText)findViewById(R.id.reportField);
        String reportContent = reportEditText.getText().toString();

        if (reportContent.isEmpty()) {
            Toast.makeText(aboutActivity.this, "You did not write an issue!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a report to be added
        Map<String, Object> report = new HashMap<>();
        report.put(KEY_NAME, reportContent);

        // Add report to the database
        db.collection("Reports").document().set(report)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(aboutActivity.this, "Report submitted!", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(aboutActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, e.toString());
                    }
                });

        // Clear the user's report text from the EditText field
        reportEditText.getText().clear();
    }
}