package com.example.kino;

public class Film {
    int filmId;
    String nameRu;
    String nameEn;
    int year;
    String filmLength;
    String[] genres;
    double rating;
    String postUrl;
    String postUrlPrev;

    public Film(int filmId, String nameRu, String nameEn, int year, String filmLength,
                String[] genres, double rating, String postUrl, String postUrlPrev) {
        this.filmId = filmId;
        this.nameRu = nameRu;
        this.nameEn = nameEn;
        this.year = year;
        this.filmLength = filmLength;
        this.genres = genres;
        this.rating = rating;
        this.postUrl = postUrl;
        this.postUrlPrev = postUrlPrev;
    }
}
