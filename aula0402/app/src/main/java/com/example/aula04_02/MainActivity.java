package com.example.aula04_02;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    // Declaração dos componentes da interface
    TextView linkCadastro;
    Button botaoLogin;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Habilita a funcionalidade de bordas de tela (Edge-to-Edge)
        EdgeToEdge.enable(this);

        // Define o layout da activity
        setContentView(R.layout.activity_main);

        // Vincula os componentes da interface (link de cadastro e botão de login)
        linkCadastro = findViewById(R.id.linkCadastro);
        botaoLogin = findViewById(R.id.botaoLogin);

        mAuth = FirebaseAuth.getInstance();

        // Configura o link para a tela de cadastro
        configurarLinkCadastro();

        // Configura a verificação das credenciais de login
        verificarCredenciaisUsuario();
    }

    // Método que configura o comportamento do link de cadastro
    private void configurarLinkCadastro() {
        linkCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Quando o link for clicado, redireciona o usuário para a tela de cadastro
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    // Método que verifica as credenciais do usuário durante o login
    private void verificarCredenciaisUsuario() {
        botaoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Recupera os campos de usuário e senha digitados
                EditText campoUsuario = findViewById(R.id.campoUsuario);
                EditText campoSenha = findViewById(R.id.campoSenha);

                // Obtém os valores digitados pelo usuário nos campos de entrada
                String usuarioDigitado = campoUsuario.getText().toString();
                String senhaDigitada = campoSenha.getText().toString();

                // Verifica se as credenciais digitadas correspondem às salvas no SharedPreferences
                if (!usuarioDigitado.isEmpty() && !senhaDigitada.isEmpty()) {
                    mAuth.signInWithEmailAndPassword(usuarioDigitado, senhaDigitada).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                FirebaseUser user = mAuth.getCurrentUser();
                                if (user.isEmailVerified()) {
                                    // Se a autenticação for bem-sucedida, exibe uma mensagem de sucesso
                                    Toast.makeText(MainActivity.this, "Autenticação bem-sucedida!", Toast.LENGTH_LONG).show();

                                    // Cria uma nova Intent para redirecionar o usuário para a HomeActivity
                                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                                    startActivity(intent);

                                    // Finaliza a tela de login, impedindo que o usuário retorne a ela
                                    finish();
                                } else {
                                    Toast.makeText(MainActivity.this, "Email nao verificado", Toast.LENGTH_LONG).show();
                                }

                            } else {
                                Log.v("Sign in Failed ", task.getException().toString());
                                Toast.makeText(MainActivity.this, "Email e ou senha incorreto.", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                } else {
                    // Caso as credenciais não correspondam, exibe uma mensagem de erro
                    Toast.makeText(MainActivity.this, "Por favor, preencha todos os campos.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
