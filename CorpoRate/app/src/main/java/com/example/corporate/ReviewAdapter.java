package com.example.corporate;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;
import java.util.Objects;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReveiwViewHolder> {
    private Context mCtx;
    private List<Review> reviewList;
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    public ReviewAdapter(Context mCtx, List<Review> reviewList){
        this.mCtx = mCtx;
        this.reviewList = reviewList;
    }

    @NonNull
    @Override
    public ReveiwViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ReveiwViewHolder(
                LayoutInflater.from(mCtx).inflate(R.layout.review_card, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ReveiwViewHolder holder, int position) {
        Review review = reviewList.get(position);

        //get username
        DocumentReference docRef = db.collection("Users")
                .document(review.getUID());
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document != null && document.exists()) {
                        if(!document.getBoolean("anonymous"))
                            holder.username.setText(document.getString("username"));
                        else
                            holder.username.setText("Anonymous User");
                    } else {
                        Log.d(TAG, "Document does not exist.");
                    }
                }
                else {
                    Log.d(TAG, "Failed to pull from database.", task.getException());
                }
            }
        });

        holder.reviewDesc.setText(review.getReviewText());
        holder.reviewDesc.setText(review.getReviewText());
    }

    @Override
    public int getItemCount() {
        return reviewList.size();
    }

    class ReveiwViewHolder extends RecyclerView.ViewHolder{
        TextView username, reviewDesc, avgEnvironmental, avgEthics, avgLeadership, avgWageEquality, avgWorkingConditions;
        RatingBar avgRatingBar;
        public ReveiwViewHolder(View itemView){
            super(itemView);

            username = itemView.findViewById(R.id.reviewUserName);
            avgRatingBar = itemView.findViewById(R.id.avgRatingBar);
            reviewDesc = itemView.findViewById(R.id.reviewDesc);
            avgEnvironmental = itemView.findViewById(R.id.environmentalRating);
            avgEthics = itemView.findViewById(R.id.ethicsRating);
            avgLeadership = itemView.findViewById(R.id.leadershipRating);
            avgWageEquality = itemView.findViewById(R.id.wageEqualityRating);
            avgWorkingConditions = itemView.findViewById(R.id.workingConditionsRating);
        }
    }


}