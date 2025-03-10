package com.example.barbearia;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ItensDatailActivity extends AppCompatActivity {
    TextView txtTitulo, txtDescricao, txtTempo, txtPeco;
    ImageView imgItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_itens_datail);

        txtTitulo = findViewById(R.id.txtTitulo);
        txtDescricao = findViewById(R.id.txtDescricao);
        txtTempo = findViewById(R.id.txtTempo);
        txtPeco = findViewById(R.id.txtPreco);
        imgItem = findViewById(R.id.imgItem);

        Intent intent = getIntent();
        String titulo = intent.getStringExtra("titulo");
        String descricao = intent.getStringExtra("descricao");
        String tempo = intent.getStringExtra("tempo");
        String preco = intent.getStringExtra("preco");
        int img = intent.getIntExtra("img", -1);

        if(titulo != null && descricao != null && tempo != null && preco != null && img != -1){
            txtTitulo.setText(titulo);
            txtDescricao.setText(descricao);
            txtTempo.setText(tempo);
            txtPeco.setText(preco);
            imgItem.setImageResource(img);
        }

    }
}