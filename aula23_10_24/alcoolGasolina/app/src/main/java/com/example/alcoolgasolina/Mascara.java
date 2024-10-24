package com.example.alcoolgasolina;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.text.NumberFormat;
import java.util.Locale;

public class Mascara implements TextWatcher {
    // Declaração da variável para o EditText a ser formatado
    private final EditText ed;

    // Construtor que recebe o EditText e o inicializa
    public Mascara(EditText editText) {
        this.ed = editText;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {/*Método vazio, não utilizado neste caso*/}

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        // Verifica se o texto não está vazio
        if (!charSequence.toString().isEmpty()) {
            // Remove o listener para evitar chamadas recursivas
            ed.removeTextChangedListener(this);

            // Limpa os caracteres não numéricos do texto
            String clean = charSequence.toString().replaceAll("[^\\d]", "");
            // Converte a string limpa para um número double
            double parsed = Double.parseDouble(clean);
            // Formata o número como moeda (real brasileiro)
            String formatted = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(parsed / 100);

            // Define o texto formatado de volta no EditText
            ed.setText(formatted);

            // Move o cursor para o final do texto formatado
            ed.setSelection(formatted.length());

            // Adiciona o listener novamente
            ed.addTextChangedListener(this);
        }
    }

    @Override
    public void afterTextChanged(Editable editable) { /*Método vazio, não utilizado neste caso*/ }
}
