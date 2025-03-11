package com.example.barbearia;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AgendamentoActivity extends AppCompatActivity {
    TextView txtTitulo, txtPreco, txtNomeP;
    ImageView imgP;
    TimePicker tp;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_agendamento);

        txtTitulo = findViewById(R.id.txtTitulo);
        txtPreco = findViewById(R.id.txtPreco);
        txtNomeP = findViewById(R.id.txtNomeP);
        imgP = findViewById(R.id.imgP);
        tp = findViewById(R.id.timePicker);

        
        tp.setIs24HourView(true);
        tp.setHour(8);
        tp.setMinute(0);

        tp.setOnTimeChangedListener((view, hourOfDay, minute) -> {
            if (minute < 30) {
                tp.setMinute(0);
            } else {
                tp.setMinute(30);
            }

            if (hourOfDay < 8) {
                tp.setHour(8);
                tp.setMinute(0);
            } else if (hourOfDay >= 18) {
                tp.setHour(17);
                tp.setMinute(30);
            }
        });


        Intent intent = getIntent();
        String titulo = intent.getStringExtra("titulo");
        String preco = intent.getStringExtra("preco");
        int img = intent.getIntExtra("imgP", -1);
        String profissional = intent.getStringExtra("profissional");

        if (titulo != null && preco != null && img != -1 && profissional != null ) {
            txtTitulo.setText(titulo);
            txtPreco.setText(preco);
            txtNomeP.setText(profissional);
            imgP.setImageResource(img);
        }

    }
}