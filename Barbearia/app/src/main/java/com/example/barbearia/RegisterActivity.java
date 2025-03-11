package com.example.barbearia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    Button botaoRegistrar;
    private FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference realtimeDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        botaoRegistrar = findViewById(R.id.botaoRegistrar);

        EditText campoCpf = findViewById(R.id.campoCpf);
        campoCpf.addTextChangedListener(MaskEditUtil.mask(campoCpf, "###.###.###-##"));

        EditText campoTelefone = findViewById(R.id.campoTelefone);
        campoTelefone.addTextChangedListener(MaskEditUtil.mask(campoTelefone, "(##) #####-####"));

        EditText campoDataNascimento = findViewById(R.id.campoDataNascimento);
        campoDataNascimento.addTextChangedListener(MaskEditUtil.mask(campoDataNascimento, "##/##/####"));


        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        realtimeDB = firebaseDatabase.getReference();

        registrarUsuario();
    }

    private void registrarUsuario() {
        botaoRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText campoEmail = findViewById(R.id.campoEmail);
                EditText campoSenha = findViewById(R.id.campoSenha);
                EditText campoNome = findViewById(R.id.campoNome);
                EditText campoCpf = findViewById(R.id.campoCpf);
                EditText campoDataNascimento = findViewById(R.id.campoDataNascimento);
                EditText campoTelefone = findViewById(R.id.campoTelefone);

                String emailUsuario = campoEmail.getText().toString();
                String senha = campoSenha.getText().toString();
                String nomeCompleto = campoNome.getText().toString();
                String cpf = campoCpf.getText().toString();
                String dataNascimento = campoDataNascimento.getText().toString();
                String telefone = campoTelefone.getText().toString();


                if (!emailUsuario.isEmpty() && !senha.isEmpty() && !nomeCompleto.isEmpty() &&
                        !cpf.isEmpty() && !dataNascimento.isEmpty() && !telefone.isEmpty()) {

                    if (senha.length() >= 6) {
                        mAuth.createUserWithEmailAndPassword(emailUsuario, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {

                                    FirebaseUser user = mAuth.getCurrentUser();

                                    if (user != null)
                                        user.sendEmailVerification();

                                    String user_id = mAuth.getCurrentUser().getUid();
                                    DatabaseReference usersRef = realtimeDB.child("user").child(user_id);

                                    User usr = new User(emailUsuario, nomeCompleto, cpf, dataNascimento, telefone);

                                    usersRef.setValue(usr).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(RegisterActivity.this, "Por favor, verifique seu email para finalizar o cadastro.", Toast.LENGTH_LONG).show();

                                                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                                startActivity(intent);

                                                finish();
                                            } else {
                                                Toast.makeText(RegisterActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });
                                } else {
                                    Toast.makeText(RegisterActivity.this, task.getException().toString(), Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    } else {
                        Toast.makeText(RegisterActivity.this, "Senha precisa conter pelo menos 6 caracteres", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "Por favor, preencha todos os campos.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
