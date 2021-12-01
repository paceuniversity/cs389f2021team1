package com.example.corporate;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class CompanyAdapter extends FirestoreRecyclerAdapter<Company, CompanyAdapter.CompanyHolder> {

    //sean
    private onCompanyListener mOnCompanyListener;

    public CompanyAdapter(@NonNull FirestoreRecyclerOptions<Company> options, onCompanyListener OnCompanyListener) {
        super(options);
        //sean
        this.mOnCompanyListener = OnCompanyListener;
    }

    @Override
    protected void onBindViewHolder(@NonNull CompanyHolder holder, int position, @NonNull Company model) {
        String numOfReviews;
        if(model.getNumOfReviews() == 1) {
            numOfReviews = model.getNumOfReviews() + " Review";
        }
        else {
            numOfReviews = model.getNumOfReviews() + " Reviews";
        }
        holder.companyName.setText(model.getName());
        holder.companyLocation.setText(model.getLocation());
        holder.companyNumReviews.setText(numOfReviews);
        Glide.with(holder.companyLogo.getContext()).load(model.getLogo())
                .fitCenter().placeholder(holder.companyLogo.getDrawable()).into(holder.companyLogo);
        holder.ratingBar.setRating(model.getAvgRating());
    }

    @NonNull
    @Override
    public CompanyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.company_card,
                parent, false);
        return new CompanyHolder(v,mOnCompanyListener);
    }

    class CompanyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView companyName, companyLocation, companyNumReviews;
        ImageView companyLogo;
        RatingBar ratingBar;
        //sean
        onCompanyListener onCompanyListener;

        public CompanyHolder(@NonNull View itemView, onCompanyListener onCompanyListener) {
            super(itemView);
            //sean
            this.onCompanyListener = onCompanyListener;

            companyName = itemView.findViewById(R.id.company_name);
            companyLocation = itemView.findViewById(R.id.company_location);
            companyNumReviews = itemView.findViewById(R.id.company_num_reviews);
            companyLogo = itemView.findViewById(R.id.company_logo);
            ratingBar = itemView.findViewById(R.id.ratingBar);

            //sean
            itemView.setOnClickListener(this);
        }

        //sean
        @Override
        public void onClick(View v) {
            onCompanyListener.onCompanyClick(getAbsoluteAdapterPosition());
        }
    }

    //sean
    public interface onCompanyListener{
        void onCompanyClick(int position);
    }
}
