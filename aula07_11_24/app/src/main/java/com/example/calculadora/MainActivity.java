package com.example.calculadora;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView historicoTextView;
    private TextView contaTextView;
    private float valorAtual = 0;
    private float resultado = 0;
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

    @SuppressLint("SetTextI18n")
    private void onOperatorClick(String operador) {
        valorAtual = Float.parseFloat(contaTextView.getText().toString());
        operadorAtual = operador;
        historicoTextView.setText(valorAtual + " " + operadorAtual);
        novoOperador = true;
    }

    public void onClearClick(View view) {
        valorAtual = 0;
        resultado = 0;
        operadorAtual = "";
        contaTextView.setText("0");
        historicoTextView.setText("");
        novoOperador = true;
    }

    public void onToggleSignClick(View view) {
        float valor = Float.parseFloat(contaTextView.getText().toString());
        valor = -valor;
        contaTextView.setText(String.valueOf(valor));
    }

    public void onPercentageClick(View view) {
        float valor = Float.parseFloat(contaTextView.getText().toString());
        valor = valor / 100;
        contaTextView.setText(String.valueOf(valor));
    }

    public void onDevideClick(View view){ onOperatorClick("/"); }

    public void onMultiplyClick(View view) { onOperatorClick("*"); }

    public void onSubtractClick(View view){ onOperatorClick("-"); }

    public void onAddClick(View view){ onOperatorClick("+"); }

    @SuppressLint("SetTextI18n")
    public void onEqualClick(View view) {
        float valorDigitado = Float.parseFloat(contaTextView.getText().toString());
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
        historicoTextView.setText(historicoTextView.getText().toString() + " " + valorDigitado);
        contaTextView.setText(String.valueOf(resultado));
        novoOperador = true;
    }

    public void onDecimalClick(View view) {
        if (!contaTextView.getText().toString().contains(".")) {
            contaTextView.append(".");
        }
    }


}
