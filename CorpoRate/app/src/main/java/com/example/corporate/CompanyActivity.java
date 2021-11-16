package com.example.corporate;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.animation.LayoutTransition;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CompanyActivity extends AppCompatActivity {
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private ImageView companyLogo;
    private TextView companyName;
    private TextView companyLocation;
    private RatingBar avgRating;
    private RatingBar avgEthics;
    private RatingBar avgEnvironmental;
    private RatingBar avgLeadership;
    private RatingBar avgWageEquality;
    private RatingBar avgWorkingConditions;
    private TextView totalReviews;
    private LinearLayout ratingLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);

        //hooks
        companyLogo = findViewById(R.id.companyLogo);
        companyName = findViewById(R.id.companyName);
        companyLocation = findViewById(R.id.companyLocation);
        avgRating = findViewById(R.id.overallRating);
        avgEthics = findViewById(R.id.EthicsRating);
        avgEnvironmental = findViewById(R.id.EnvironmentalRating);
        avgLeadership = findViewById(R.id.LeadershipRating);
        avgWageEquality = findViewById(R.id.WageEqualityRating);
        avgWorkingConditions = findViewById(R.id.WorkingConditionsRating);
        totalReviews = findViewById(R.id.totalCompanyReviews);
        ratingLayout = findViewById(R.id.companyRatingDetails);

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
        navigationView.setNavigationItemSelectedListener(this::onNavigationItemSelected);
        navigationView.setCheckedItem(R.id.nav_home);

        // Show All User Data
        DocumentReference docRef = db.collection("Companies")
                .document("Amazon");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document != null && document.exists()) {
                        companyName.setText(document.getId());
                        companyLocation.setText(document.getString("location"));
                        avgRating.setRating(Objects.requireNonNull(document.getLong("avgRating")).floatValue());
                        avgEthics.setRating(Objects.requireNonNull(document.getLong("avgEthics")).floatValue());
                        avgEnvironmental.setRating(Objects.requireNonNull(document.getLong("avgEnvironmental")).floatValue());
                        avgLeadership.setRating(Objects.requireNonNull(document.getLong("avgLeadership")).floatValue());
                        avgWageEquality.setRating(Objects.requireNonNull(document.getLong("avgWageEquality")).floatValue());
                        avgWorkingConditions.setRating(Objects.requireNonNull(document.getLong("avgWorkingConditions")).floatValue());
                        totalReviews.setText(document.getLong("numOfReviews").toString());
                    } else {
                        Log.d(TAG, "Document does not exist.");
                    }
                }
                else {
                    Log.d(TAG, "Failed to pull from database.", task.getException());
                }
            }
        });
    }


    /** Drawer Navigation Handling */
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                startActivity(new Intent(CompanyActivity.this, MainActivity.class));
                break;
            case R.id.nav_search:
                startActivity(new Intent(CompanyActivity.this, SearchActivity.class));
                break;
            case R.id.nav_my_reviews:
                break;
            case R.id.nav_about:
                startActivity(new Intent(CompanyActivity.this, AboutActivity.class));
                break;
            case R.id.nav_profile:
                startActivity(new Intent(CompanyActivity.this, ProfileActivity.class));
                break;
            case R.id.nav_sign_out:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(CompanyActivity.this, LoginActivity.class));
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public void expandCard(View view) {
        if (ratingLayout.getVisibility() == View.GONE) {
            TransitionManager.beginDelayedTransition(ratingLayout, new AutoTransition());
            ratingLayout.setVisibility(View.VISIBLE);
        }else {
            TransitionManager.beginDelayedTransition(ratingLayout, new AutoTransition());
            ratingLayout.setVisibility(View.GONE);
        }
    }
}