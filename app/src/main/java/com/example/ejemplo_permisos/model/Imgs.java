package com.example.ejemplo_permisos.model;

public class Imgs {
    private String idImg;
    private String URL;

    public Imgs(String idImg, String URL) {
        this.idImg = idImg;
        this.URL = URL;
    }

    public String getIdImg() {
        return idImg;
    }

    public void setIdImg(String idImg) {
        this.idImg = idImg;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
}
