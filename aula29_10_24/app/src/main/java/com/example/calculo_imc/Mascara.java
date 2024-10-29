package com.example.calculo_imc;

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
        PESO,
        ALTURA
    }

    // Construtor que recebe o EditText e o tipo de campo
    public Mascara(EditText editText, TipoCampo tipoCampo) {
        this.ed = editText;
        this.tipoCampo = tipoCampo;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        // Verifica se o texto não está vazio
        if (!charSequence.toString().isEmpty()) {
            ed.removeTextChangedListener(this);

            String clean = charSequence.toString().replaceAll("[^\\d]", "");
            double parsed = Double.parseDouble(clean);

            String formatted;
            if (tipoCampo == TipoCampo.PESO) {
                // Formata para peso (ex: 70.50 kg)
                formatted = new DecimalFormat("#,##0.00").format(parsed / 100) + " kg";
            } else {
                // Formata para altura (ex: 1.75 m)
                formatted = new DecimalFormat("#0.00").format(parsed / 100) + " m";
            }

            ed.setText(formatted);
            ed.setSelection(formatted.length() - (tipoCampo == TipoCampo.PESO ? 3 : 2)); // Ajusta o cursor

            ed.addTextChangedListener(this);
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {}
}
