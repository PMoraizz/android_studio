package com.example.aula11_12_24;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        this.recyclerView = findViewById(R.id.recyclerview);

        // Comentar
        int img1 = R.drawable.pizza_3_queijos;
        int img2 = R.drawable.pizza_calabresa;
        int img3 = R.drawable.pizza_queijo;
        int img4 = R.drawable.pizza_margherita;
        int img5 = R.drawable.pizza_pepperoni;
        int img6 = R.drawable.pizza_frango_requeijao;
        int img7 = R.drawable.pizza_cheddar;
        int img8 = R.drawable.pizza_4_queijos;
        int img9 = R.drawable.pizza_bauru;
        int img10 = R.drawable.pizza_catuperoni;
        int img11 = R.drawable.pizza_pao_alho;
        int img12 = R.drawable.pizza_frango_caipira;
        int img13 = R.drawable.pizza_cheddar_bacon;
        int img14 = R.drawable.pizza_cheddar_pepperoni;
        int img15 = R.drawable.pizza_corn_bacon;
        int img16 = R.drawable.pizza_presunto;
        int img17 = R.drawable.pizza_napolitana;

        // Comentar
        PizzaModel pizzaModal_1 = new PizzaModel(img1,"3 Queijos", "R$ 45.00",  "Queijo, requeijão, oregano e parmesão ralado.");
        PizzaModel pizzaModal_2 = new PizzaModel(img2,"Calabresa", "R$ 45.00",  "Queijo, calabresa e cebola, oregano.");
        PizzaModel pizzaModal_3 = new PizzaModel(img3,"Pizza de Queijo", "R$ 45.00",  "Queijo e orégano.");
        PizzaModel pizzaModal_4 = new PizzaModel(img4,"Margherita", "R$ 45.00",  "Queijo, tomate, oregano e manjericão.");
        PizzaModel pizzaModal_5 = new PizzaModel(img5,"Pepperoni", "R$ 45.00",  "Queijo, oregano e pepperoni.");
        PizzaModel pizzaModal_6 = new PizzaModel(img6,"Frango com Requeijão Especial", "R$ 45.00",  "Frango desfiado, cebola, oregano e requeijão");
        PizzaModel pizzaModal_7 = new PizzaModel(img7,"Cheddar", "R$ 45.00",  "Queijo, molho sabor queijo cheddar e orégano.");
        PizzaModel pizzaModal_8 = new PizzaModel(img8,"4 Queijos", "R$ 45.00",  "Queijo, requeijão, gorgonzola, oregano e parmesão ralado");
        PizzaModel pizzaModal_9 = new PizzaModel(img9,"Bauru", "R$ 45.00",  "Queijo, presunto, requeijão, oregano e tomate.");
        PizzaModel pizzaModal_10 = new PizzaModel(img10,"Catuperoni", "R$ 45.00",  "Queijo, pepperoni, requeijão, oregano e parmesão ralado.");
        PizzaModel pizzaModal_11 = new PizzaModel(img11,"Pizza Pão de Alho", "R$ 45.00",  "Queijo, pão ciabatta, pasta de alho, parmesão ralado e orégano.");
        PizzaModel pizzaModal_12 = new PizzaModel(img12,"Frango Caipira", "R$ 45.00",  "Queijo, frango desfiado, milho, catupiry e orégano.");
        PizzaModel pizzaModal_13 = new PizzaModel(img13,"Cheddar & Bacon", "R$ 45.00",  "Queijo, molho sabor queijo cheddar, bacon e orégano.");
        PizzaModel pizzaModal_14 = new PizzaModel(img14,"Cheddar & Pepperoni", "R$ 45.00",  "Queijo, molho sabor queijo cheddar, pepperoni e orégano.");
        PizzaModel pizzaModal_15 = new PizzaModel(img15,"Corn & Bacon", "R$ 45.00",  "Queijo, bacon, oregano e milho.");
        PizzaModel pizzaModal_16 = new PizzaModel(img16,"Presunto", "R$ 45.00",  "Queijo, oregano e presunto.");
        PizzaModel pizzaModal_17 = new PizzaModel(img17,"Napolitana", "R$ 45.00",  "Queijo, tomate, oregano e parmesão ralado.");

        // Comentar
        ArrayList<PizzaModel> pizzaModals = new ArrayList<>();
        pizzaModals.add(pizzaModal_1);
        pizzaModals.add(pizzaModal_2);
        pizzaModals.add(pizzaModal_3);
        pizzaModals.add(pizzaModal_4);
        pizzaModals.add(pizzaModal_5);
        pizzaModals.add(pizzaModal_6);
        pizzaModals.add(pizzaModal_7);
        pizzaModals.add(pizzaModal_8);
        pizzaModals.add(pizzaModal_9);
        pizzaModals.add(pizzaModal_10);
        pizzaModals.add(pizzaModal_11);
        pizzaModals.add(pizzaModal_12);
        pizzaModals.add(pizzaModal_13);
        pizzaModals.add(pizzaModal_14);
        pizzaModals.add(pizzaModal_15);
        pizzaModals.add(pizzaModal_16);
        pizzaModals.add(pizzaModal_17);

        // Comentar
        RecyclerView.Adapter<PizzaAdapter.ViewHolder> adapter = new PizzaAdapter(pizzaModals);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        // Comentar
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}