package com.example.ex_retrofit_mvvm.network;

import com.example.ex_retrofit_mvvm.model.Hero;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {
    @GET("marvel")
    Call<List<Hero>> getHeroes();
}