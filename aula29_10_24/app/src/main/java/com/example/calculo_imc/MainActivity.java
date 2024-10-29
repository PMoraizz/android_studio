package com.example.calculo_imc;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText edtPeso, edtAltura;
    private ImageView imgImc;
    private TextView txtImc, txtTipoPeso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        edtPeso = findViewById(R.id.edtPeso);
        edtAltura = findViewById(R.id.edtAltura);
        imgImc = findViewById(R.id.imgImc);
        txtImc = findViewById(R.id.txtImc);
        txtTipoPeso = findViewById(R.id.txtTipoPeso);

        edtPeso.addTextChangedListener(new Mascara(edtPeso, Mascara.TipoCampo.PESO));
        edtAltura.addTextChangedListener(new Mascara(edtAltura, Mascara.TipoCampo.ALTURA));
    }

    public void onClick(View view) {
        double altura, peso, op;
        try {

            peso = Double.parseDouble(edtPeso.getText().toString().replaceAll("[^\\d]", ""));
            altura = Double.parseDouble(edtAltura.getText().toString().replaceAll("[^\\d]", ""));

            op = (peso / (altura*altura)) * 100;

            retorno(op, peso, altura);

        } catch (Exception e) {
            // Captura exceções, caso haja um erro na conversão ou nos cálculos
        }
    }

    @SuppressLint("SetTextI18n")
    public void retorno(double op, double peso, double altura) {
        if(peso == 0.00 || altura == 0.00){
            imgImc.setImageResource(R.drawable.ic_launcher_foreground);
            Toast.makeText(this, "Insira os valores dos combustiveis", Toast.LENGTH_SHORT).show();
        } else {
            @SuppressLint("DefaultLocale")
            String resultado = String.format("%.2f", op);

            txtImc.setText("IMC: " + resultado);

            if (op < 18.5){
                imgImc.setImageResource(R.drawable.baixo_peso);
                txtTipoPeso.setText("Peso Baixo");
            }

            if (op >= 18.5 && op < 24.9){
                imgImc.setImageResource(R.drawable.normal_peso);
                txtTipoPeso.setText("Peso Normal");
            }

            if (op >= 24.9 && op < 29.9){
                imgImc.setImageResource(R.drawable.excesso_peso);
                txtTipoPeso.setText("Excesso De Peso");
            }

            if (op >= 29.9 && op < 34.9){
                imgImc.setImageResource(R.drawable.obsidade);
                txtTipoPeso.setText("Obesidade");
            }

            if (op >= 34.9){
                imgImc.setImageResource(R.drawable.obsidade_extrema);
                txtTipoPeso.setText("Obesidade Extrema");
            }

        }
    }


}