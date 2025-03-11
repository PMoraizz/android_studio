package com.example.barbearia;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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

                // Atualiza os campos de texto com os dados recuperados do SharedPreferences
                nome.setText(nomeUsuario);  // Exibe o nome do usuário
                email.setText(emailUsuario);  // Exibe o e-mail do usuário
                dataNasc.setText(dataNascimento);  // Exibe a data de nascimento do usuário
                telefone.setText(telefoneUsuario);  // Exibe o telefone do usuário
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.v("onCancelled", error.getMessage());
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerView2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Agendamento> agendamentosList = new ArrayList<>();
        agendamentosList.add(new Agendamento("Corte + Pigmentação", "Lohan", "08:00", "22/12/2025"));
        agendamentosList.add(new Agendamento("Corte + Pigmentação", "Coisa", "08:00", "22/12/2025"));
        agendamentosList.add(new Agendamento("Corte + Pigmentação", "Lohan", "08:00", "22/12/2025"));
        agendamentosList.add(new Agendamento("Corte + Pigmentação", "Coisa", "08:00", "22/12/2025"));
        agendamentosList.add(new Agendamento("Corte + Pigmentação", "Lohan", "08:00", "22/12/2025"));
        agendamentosList.add(new Agendamento("Corte + Pigmentação", "Coisa", "08:00", "22/12/2025"));
        agendamentosList.add(new Agendamento("Corte + Pigmentação", "Lohan", "08:00", "22/12/2025"));
        agendamentosList.add(new Agendamento("Corte + Pigmentação", "Coisa", "08:00", "22/12/2025"));


        AgendamentosAdapter agendamentosAdapter = new AgendamentosAdapter(this, agendamentosList);
        recyclerView.setAdapter(agendamentosAdapter);
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