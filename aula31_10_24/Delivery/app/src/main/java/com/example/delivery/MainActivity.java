package com.example.delivery;

// Importação das bibliotecas necessárias para a funcionalidade da interface e interação com o sistema Android

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Declaração dos elementos da interface: EditText para nome, TextViews para mostrar quantidade e mensagens, e Buttons para ações
    private EditText edtNome;
    private TextView txtQuantidade, txtMsgUsuario;
    private Button btnMais, btnMenos, btnOrder;

    // Variável que controla a quantidade de itens selecionados
    private int qtd = 0;

    // Constantes que definem o valor máximo e mínimo de itens e o valor unitário de cada item
    public static final int MAXIMO = 10, MINIMO = 0, valor = 15;

    // Método onCreate() é chamado quando a atividade é criada
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Inicialização dos componentes da interface a partir do layout (usando findViewById)
        edtNome = findViewById(R.id.edtNome);
        txtQuantidade = findViewById(R.id.txtQuantidade);
        txtMsgUsuario = findViewById(R.id.txtMsgUsuario);
        btnMais = findViewById(R.id.btnMais);
        btnMenos = findViewById(R.id.btnMenos);
        btnOrder = findViewById(R.id.btnOrder);
    }

    // Método que é chamado quando o botão "Mais" é pressionado
    @SuppressLint("SetTextI18n")
    public void onClickMais(View view) {
        // Aumenta a quantidade de itens selecionados
        qtd += 1;

        // Se a quantidade atingir o limite máximo, o botão "Mais" é desabilitado
        if (qtd == MAXIMO) {
            btnMais.setEnabled(false);
            Toast.makeText(this, "Quantidade máxima por pedido atingida", Toast.LENGTH_SHORT).show();
        } else {
            // Se a quantidade ainda não atingiu o máximo, exibe a mensagem vazia e habilita o botão "Menos" e "Order"
            txtMsgUsuario.setText("");
            btnMenos.setEnabled(true);
            btnOrder.setEnabled(true);
        }
        // Atualiza o texto na TextView que mostra a quantidade de itens
        txtQuantidade.setText("" + qtd);
    }

    // Método que é chamado quando o botão "Menos" é pressionado
    @SuppressLint("SetTextI18n")
    public void onClickMenos(View view) {
        // Diminui a quantidade de itens selecionados
        qtd -= 1;

        // Se a quantidade atingir o valor mínimo, desabilita o botão "Menos" e "Order"
        if (qtd == MINIMO) {
            btnMenos.setEnabled(false);
            btnOrder.setEnabled(false);
            Toast.makeText(this, "Não é permitido menos que zero itens", Toast.LENGTH_SHORT).show();
        } else {
            // Se a quantidade não atingiu o mínimo, exibe a mensagem vazia e habilita o botão "Mais"
            txtMsgUsuario.setText("");
            btnMais.setEnabled(true);
        }
        // Atualiza o texto na TextView que mostra a quantidade de itens
        txtQuantidade.setText("" + qtd);
    }

    // Método que é chamado quando o botão "Order" é pressionado
    @SuppressLint("SetTextI18n")
    public void onClickOrder(View view) {
        // Exibe uma mensagem personalizada, mostrando o nome do usuário e o valor total da compra
        txtMsgUsuario.setText("Olá, " + edtNome.getText().toString() + " seu total é de $" + (qtd * valor) + "\nObrigado por Comprar com NOIS CRIA");
    }
}
