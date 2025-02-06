package com.example.aula04_02;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Habilita a funcionalidade de bordas de tela (Edge-to-Edge)
        EdgeToEdge.enable(this);

        // Define o layout da activity
        setContentView(R.layout.activity_home);

        // Recupera os dados armazenados no SharedPreferences
        // "dadosUsuario" é o nome do arquivo de preferências
        SharedPreferences preferencias = getSharedPreferences("dadosUsuario", MODE_PRIVATE);

        // Obtém os dados de usuário armazenados no SharedPreferences ou retorna um valor padrão
        String nomeUsuario = preferencias.getString("nome", "Nome não encontrado");
        String emailUsuario = preferencias.getString("email", "Email não encontrado");
        String cpfUsuario = preferencias.getString("cpf", "CPF não encontrado");
        String dataNascimento = preferencias.getString("dataNascimento", "Data de nascimento não encontrada");
        String generoUsuario = preferencias.getString("genero", "Gênero não informado");
        String telefoneUsuario = preferencias.getString("telefone", "Telefone não encontrado");
        String enderecoUsuario = preferencias.getString("endereco", "Endereço não encontrado");

        // Recupera as referências para os campos de texto no layout
        TextView nome = findViewById(R.id.nomeUsuario);
        EditText email = findViewById(R.id.emailUsuario);
        EditText cpf = findViewById(R.id.cpfUsuario);
        EditText dataNasc = findViewById(R.id.dataNascimentoUsuario);
        EditText genero = findViewById(R.id.generoUsuario);
        EditText telefone = findViewById(R.id.telefoneUsuario);
        EditText endereco = findViewById(R.id.enderecoUsuario);

        // Atualiza os campos de texto com os dados recuperados do SharedPreferences
        nome.setText(nomeUsuario);  // Exibe o nome do usuário
        email.setText(emailUsuario);  // Exibe o e-mail do usuário
        cpf.setText(cpfUsuario);  // Exibe o CPF do usuário
        dataNasc.setText(dataNascimento);  // Exibe a data de nascimento do usuário
        genero.setText(generoUsuario);  // Exibe o gênero do usuário
        telefone.setText(telefoneUsuario);  // Exibe o telefone do usuário
        endereco.setText(enderecoUsuario);  // Exibe o endereço do usuário
    }
}
