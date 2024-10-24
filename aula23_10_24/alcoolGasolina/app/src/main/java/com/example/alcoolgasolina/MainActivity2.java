package com.example.alcoolgasolina;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {
    // Declaração das variáveis para os campos de entrada e a imagem de resposta
    private EditText edtGasolina, edtAlcool;
    private ImageView imgResp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        // Encontrar os objetos pelo ID definido no layout
        edtGasolina = findViewById(R.id.edtGasolina);
        edtAlcool = findViewById(R.id.edtAlcool);
        imgResp = findViewById(R.id.img2);

        // Adiciona um listener para aplicar uma máscara de entrada nos campos de texto
        edtGasolina.addTextChangedListener(new Mascara(edtGasolina));
        edtAlcool.addTextChangedListener(new Mascara(edtAlcool));
    }

    /*
     * Método chamado quando o botão é clicado
     * Calcula a relação entre o preço do álcool e da gasolina
     */
    public void onClick(View view) {
        double gasolina, alcool, op;
        try {
            // Captura os valores dos campos de texto, removendo caracteres não numéricos
            gasolina = Double.parseDouble(edtGasolina.getText().toString().replaceAll("[^\\d]", ""));
            alcool = Double.parseDouble(edtAlcool.getText().toString().replaceAll("[^\\d]", ""));

            // Calcula a relação entre o preço do álcool e da gasolina
            op = alcool / gasolina;

            // Verifica se algum dos valores é zero
            if (alcool == 0.0 || gasolina == 0.0) {
                // Se um dos valores for zero, exibe uma imagem padrão, e retorna um Toast ao usuario
                imgResp.setImageResource(R.drawable.ic_launcher_foreground);
                Toast.makeText(this, "Insira os valores dos combustiveis", Toast.LENGTH_SHORT).show();
            } else {
                // Se a relação for menor ou igual a 0.7, exibe a imagem do álcool, caso contrário, exibe a da gasolina
                if (op <= 0.7) {
                    imgResp.setImageResource(R.drawable.alcool);
                } else {
                    imgResp.setImageResource(R.drawable.gasolina);
                }
            }
        } catch (Exception e) {
            // Captura exceções, caso haja um erro na conversão ou nos cálculos
        }
    }
}
