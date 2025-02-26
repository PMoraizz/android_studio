package com.example.aula04_02;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Habilita a funcionalidade de bordas de tela (Edge-to-Edge)
        EdgeToEdge.enable(this);

        // Define o layout da activity
        setContentView(R.layout.activity_home);

        // Recupera as referências para os campos de texto no layout
        TextView nome = findViewById(R.id.nomeUsuario);
        EditText email = findViewById(R.id.emailUsuario);
        EditText cpf = findViewById(R.id.cpfUsuario);
        EditText dataNasc = findViewById(R.id.dataNascimentoUsuario);
        EditText genero = findViewById(R.id.generoUsuario);
        EditText telefone = findViewById(R.id.telefoneUsuario);
        EditText endereco = findViewById(R.id.enderecoUsuario);


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
                String cpfUsuario = snapshot.child("cpf").getValue().toString();
                String dataNascimento = snapshot.child("nascimento").getValue().toString();
                String generoUsuario = snapshot.child("sexo").getValue().toString();
                String telefoneUsuario = snapshot.child("telefone").getValue().toString();
                String enderecoUsuario = snapshot.child("endereco").getValue().toString();

                // Atualiza os campos de texto com os dados recuperados do SharedPreferences
                nome.setText(nomeUsuario);  // Exibe o nome do usuário
                email.setText(emailUsuario);  // Exibe o e-mail do usuário
                cpf.setText(cpfUsuario);  // Exibe o CPF do usuário
                dataNasc.setText(dataNascimento);  // Exibe a data de nascimento do usuário
                genero.setText(generoUsuario);  // Exibe o gênero do usuário
                telefone.setText(telefoneUsuario);  // Exibe o telefone do usuário
                endereco.setText(enderecoUsuario);  // Exibe o endereço do usuário
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.v("onCancelled", error.getMessage());
            }
        });
        // Obtém os dados de usuário armazenados no SharedPreferences ou retorna um valor padrão

    }
}
