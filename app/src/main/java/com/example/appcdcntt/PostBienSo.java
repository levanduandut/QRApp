package com.example.appcdcntt;

import com.google.gson.annotations.SerializedName;

public class PostBienSo {
    @SerializedName("token")
    private String token;
    @SerializedName("bienSo")
    private String bienSo;

    public PostBienSo(String token, String bienSo) {
        this.token = token;
        this.bienSo = bienSo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getBienSo() {
        return bienSo;
    }

    public void setBienSo(String bienSo) {
        this.bienSo = bienSo;
    }
}
