package com.example.ex_retrofit_mvvm.repository;

import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.ex_retrofit_mvvm.model.Hero;
import com.example.ex_retrofit_mvvm.network.APIService;
import com.example.ex_retrofit_mvvm.network.ApiUtils;
import com.example.ex_retrofit_mvvm.view.MainView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HeroesRepository {
    private static HeroesRepository heroesRepository;
    private static MainView mainView;
    private APIService apiService;
    private MutableLiveData<List<Hero>> listMutableLiveData;

    private HeroesRepository() {
        apiService = ApiUtils.getAPIService();
    }

    public static HeroesRepository getInstance(MainView _mainView) {
        if (heroesRepository == null)
            heroesRepository = new HeroesRepository();
        mainView = _mainView;
        return heroesRepository;
    }

    public MutableLiveData<List<Hero>> getHeroes() {
        if (mainView != null)
            mainView.showProgress();
        listMutableLiveData = new MutableLiveData<>();
        apiService.getHeroes().enqueue(new Callback<List<Hero>>() {
            @Override
            public void onResponse(@NonNull Call<List<Hero>> call, @NonNull Response<List<Hero>> response) {
                if (mainView != null) {
                    mainView.hideProgress();
                    if (response.isSuccessful())
                        listMutableLiveData.setValue(response.body());
                    else
                        listMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Hero>> call, @NonNull Throwable t) {
                if (mainView != null) {
                    mainView.hideProgress();
                    listMutableLiveData.setValue(null);
                }
            }
        });
        return listMutableLiveData;
    }
}
