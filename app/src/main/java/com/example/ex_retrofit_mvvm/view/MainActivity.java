package com.example.ex_retrofit_mvvm.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.ex_retrofit_mvvm.R;
import com.example.ex_retrofit_mvvm.adapter.HeroesAdapter;
import com.example.ex_retrofit_mvvm.model.Hero;
import com.example.ex_retrofit_mvvm.mvvm.HeroesViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView {
    private RecyclerView recyclerView;
    private HeroesAdapter adapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        HeroesViewModel model = ViewModelProviders.of(this).get(HeroesViewModel.class);
        model.init(this);

        model.setHeroesRepository();
        model.getHeroesRepository().observe(this, new Observer<List<Hero>>() {
            @Override
            public void onChanged(@Nullable List<Hero> heroList) {
                if (heroList != null)
                    adapter.setHeroes(heroList);
                else
                    Log.d("response: ", "Failed");
            }
        });
    }

    private void init() {
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);

        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new HeroesAdapter(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }
}
