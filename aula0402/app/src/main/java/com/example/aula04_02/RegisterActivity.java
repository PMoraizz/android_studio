package com.example.aula04_02;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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

public class RegisterActivity extends AppCompatActivity {
    // Declaração do botão de registrar
    Button botaoRegistrar;
    private FirebaseAuth mAuth;

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

                    mAuth.createUserWithEmailAndPassword(emailUsuario, senha);


                    FirebaseUser user = mAuth.getCurrentUser();
                    user.sendEmailVerification();

                    // Salvando os dados no SharedPreferences para persistência
                    SharedPreferences preferencias = getSharedPreferences("dadosUsuario", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferencias.edit();

                    editor.putString("nome", nomeCompleto);
                    editor.putString("cpf", cpf);
                    editor.putString("dataNascimento", dataNascimento);
                    editor.putString("genero", genero);
                    editor.putString("telefone", telefone);
                    editor.putString("endereco", endereco);

                    // Aplica as mudanças no SharedPreferences
                    editor.apply();

                    // Exibindo uma mensagem de sucesso
                    Toast.makeText(RegisterActivity.this, nomeCompleto + " registrado com sucesso!", Toast.LENGTH_LONG).show();

                    // Redirecionando o usuário para a tela de login (MainActivity)
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);

                    // Finaliza a activity de registro para evitar que o usuário retorne a ela após o login
                    finish();


                } else {
                    // Exibindo uma mensagem de erro caso algum campo não tenha sido preenchido
                    Toast.makeText(RegisterActivity.this, "Por favor, preencha todos os campos.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
