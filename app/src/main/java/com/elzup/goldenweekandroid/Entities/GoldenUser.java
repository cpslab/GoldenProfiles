package com.elzup.goldenweekandroid.entities;

public class GoldenUser {
    // ニックネーム,学籍番号,今期アニメ,好きなアニメ,好きな言語,画像
    private String name, studentID, currentAnime, favoriteAnime, language, imgURL;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getCurrentAnime() {
        return currentAnime;
    }

    public void setCurrentAnime(String currentAnime) {
        this.currentAnime = currentAnime;
    }

    public String getFavoriteAnime() {
        return favoriteAnime;
    }

    public void setFavoriteAnime(String favoriteAnime) {
        this.favoriteAnime = favoriteAnime;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }
}
