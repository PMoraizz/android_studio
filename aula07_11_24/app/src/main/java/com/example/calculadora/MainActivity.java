package com.example.calculadora;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView historicoTextView;
    private TextView contaTextView;
    private double valorAtual = 0;
    private double resultado = 0;
    private String operadorAtual = "";
    private boolean novoOperador = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        historicoTextView = findViewById(R.id.historico);
        contaTextView = findViewById(R.id.conta);

        // Configuração de cada botão numérico
        Button[] numButtons = new Button[] {
                findViewById(R.id.btn0), findViewById(R.id.btn1), findViewById(R.id.btn2),
                findViewById(R.id.btn3), findViewById(R.id.btn4), findViewById(R.id.btn5),
                findViewById(R.id.btn6), findViewById(R.id.btn7), findViewById(R.id.btn8), findViewById(R.id.btn9)
        };

        for (Button btn : numButtons) {
            btn.setOnClickListener(this::onNumberClick);
        }

        // Configuração de cada botão de operação
        findViewById(R.id.btnAdicao).setOnClickListener(v -> onOperatorClick("+"));
        findViewById(R.id.btnSubitracao).setOnClickListener(v -> onOperatorClick("-"));
        findViewById(R.id.btnMultiplicacao).setOnClickListener(v -> onOperatorClick("*"));
        findViewById(R.id.btnDivisao).setOnClickListener(v -> onOperatorClick("/"));
        findViewById(R.id.btnIgual).setOnClickListener(v -> onEqualClick());
        findViewById(R.id.btnAC).setOnClickListener(v -> onClearClick());
        findViewById(R.id.btnDecimal).setOnClickListener(v -> onDecimalClick());
        findViewById(R.id.btnInverterSinal).setOnClickListener(v -> onToggleSignClick());
        findViewById(R.id.btnPorcentagem).setOnClickListener(v -> onPercentageClick());
    }

    private void onNumberClick(View v) {
        Button button = (Button) v;
        if (novoOperador) {
            contaTextView.setText("");
            novoOperador = false;
        }
        String numero = button.getText().toString();
        contaTextView.append(numero);
    }

    private void onOperatorClick(String operador) {
        valorAtual = Double.parseDouble(contaTextView.getText().toString());
        operadorAtual = operador;
        historicoTextView.setText(valorAtual + " " + operadorAtual);
        novoOperador = true;
    }

    private void onEqualClick() {
        double valorDigitado = Double.parseDouble(contaTextView.getText().toString());
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
                    resultado = valorAtual / valorDigitado;
                } else {
                    contaTextView.setText("Erro: Div/0");
                    return;
                }
                break;
            default:
                resultado = valorDigitado;
                break;
        }
        historicoTextView.setText(historicoTextView.getText().toString() + " " + valorDigitado + " =");
        contaTextView.setText(String.valueOf(resultado));
        novoOperador = true;
    }

    private void onClearClick() {
        valorAtual = 0;
        resultado = 0;
        operadorAtual = "";
        contaTextView.setText("");
        historicoTextView.setText("");
        novoOperador = true;
    }

    private void onDecimalClick() {
        if (!contaTextView.getText().toString().contains(".")) {
            contaTextView.append(".");
        }
    }

    private void onToggleSignClick() {
        double valorAtualDisplay = Double.parseDouble(contaTextView.getText().toString());
        valorAtualDisplay = -valorAtualDisplay;
        contaTextView.setText(String.valueOf(valorAtualDisplay));
    }

    private void onPercentageClick() {
        double valorAtualDisplay = Double.parseDouble(contaTextView.getText().toString());
        valorAtualDisplay = valorAtualDisplay / 100;
        contaTextView.setText(String.valueOf(valorAtualDisplay));
    }
}
