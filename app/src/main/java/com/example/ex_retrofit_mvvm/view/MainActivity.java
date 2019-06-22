package com.example.ex_retrofit_mvvm.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.ex_retrofit_mvvm.R;
import com.example.ex_retrofit_mvvm.adapter.HeroesAdapter;
import com.example.ex_retrofit_mvvm.model.Hero;
import com.example.ex_retrofit_mvvm.mvvm.HeroesViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    HeroesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        setUpRecyclerView();

        HeroesViewModel model = ViewModelProviders.of(this).get(HeroesViewModel.class);
        model.init();

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
    }

    private void setUpRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new HeroesAdapter(this);
        recyclerView.setAdapter(adapter);
    }
}
