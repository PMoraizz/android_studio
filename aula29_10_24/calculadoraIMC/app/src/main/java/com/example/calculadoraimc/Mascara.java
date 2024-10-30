package com.example.calculadoraimc;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.text.DecimalFormat;

public class Mascara implements TextWatcher {

    // Declaração da variável para o EditText a ser formatado
    private final EditText ed;
    private final TipoCampo tipoCampo;

    // Enumeração para definir o tipo de campo (Peso ou Altura)
    public enum TipoCampo {
        PESO, // Representa o campo de peso
        ALTURA // Representa o campo de altura
    }

    // Construtor que recebe o EditText e o tipo de campo
    public Mascara(EditText editText, TipoCampo tipoCampo) {
        this.ed = editText; // Inicializa o EditText
        this.tipoCampo = tipoCampo; // Define o tipo de campo
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        // Método não utilizado, mas necessário para a interface TextWatcher
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        // Verifica se o texto não está vazio
        if (!charSequence.toString().isEmpty()) {
            ed.removeTextChangedListener(this); // Remove o listener para evitar loops

            // Remove caracteres não numéricos e converte para double
            String clean = charSequence.toString().replaceAll("[^\\d]", "");
            double parsed = Double.parseDouble(clean);

            // Variável para armazenar o texto formatado
            String formatted;

            if (tipoCampo == TipoCampo.PESO) {
                // Formata para peso (ex: 70.50 kg)
                formatted = new DecimalFormat("#,##0.00").format(parsed / 100) + " kg";
            } else {
                // Formata para altura (ex: 1.75 m)
                formatted = new DecimalFormat("#0.00").format(parsed / 100) + " m";
            }

            // Define o texto formatado no EditText
            ed.setText(formatted);

            // Ajusta o cursor para o final do texto formatado
            ed.setSelection(formatted.length() - (tipoCampo == TipoCampo.PESO ? 3 : 2));

            // Readição do listener
            ed.addTextChangedListener(this);
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {
        // Método não utilizado, mas necessário para a interface TextWatcher
    }
}
