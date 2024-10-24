package com.example.alcoolgasolina;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.text.NumberFormat;
import java.util.Locale;

public class Mascara implements TextWatcher {

    private EditText ed;

    public Mascara(EditText editText){
        this.ed = editText;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if(!charSequence.toString().equals("")){
            ed.removeTextChangedListener(this);
            String clean = charSequence.toString().replaceAll("[^\\d]", "");
            double parsed = Double.parseDouble(clean);
            String formattedd = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(parsed/100);

            ed.setText(formattedd);
            ed.setSelection(formattedd.length());
            ed.addTextChangedListener(this);
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

}
