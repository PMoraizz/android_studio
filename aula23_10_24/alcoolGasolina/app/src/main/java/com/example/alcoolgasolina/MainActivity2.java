package com.example.alcoolgasolina;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {
    static EditText edtGasolina, edtAlcool;
    static ImageView imgResp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        edtGasolina = findViewById(R.id.edtGasolina);
        edtAlcool = findViewById(R.id.edtAlcool);
        imgResp = findViewById(R.id.img2);

        edtGasolina.addTextChangedListener(new Mascara(edtGasolina));
        edtAlcool.addTextChangedListener(new Mascara(edtAlcool));

    }

    public void onClick(View view) {
        double gasolina, alcool, op;
        try {
            gasolina = Double.parseDouble(edtGasolina.getText().toString().replaceAll("[^\\d]", ""));
            alcool = Double.parseDouble(edtAlcool.getText().toString().replaceAll("[^\\d]", ""));

            op = gasolina/alcool;

            if(alcool == 0.0 || gasolina == 0.0){
                imgResp.setImageResource(R.drawable.ic_launcher_foreground);
            } else {
                if(op >= 0.7){
                    imgResp.setImageResource(R.drawable.alcool);
                } else {
                    imgResp.setImageResource(R.drawable.gasolina);
                }
            }
        } catch (Exception e){

        }
    }
}


