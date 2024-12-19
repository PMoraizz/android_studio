package com.example.aula18_12_24;


public class Movie {
    private String overview;
    private String title;
    private String releaseDate;
    private int posterImage;
    private int categoria;
    private double rating;

    public Movie(String title, int posterImage, String releaseDate, double rating, String overview, int categoria) {
        this.overview = overview;
        this.title = title;
        this.releaseDate = releaseDate;
        this.posterImage = posterImage;
        this.categoria = categoria;
        this.rating = rating;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getPosterImage() {
        return posterImage;
    }

    public void setPosterImage(int posterImage) {
        this.posterImage = posterImage;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }


}
