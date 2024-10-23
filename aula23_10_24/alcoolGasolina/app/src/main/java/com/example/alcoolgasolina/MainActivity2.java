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
    }

    public void onClick(View view) {
        edtGasolina = findViewById(R.id.edtGasolina);
        edtAlcool = findViewById(R.id.edtAlcool);
        imgResp = findViewById(R.id.img2);

        double gasolina, alcool, op;
        gasolina = Double.parseDouble(edtGasolina.getText().toString());
        alcool = Double.parseDouble(edtAlcool.getText().toString());

        op = alcool/gasolina;

        if(op >= 0.7){
            imgResp.setImageResource(R.drawable.alcool);
        } else {
            imgResp.setImageResource(R.drawable.gasolina);
        }
    }
}


