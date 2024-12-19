package com.example.aula11_12_24;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PizzaAdapter extends RecyclerView.Adapter<PizzaAdapter.ViewHolder> {

    ArrayList<PizzaModel> pizzaModals;

    // Comentar
    public PizzaAdapter(ArrayList<PizzaModel> pizzaModals) {
        this.pizzaModals = pizzaModals;
    }

    // Comentar
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView pizza_image;
        TextView pizza_name;
        TextView pizza_value;
        TextView pizza_ingredients;

        // Comentar
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            pizza_image = itemView.findViewById(R.id.pizza_image);
            pizza_name = itemView.findViewById(R.id.pizza_name);
            pizza_value = itemView.findViewById(R.id.pizza_value);
            pizza_ingredients = itemView.findViewById(R.id.pizza_ingredients);
        }
    }

    @NonNull
    @Override
    public PizzaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull PizzaAdapter.ViewHolder holder, int position) {

        // Comentar
        PizzaModel pizzaModel = pizzaModals.get(position);

        // Comentar
        holder.pizza_image.setImageResource(pizzaModel.getPizzaImage());
        holder.pizza_name.setText(pizzaModel.getPizzaName());
        holder.pizza_value.setText(pizzaModel.getPizzaValue());
        holder.pizza_ingredients.setText(pizzaModel.getPizzaIngredients());
    }

    @Override
    public int getItemCount() {
        return pizzaModals.size();
    }
}
