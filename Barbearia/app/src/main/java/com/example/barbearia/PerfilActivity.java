package com.example.barbearia;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PerfilActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_perfil);

        TextView nome = findViewById(R.id.txtNome);
        TextView email = findViewById(R.id.txtEmail);
        TextView dataNasc = findViewById(R.id.txtNascimento);
        TextView telefone = findViewById(R.id.txtTelefone);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

        String idUsuario = mAuth.getCurrentUser().getUid();

        DatabaseReference userRef = myRef.child("user").child(idUsuario);

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String nomeUsuario = snapshot.child("nome").getValue().toString();
                String emailUsuario = snapshot.child("email").getValue().toString();
                String dataNascimento = snapshot.child("nascimento").getValue().toString();
                String telefoneUsuario = snapshot.child("telefone").getValue().toString();

                nome.setText(nomeUsuario);
                email.setText(emailUsuario);
                dataNasc.setText(dataNascimento);
                telefone.setText(telefoneUsuario);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.v("onCancelled", error.getMessage());
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerView2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Agendamento> agendamentosList = new ArrayList<>();

        DatabaseReference agendamentoRef = FirebaseDatabase.getInstance()
                .getReference("user")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child("agendamentos");

        agendamentoRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                agendamentosList.clear();
                for (DataSnapshot agendamentoSnapshot : snapshot.getChildren()) {
                    String titulo = agendamentoSnapshot.child("titulo").getValue(String.class);
                    String profissional = agendamentoSnapshot.child("profissional").getValue(String.class);
                    String horario = agendamentoSnapshot.child("horario").getValue(String.class);
                    String data = agendamentoSnapshot.child("data").getValue(String.class);

                    if (titulo != null && profissional != null && horario != null && data != null) {
                        agendamentosList.add(new Agendamento(titulo, profissional, horario, data));
                        AgendamentosAdapter agendamentosAdapter = new AgendamentosAdapter(PerfilActivity.this, agendamentosList);
                        recyclerView.setAdapter(agendamentosAdapter);
                    }
                }
                Log.d("Agendamentos", "Lista atualizada: " + agendamentosList.size() + " agendamentos carregados.");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Agendamentos", "Erro ao carregar agendamentos: " + error.getMessage());
            }
        });
    }

    public void onClickProdutos(View v) {
        Intent intent = new Intent(PerfilActivity.this, CatalogoActivity.class);
        startActivity(intent);
    }

    public void onClickEstabelecimento(View v) {
        Intent intent = new Intent(PerfilActivity.this, BarbeariaActivity.class);
        startActivity(intent);
    }

    public void onClickProfissionais(View v) {
        Intent intent = new Intent(PerfilActivity.this, ProfissionaisActivity.class);
        startActivity(intent);
    }
}
