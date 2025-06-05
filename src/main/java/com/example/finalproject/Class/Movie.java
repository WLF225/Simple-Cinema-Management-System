package com.example.finalproject.Class;

public class Movie implements Comparable<Movie> {

    private String title;
    private String description;
    private int year;
    private double rating;

    public Movie(String title, String description, int year, double rating) {
        setTitle(title);
        setDescription(description);
        setYear(year);
        setRating(rating);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        if (year < 1888) {
            throw new ArithmeticException("The first movie was made in the 1888");
        }
        this.year = year;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        if(rating > 10 || rating < 0)
            throw new ArithmeticException("The rating must be between 0 and 10");
        this.rating = rating;
    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }

    @Override
    public int compareTo(Movie movie) {
        return title.compareTo(movie.title);
    }

    @Override
    public String toString() {
        return  "Title: " +title+
                "\nDescription: "+description+"\n" +
                "Release Year: "+year+"\n" +
                "Rating: "+rating+"\n\n";
    }
}
