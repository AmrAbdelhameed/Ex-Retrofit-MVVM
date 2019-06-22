package com.example.ex_retrofit_mvvm.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ex_retrofit_mvvm.R;
import com.example.ex_retrofit_mvvm.model.Hero;

import java.util.ArrayList;
import java.util.List;

public class HeroesAdapter extends RecyclerView.Adapter<HeroesAdapter.HeroViewHolder> {
    private Context context;
    private List<Hero> heroes;

    public HeroesAdapter(Context context) {
        this.context = context;
        heroes = new ArrayList<>();
    }

    public void setHeroes(List<Hero> heroes) {
        this.heroes = heroes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HeroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.hero_item, parent, false);
        return new HeroViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HeroViewHolder holder, int position) {
        Hero hero = heroes.get(position);
        holder.bind(hero);
    }

    @Override
    public int getItemCount() {
        return heroes.size();
    }

    class HeroViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        HeroViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
        }

        void bind(Hero hero) {
            Glide.with(context)
                    .load(hero.getImageurl())
                    .into(imageView);
            textView.setText(hero.getName());
        }
    }
}