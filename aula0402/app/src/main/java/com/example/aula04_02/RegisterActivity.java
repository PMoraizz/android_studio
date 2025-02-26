package com.example.aula04_02;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
    // Declaração do botão de registrar
    Button botaoRegistrar;
    private FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference realtimeDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Habilita a funcionalidade de bordas de tela (Edge-to-Edge)
        EdgeToEdge.enable(this);

        // Define o layout da activity
        setContentView(R.layout.activity_register);

        // Vincula o botão de registrar com o botão da interface
        botaoRegistrar = findViewById(R.id.botaoRegistrar);

        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        realtimeDB = firebaseDatabase.getReference();

        // Chama o método para registrar o usuário
        registrarUsuario();
    }

    private void registrarUsuario() {
        // Configura o ouvinte de clique para o botão de registrar
        botaoRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtendo as referências dos campos de entrada do usuário
                EditText campoEmail = findViewById(R.id.campoEmail);
                EditText campoSenha = findViewById(R.id.campoSenha);
                EditText campoNome = findViewById(R.id.campoNome);
                EditText campoCpf = findViewById(R.id.campoCpf);
                EditText campoDataNascimento = findViewById(R.id.campoDataNascimento);
                RadioGroup radioGroupGenero = findViewById(R.id.radioGroupGenero);
                EditText campoTelefone = findViewById(R.id.campoTelefone);
                EditText campoEndereco = findViewById(R.id.campoEndereco);

                // Obtendo os valores digitados pelo usuário nos campos de entrada
                String emailUsuario = campoEmail.getText().toString();
                String senha = campoSenha.getText().toString();
                String nomeCompleto = campoNome.getText().toString();
                String cpf = campoCpf.getText().toString();
                String dataNascimento = campoDataNascimento.getText().toString();
                String telefone = campoTelefone.getText().toString();
                String endereco = campoEndereco.getText().toString();

                // Obtendo o valor selecionado no RadioGroup (para gênero)
                int selectedGenderId = radioGroupGenero.getCheckedRadioButtonId();
                RadioButton radioButtonGenero = findViewById(selectedGenderId);
                String genero = radioButtonGenero != null ? radioButtonGenero.getText().toString() : "";

                // Verificando se todos os campos foram preenchidos
                if (!emailUsuario.isEmpty() && !senha.isEmpty() && !nomeCompleto.isEmpty() &&
                        !cpf.isEmpty() && !dataNascimento.isEmpty() && !genero.isEmpty() &&
                        !telefone.isEmpty() && !endereco.isEmpty()) {

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

                                    User usr = new User(emailUsuario, nomeCompleto, cpf, dataNascimento, genero, telefone, endereco);

                                    usersRef.setValue(usr).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                // Exibindo uma mensagem de sucesso
                                                Toast.makeText(RegisterActivity.this, "Por favor, verifique seu email para finalizar o cadastro.", Toast.LENGTH_LONG).show();

                                                // Redirecionando o usuário para a tela de login (MainActivity)
                                                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                                startActivity(intent);

                                                // Finaliza a activity de registro para evitar que o usuário retorne a ela após o login
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
                    // Exibindo uma mensagem de erro caso algum campo não tenha sido preenchido
                    Toast.makeText(RegisterActivity.this, "Por favor, preencha todos os campos.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
