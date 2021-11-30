package com.example.corporate;

public class Review {
    private String company, UID, reviewText;
    private double avgEnvironmental, avgEthics, avgLeadership, avgRating, avgWageEquality,avgWorkingConditions;
    private int numOfDislikes, numOfLikes;

    public Review(){

    }

    public Review(String company, String UID, String reviewText, double avgEnvironmental, double avgEthics,
                  double avgLeadership, double avgRating, double avgWageEquality,double avgWorkingConditions,
                  int numOfDislikes, int numOfLikes) {
        this.company = company;
        this.UID = UID;
        this.reviewText = reviewText;
        this.avgEnvironmental = avgEnvironmental;
        this.avgEthics = avgEthics;
        this.avgLeadership = avgLeadership;
        this.avgRating = avgRating;
        this.avgWageEquality = avgWageEquality;
        this.avgWorkingConditions = avgWorkingConditions;
        this.numOfDislikes = numOfDislikes;
        this.numOfLikes = numOfLikes;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getUID() {
        return UID;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public double getAvgEnvironmental() {
        return avgEnvironmental;
    }

    public void setAvgEnvironmental(double avgEnvironmental) {
        this.avgEnvironmental = avgEnvironmental;
    }

    public double getAvgEthics() {
        return avgEthics;
    }

    public void setAvgEthics(double avgEthics) {
        this.avgEthics = avgEthics;
    }

    public double getAvgLeadership() {
        return avgLeadership;
    }

    public void setAvgLeadership(double avgLeadership) {
        this.avgLeadership = avgLeadership;
    }

    public double getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(double avgRating) {
        this.avgRating = avgRating;
    }

    public double getAvgWageEquality() {
        return avgWageEquality;
    }

    public void setAvgWageEquality(double avgWageEquality) {
        this.avgWageEquality = avgWageEquality;
    }

    public double getAvgWorkingConditions() {
        return avgWorkingConditions;
    }

    public void setAvgWorkingConditions(double avgWorkingConditions) {
        this.avgWorkingConditions = avgWorkingConditions;
    }

    public int getNumOfDislikes() {
        return numOfDislikes;
    }

    public void setNumOfDislikes(int numOfDislikes) {
        this.numOfDislikes = numOfDislikes;
    }

    public int getNumOfLikes() {
        return numOfLikes;
    }

    public void setNumOfLikes(int numOfLikes) {
        this.numOfLikes = numOfLikes;
    }
}
