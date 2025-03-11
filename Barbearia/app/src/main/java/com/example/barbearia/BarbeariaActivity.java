package com.example.barbearia;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BarbeariaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_barbearia);

        ImageButton whatsappButton = findViewById(R.id.imageButton);
        ImageButton instagramButton = findViewById(R.id.imageButton2);
        ImageButton facebookButton = findViewById(R.id.imageButton3);

        whatsappButton.setOnClickListener(v -> openLink("https://wa.me/3189282048"));
        instagramButton.setOnClickListener(v -> openLink("https://www.instagram.com/baianobarbershop"));
        facebookButton.setOnClickListener(v -> openLink("https://www.facebook.com/BarbeariaBaianoBarberShop"));
    }

    // Método para abrir links externos
    private void openLink(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }

    public void onClickProdutos(View v) {
        Intent intent = new Intent(BarbeariaActivity.this, CatalogoActivity.class);
        startActivity(intent);

    }

    public void onClickProfissionais(View v) {
        Intent intent = new Intent(BarbeariaActivity.this, ProfissionaisActivity.class);
        startActivity(intent);

    }

    public void onClickPerfil(View v) {
        Intent intent = new Intent(BarbeariaActivity.this, PerfilActivity.class);
        startActivity(intent);

    }
}