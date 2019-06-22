package com.example.ex_retrofit_mvvm.mvvm;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.ex_retrofit_mvvm.repository.HeroesRepository;
import com.example.ex_retrofit_mvvm.model.Hero;

import java.util.List;

public class HeroesViewModel extends ViewModel {
    private MutableLiveData<List<Hero>> listMutableLiveData;
    private HeroesRepository heroesRepository;

    public void init() {
        heroesRepository = HeroesRepository.getInstance();
    }

    public void setHeroesRepository() {
        listMutableLiveData = heroesRepository.getHeroes();
    }

    public LiveData<List<Hero>> getHeroesRepository() {
        return listMutableLiveData;
    }
}
