package com.example.calculadora;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Declaração das variáveis para armazenar os TextViews e os valores da operação
    private TextView historicoTextView;
    private TextView contaTextView;
    private float valorAtual = 0; // Valor atual da operação
    private float resultado = 0;  // Resultado final da operação
    private String operadorAtual = ""; // Operador atual (+, -, *, /)
    private boolean novoOperador = true; // Flag para verificar se estamos iniciando uma nova operação

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializando as views (TextViews)
        historicoTextView = findViewById(R.id.historico);
        contaTextView = findViewById(R.id.conta);

        // Configuração dos botões numéricos de 0 a 9
        Button[] numButtons = new Button[] {
                findViewById(R.id.btn0), findViewById(R.id.btn1), findViewById(R.id.btn2),
                findViewById(R.id.btn3), findViewById(R.id.btn4), findViewById(R.id.btn5),
                findViewById(R.id.btn6), findViewById(R.id.btn7), findViewById(R.id.btn8), findViewById(R.id.btn9)
        };

        // Atribui um ouvinte de clique para cada botão numérico
        for (Button btn : numButtons) {
            btn.setOnClickListener(this::onNumberClick);
        }
    }

    // Método chamado quando um número é clicado
    private void onNumberClick(View v) {
        // Converte a visão para um botão
        Button button = (Button) v;
        // Se for um novo operador, limpa a tela
        if (novoOperador) {
            contaTextView.setText("");
            novoOperador = false;
        }
        // Adiciona o número clicado à tela de exibição
        String numero = button.getText().toString();
        contaTextView.append(numero);
    }

    // Método que trata o clique de um operador (+, -, *, /)
    @SuppressLint("SetTextI18n")
    private void onOperatorClick(String operador) {
        // Obtém o valor atual da tela
        valorAtual = Float.parseFloat(contaTextView.getText().toString());
        // Armazena o operador clicado
        operadorAtual = operador;
        // Atualiza o histórico com a operação atual
        historicoTextView.setText(valorAtual + " " + operadorAtual);
        // Marca que estamos prontos para digitar o próximo número
        novoOperador = true;
    }

    // Método para limpar a calculadora (botão AC)
    public void onClearClick(View view) {
        valorAtual = 0;
        resultado = 0;
        operadorAtual = "";
        // Limpa a tela
        contaTextView.setText("0");
        // Limpa o histórico
        historicoTextView.setText("");
        novoOperador = true;
    }

    // Método para alternar o sinal (positivo/negativo) do número atual
    public void onToggleSignClick(View view) {
        float valor = Float.parseFloat(contaTextView.getText().toString());
        // Inverte o sinal
        valor = -valor;
        // Atualiza a tela com o novo valor
        contaTextView.setText(String.valueOf(valor));
    }

    // Método para calcular o percentual do número atual
    public void onPercentageClick(View view) {
        float valor = Float.parseFloat(contaTextView.getText().toString());
        // Converte o número para percentual
        valor = valor / 100;
        // Atualiza a tela com o novo valor
        contaTextView.setText(String.valueOf(valor));
    }

    // Métodos para os operadores de divisão, multiplicação, subtração e adição
    public void onDevideClick(View view){ onOperatorClick("/"); }
    public void onMultiplyClick(View view) { onOperatorClick("*"); }
    public void onSubtractClick(View view){ onOperatorClick("-"); }
    public void onAddClick(View view){ onOperatorClick("+"); }

    // Método para calcular o resultado quando o botão de igual (=) é pressionado
    @SuppressLint("SetTextI18n")
    public void onEqualClick(View view) {
        // Obtém o número digitado
        float valorDigitado = Float.parseFloat(contaTextView.getText().toString());
        // Realiza a operação de acordo com o operador atual
        switch (operadorAtual) {
            case "+":
                resultado = valorAtual + valorDigitado;
                break;
            case "-":
                resultado = valorAtual - valorDigitado;
                break;
            case "*":
                resultado = valorAtual * valorDigitado;
                break;
            case "/":
                if (valorDigitado != 0) {
                    // Verifica se não é uma divisão por zero
                    resultado = valorAtual / valorDigitado;
                } else {
                    // Exibe mensagem de erro
                    contaTextView.setText("Erro: Div/0");
                    return;
                }
                break;
            default:
                // Se não houver operador, apenas exibe o valor digitado
                resultado = valorDigitado;
                break;
        }
        // Atualiza o histórico com a operação concluída
        historicoTextView.setText(historicoTextView.getText().toString() + " " + valorDigitado);
        // Exibe o resultado final na tela
        contaTextView.setText(String.valueOf(resultado));
        // Marca que a operação foi finalizada
        novoOperador = true;
    }

    // Método para adicionar o ponto decimal na tela, se ainda não houver um
    public void onDecimalClick(View view) {
        // Verifica se já existe um ponto decimal
        if (!contaTextView.getText().toString().contains(".")) {
            // Adiciona o ponto decimal
            contaTextView.append(".");
        }
    }
}
