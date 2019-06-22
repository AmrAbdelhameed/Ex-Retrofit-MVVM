package com.example.ex_retrofit_mvvm.repository;

import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.ex_retrofit_mvvm.model.Hero;
import com.example.ex_retrofit_mvvm.network.APIService;
import com.example.ex_retrofit_mvvm.network.ApiUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HeroesRepository {
    private static HeroesRepository heroesRepository;
    private APIService apiService;

    private HeroesRepository() {
        apiService = ApiUtils.getAPIService();
    }

    public static HeroesRepository getInstance() {
        if (heroesRepository == null)
            heroesRepository = new HeroesRepository();
        return heroesRepository;
    }

    public MutableLiveData<List<Hero>> getHeroes() {
        final MutableLiveData<List<Hero>> listMutableLiveData = new MutableLiveData<>();
        apiService.getHeroes().enqueue(new Callback<List<Hero>>() {
            @Override
            public void onResponse(@NonNull Call<List<Hero>> call, @NonNull Response<List<Hero>> response) {
                if (response.isSuccessful())
                    listMutableLiveData.setValue(response.body());
                else
                    listMutableLiveData.setValue(null);
            }

            @Override
            public void onFailure(@NonNull Call<List<Hero>> call, @NonNull Throwable t) {
                listMutableLiveData.setValue(null);
            }
        });
        return listMutableLiveData;
    }
}
