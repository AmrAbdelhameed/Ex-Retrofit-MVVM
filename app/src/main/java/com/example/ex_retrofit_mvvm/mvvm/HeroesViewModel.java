package com.example.ex_retrofit_mvvm.mvvm;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.ex_retrofit_mvvm.model.Hero;
import com.example.ex_retrofit_mvvm.repository.HeroesRepository;
import com.example.ex_retrofit_mvvm.view.MainView;

import java.util.List;

public class HeroesViewModel extends ViewModel {
    private MutableLiveData<List<Hero>> listMutableLiveData;
    private HeroesRepository heroesRepository;

    public void init(MainView mainView) {
        heroesRepository = HeroesRepository.getInstance(mainView);
    }

    public void setHeroesRepository() {
        listMutableLiveData = heroesRepository.getHeroes();
    }

    public LiveData<List<Hero>> getHeroesRepository() {
        return listMutableLiveData;
    }
}
