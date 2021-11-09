package com.example.corporate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SearchActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private static final String TAG = "searchActivity";
    private static final String KEY_NAME_CONTENT= "content";
    private static final String KEY_NAME_UID = "uid";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        this.setTitle("Search");

        // Drawer Navigation + Toolbar
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_search);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String searchQuery = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.searchField2);
        textView.setText(searchQuery);
    }

    public void search(View view) {

    }

    public void addSuggestion(View view) {
        EditText suggestionEditText = (EditText)findViewById(R.id.suggestField);
        String suggestionContent = suggestionEditText.getText().toString();

        if (suggestionContent.isEmpty()) {
            Toast.makeText(SearchActivity.this, "You did not write a suggestion!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a suggestion to be added
        Map<String, Object> suggestion = new HashMap<>();
        suggestion.put(KEY_NAME_CONTENT, suggestionContent);
        suggestion.put(KEY_NAME_UID, Objects.requireNonNull(auth.getCurrentUser()).getUid());

        // Add suggestion to the database
        db.collection("Suggestions").document().set(suggestion)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(SearchActivity.this, "Suggestion submitted!", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(SearchActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, e.toString());
                    }
                });

        // Clear the user's suggestion text from the EditText field
        suggestionEditText.getText().clear();
    }

    /** Drawer Navigation Handling */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                startActivity(new Intent(SearchActivity.this, MainActivity.class));
                break;
            case R.id.nav_search:
                break;
            case R.id.nav_my_reviews:
                break;
            case R.id.nav_about:
                startActivity(new Intent(SearchActivity.this, AboutActivity.class));
                break;
            case R.id.nav_profile:
                startActivity(new Intent(SearchActivity.this, ProfileActivity.class));
                break;
            case R.id.nav_sign_out:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(SearchActivity.this, LoginActivity.class));
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}