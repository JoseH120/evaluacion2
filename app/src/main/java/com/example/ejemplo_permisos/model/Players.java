package com.example.ejemplo_permisos.model;

public class Players {
    private String idPlayer;
    private String nickNamePlayer;
    private String idImgs;
    private String scorePlayer;

    public Players(String idPlayer, String nickNamePlayer, String idImgs, String scorePlayer) {
        this.idPlayer = idPlayer;
        this.nickNamePlayer = nickNamePlayer;
        this.idImgs = idImgs;
        this.scorePlayer = scorePlayer;
    }

    public String getIdPlayer() {
        return idPlayer;
    }

    public void setIdPlayer(String idPlayer) {
        this.idPlayer = idPlayer;
    }

    public String getNickNamePlayer() {
        return nickNamePlayer;
    }

    public void setNickNamePlayer(String nickNamePlayer) {
        this.nickNamePlayer = nickNamePlayer;
    }

    public String getIdImgs() {
        return idImgs;
    }

    public void setIdImgs(String idImgs) {
        this.idImgs = idImgs;
    }

    public String getScorePlayer() {
        return scorePlayer;
    }

    public void setScorePlayer(String scorePlayer) {
        this.scorePlayer = scorePlayer;
    }
}
