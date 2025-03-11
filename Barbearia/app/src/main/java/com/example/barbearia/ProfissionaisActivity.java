package com.example.barbearia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ProfissionaisActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profissionais);

    }

    public void onClickProdutos(View v) {
        Intent intent = new Intent(ProfissionaisActivity.this, CatalogoActivity.class);
        startActivity(intent);

    }

    public void onClickEstabelecimento(View v) {
        Intent intent = new Intent(ProfissionaisActivity.this, BarbeariaActivity.class);
        startActivity(intent);

    }

    public void onClickPerfil(View v) {
        Intent intent = new Intent(ProfissionaisActivity.this, PerfilActivity.class);
        startActivity(intent);

    }
}