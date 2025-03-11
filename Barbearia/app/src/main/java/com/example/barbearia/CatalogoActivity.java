package com.example.barbearia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CatalogoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_catalogo);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Itens> itensList = new ArrayList<>();
        itensList.add(new Itens("Barboterapia", "PROCEDIMENTO QUE TEM COMO OBJETIVO PROMOVER O RELAXAMENTO, ALÉM DE PREZAR PELA HIDRATAÇÃO DA PELE E, CLARO, UMA BARBA BEM FEITA.", "R$ 40,00", "30 min", R.drawable.barboterapia));
        itensList.add(new Itens("Corte", "DO CLASSICO AO MODERNO. EQUIPE ALTAMENTE CAPACITADA PARA MELHOR TE ATENDER E ORIENTAR NA ESCOLHA DO SEU CORTE.", "R$ 60,00", "30 min", R.drawable.corte));
        itensList.add(new Itens("Corte + Barba", "SERVIÇO DE CORTE DE CABELO, MAIS SERVIÇO DE BARBOTERAPIA.", "R$ 90,00", "60 min", R.drawable.corte_barba));
        itensList.add(new Itens("PENTEADO", "MODELAGEM E FIXAÇAO DE CABELO.", "R$ 30,00", "30 min", R.drawable.penteado));
        itensList.add(new Itens("PEZINHO", "O PÉZINHO VAI DEIXAR UMA APARÊNCIA DE CABELO CORTADO, É FEITO TODO O ACABAMENTO SEM FAZER O CORTE DO CABELO.", "R$ 15,00", "30 min", R.drawable.pezinho));
        itensList.add(new Itens("SOBRANCELHA", "LIMPEZA E ALINHAMENTO NA NAVALHA .", "R$ 20,00", "30 min", R.drawable.sobrancelha));
        itensList.add(new Itens("BARBA + PIGMENTAÇAO", "PROCEDIMENTO DE ALINHAMENTO DE BARBA, COM O PROCESSO DE CAMUFLAGEM DE FIOS BRANCOS.", "R$ 70,00", "60 min", R.drawable.barba_pigmentacao));
        itensList.add(new Itens("CORTE + PIGMENTAÇÃO", "SERVIÇO DE CORTE, PROCEDIMENTO DE BARBOTERAPIA E SELAGEM DE CABELO ( PROCEDIMENTO DE ALINHAMENTO DE FIOS E REDUÇAO DE VOLUME ).", "R$ 170,00", "120 min", R.drawable.corte_pigmentacao));

        ItensAdapter itensAdapter = new ItensAdapter(this, itensList);
        recyclerView.setAdapter(itensAdapter);

        itensAdapter.setOnItemClickListener(new ItensAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Itens itens) {
                Intent intent = new Intent(CatalogoActivity.this, ItensDatailActivity.class);
                intent.putExtra("titulo", itens.getTitulo());
                intent.putExtra("descricao", itens.getDescricao());
                intent.putExtra("img", itens.getImg());
                intent.putExtra("tempo", itens.getTempo());
                intent.putExtra("preco", itens.getPreco());

                startActivity(intent);
            }
        });
    }


    public void onClickEstabelecimento(View v) {
        Intent intent = new Intent(CatalogoActivity.this, BarbeariaActivity.class);
        startActivity(intent);

    }

    public void onClickProfissionais(View v) {
        Intent intent = new Intent(CatalogoActivity.this, ProfissionaisActivity.class);
        startActivity(intent);

    }

    public void onClickPerfil(View v) {
        Intent intent = new Intent(CatalogoActivity.this, PerfilActivity.class);
        startActivity(intent);

    }
}