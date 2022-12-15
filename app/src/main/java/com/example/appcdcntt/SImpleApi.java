package com.example.appcdcntt;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SImpleApi {
    @POST("/api/users")
    Call<PostBienSo> createPost(@Body PostBienSo dataModal);
}
