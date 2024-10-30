package com.example.calculadoraimc;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText edtPeso, edtAltura; // Campos para entrada de peso e altura
    private ImageView imgImc; // Imagem que representa o IMC
    private TextView txtImc, txtTipoPeso; // TextViews para exibir IMC e tipo de peso

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // Habilita o modo de tela cheia
        setContentView(R.layout.activity_main); // Define o layout da atividade

        // Inicializa os componentes da interface
        edtPeso = findViewById(R.id.edtPeso);
        edtAltura = findViewById(R.id.edtAltura);
        imgImc = findViewById(R.id.imgImc);
        txtImc = findViewById(R.id.txtImc);
        txtTipoPeso = findViewById(R.id.txtTipoPeso);

        // Adiciona máscara de entrada nos campos de peso e altura
        edtPeso.addTextChangedListener(new Mascara(edtPeso, Mascara.TipoCampo.PESO));
        edtAltura.addTextChangedListener(new Mascara(edtAltura, Mascara.TipoCampo.ALTURA));
    }

    public void onClick(View view) {
        double altura, peso, op; // Variáveis para altura, peso e resultado do cálculo
        try {
            // Converte as entradas de texto para double após remover caracteres não numéricos
            peso = Double.parseDouble(edtPeso.getText().toString().replaceAll("[^\\d]", ""));
            altura = Double.parseDouble(edtAltura.getText().toString().replaceAll("[^\\d]", ""));

            // Calcula o IMC
            op = (peso / (altura * altura)) * 100;

            // Chama o método para processar e exibir o resultado
            retorno(op, peso, altura);

        } catch (Exception e) {
            // Captura exceções, caso haja um erro na conversão ou nos cálculos
        }
    }

    @SuppressLint("SetTextI18n")
    public void retorno(double op, double peso, double altura) {
        // Verifica se os valores de peso ou altura são zero
        if (peso == 0.00 || altura == 0.00) {
            imgImc.setImageResource(R.drawable.ic_launcher_foreground); // Imagem padrão
            Toast.makeText(this, "Insira os valores dos combustiveis", Toast.LENGTH_SHORT).show(); // Mensagem de erro
        } else {
            @SuppressLint("DefaultLocale")
            String resultado = String.format("%.2f", op); // Formata o resultado do IMC

            txtImc.setText("IMC: " + resultado); // Exibe o IMC

            // Determina o tipo de peso baseado no IMC e atualiza a imagem e texto correspondente
            if (op < 18.5) {
                imgImc.setImageResource(R.drawable.baixo_peso);
                txtTipoPeso.setText("Peso Baixo");
            } else if (op >= 18.5 && op < 24.9) {
                imgImc.setImageResource(R.drawable.normal_peso);
                txtTipoPeso.setText("Peso Normal");
            } else if (op >= 24.9 && op < 29.9) {
                imgImc.setImageResource(R.drawable.excesso_peso);
                txtTipoPeso.setText("Excesso De Peso");
            } else if (op >= 29.9 && op < 34.9) {
                imgImc.setImageResource(R.drawable.obsidade);
                txtTipoPeso.setText("Obesidade");
            } else {
                imgImc.setImageResource(R.drawable.obsidade_extrema);
                txtTipoPeso.setText("Obesidade Extrema");
            }
        }
    }
}
