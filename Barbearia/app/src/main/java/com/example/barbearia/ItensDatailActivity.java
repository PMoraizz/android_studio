package com.example.barbearia;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ItensDatailActivity extends AppCompatActivity {
    TextView txtTitulo, txtDescricao, txtTempo, txtPeco, txtNomeP1, txtNomeP2, txtNomeP3;
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
        txtNomeP1 = findViewById(R.id.txtNomeP1);
        txtNomeP2 = findViewById(R.id.txtNomeP2);
        txtNomeP3 = findViewById(R.id.txtNomeP3);

        Intent intent = getIntent();
        String titulo = intent.getStringExtra("titulo");
        String descricao = intent.getStringExtra("descricao");
        String tempo = intent.getStringExtra("tempo");
        String preco = intent.getStringExtra("preco");
        int img = intent.getIntExtra("img", -1);


        if (titulo != null && descricao != null && tempo != null && preco != null && img != -1) {
            txtTitulo.setText(titulo);
            txtDescricao.setText(descricao);
            txtTempo.setText(tempo);
            txtPeco.setText(preco);
            imgItem.setImageResource(img);
        }
    }

    public void ll1(View v) {
        Intent intent = new Intent(ItensDatailActivity.this, AgendamentoActivity.class);
        intent.putExtra("profissional", txtNomeP1.getText().toString());
        intent.putExtra("preco", txtPeco.getText().toString());
        intent.putExtra("titulo", txtTitulo.getText().toString());
        intent.putExtra("imgP", R.drawable.barbeiro1);
        startActivity(intent);
    }

    public void ll2(View v) {
        Intent intent = new Intent(ItensDatailActivity.this, AgendamentoActivity.class);
        intent.putExtra("profissional", txtNomeP2.getText().toString());
        intent.putExtra("preco", txtPeco.getText().toString());
        intent.putExtra("titulo", txtTitulo.getText().toString());
        intent.putExtra("imgP", R.drawable.barbeiro2);
        startActivity(intent);
    }

    public void ll3(View v) {
        Intent intent = new Intent(ItensDatailActivity.this, AgendamentoActivity.class);
        intent.putExtra("profissional", txtNomeP3.getText().toString());
        intent.putExtra("preco", txtPeco.getText().toString());
        intent.putExtra("titulo", txtTitulo.getText().toString());
        intent.putExtra("imgP", R.drawable.barbeiro3);
        startActivity(intent);
    }

}