package com.example.corporate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReveiwViewHolder> {
    private Context mCtx;
    private List<Review> reviewList;

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