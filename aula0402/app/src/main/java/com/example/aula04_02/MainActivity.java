package com.example.aula04_02;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Declaração dos componentes da interface
    TextView linkCadastro;
    Button botaoLogin;

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

                // Recupera as credenciais salvas no SharedPreferences
                SharedPreferences preferencias = getSharedPreferences("dadosUsuario", MODE_PRIVATE);
                String usuarioSalvo = preferencias.getString("email", "");
                String senhaSalva = preferencias.getString("senha", "");
                String nomeCompleto = preferencias.getString("nome", "Usuário");

                // Verifica se as credenciais digitadas correspondem às salvas no SharedPreferences
                if (usuarioDigitado.equals(usuarioSalvo) && senhaDigitada.equals(senhaSalva)) {
                    // Se a autenticação for bem-sucedida, exibe uma mensagem de sucesso
                    Toast.makeText(MainActivity.this, "Autenticação bem-sucedida!", Toast.LENGTH_LONG).show();

                    // Cria uma nova Intent para redirecionar o usuário para a HomeActivity
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    intent.putExtra("nome", nomeCompleto); // Passa o nome do usuário para a HomeActivity
                    startActivity(intent);

                    // Finaliza a tela de login, impedindo que o usuário retorne a ela
                    finish();
                } else {
                    // Caso as credenciais não correspondam, exibe uma mensagem de erro
                    Toast.makeText(MainActivity.this, "Email e ou senha incorreto.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
