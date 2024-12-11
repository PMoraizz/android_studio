package com.example.aula03_12_24;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
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
        int img1 = R.drawable.f1;
        int img2 = R.drawable.f2;
        int img3 = R.drawable.f3;

        // Comentar
        RocketModel rocketModal_1 = new RocketModel(img1,"falcon 1", "06/11/2024", true, "satellite");
        RocketModel rocketModal_2 = new RocketModel(img2,"falcon 2", "06/11/2024", true, "satellite");
        RocketModel rocketModal_3 = new RocketModel(img3,"falcon 3", "06/11/2024", false, "satellite");
        RocketModel rocketModal_4 = new RocketModel(img1,"falcon 4", "06/11/2024", false, "satellite");
        RocketModel rocketModal_5 = new RocketModel(img2,"falcon 5", "06/11/2024", false, "satellite");
        RocketModel rocketModal_6 = new RocketModel(img3,"falcon 6", "06/11/2024", false, "satellite");
        RocketModel rocketModal_7 = new RocketModel(img1,"falcon 7", "06/11/2024", false, "satellite");
        RocketModel rocketModal_8 = new RocketModel(img2,"falcon 8", "06/11/2024", false, "satellite");
        RocketModel rocketModal_9 = new RocketModel(img3,"falcon 9", "06/11/2024", false, "satellite");

        // Comentar
        ArrayList<RocketModel> rocketModals = new ArrayList<>();
        rocketModals.add(rocketModal_1);
        rocketModals.add(rocketModal_2);
        rocketModals.add(rocketModal_3);
        rocketModals.add(rocketModal_4);
        rocketModals.add(rocketModal_5);
        rocketModals.add(rocketModal_6);
        rocketModals.add(rocketModal_7);
        rocketModals.add(rocketModal_8);
        rocketModals.add(rocketModal_9);

        // Comentar
        RecyclerView.Adapter<RocketAdapter.ViewHolder> adapter = new RocketAdapter(rocketModals);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        // Comentar
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }
}