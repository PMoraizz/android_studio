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

    // Declaração dos componentes da interface
    private EditText edtPeso, edtAltura; // Campos para entrada de peso e altura
    private ImageView imgImc; // Imagem que representa o IMC
    private TextView txtImc, txtTipoPeso; // TextViews para exibir IMC e tipo de peso

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // Habilita o modo de tela cheia
        setContentView(R.layout.activity_main); // Define o layout da atividade

        // Inicializa os componentes da interface
        this.edtPeso = findViewById(R.id.edtPeso);
        this.edtAltura = findViewById(R.id.edtAltura);
        this.imgImc = findViewById(R.id.imgImc);
        this.txtImc = findViewById(R.id.txtImc);
        this.txtTipoPeso = findViewById(R.id.txtTipoPeso);

        // Adiciona máscara de entrada nos campos de peso e altura
        edtPeso.addTextChangedListener(new Mascara(edtPeso, Mascara.TipoCampo.PESO));
        edtAltura.addTextChangedListener(new Mascara(edtAltura, Mascara.TipoCampo.ALTURA));
    }

    public void onClick(View view) {
        double altura, peso, imc; // Variáveis para altura, peso e resultado do cálculo
        try {
            // Converte as entradas de texto para double após remover caracteres não numéricos
            peso = Double.parseDouble(edtPeso.getText().toString().replaceAll("kg", ""));
            altura = Double.parseDouble(edtAltura.getText().toString().replaceAll("m", ""));

            // Calcula o IMC
            imc = (peso / (altura * altura));

            // Chama o método para processar e exibir o resultado
            retorno(imc, peso, altura);

        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    /*
     * Método que processa o resultado do cálculo do IMC e atualiza a interface.
     * Recebe como parâmetros o IMC calculado, o peso e a altura do usuário.
     */
    @SuppressLint("SetTextI18n")
    public void retorno(double imc, double peso, double altura) {

        // Verifica se os valores de peso ou altura são zero
        if (peso == 0.00 || altura == 0.00) {
            imgImc.setImageResource(R.drawable.ic_launcher_foreground); // Imagem padrão
            Toast.makeText(this, "Insira os valores dos combustiveis", Toast.LENGTH_SHORT).show(); // Mensagem de erro
            txtImc.setText("IMC: ");
            txtTipoPeso.setText("");

        } else {
            @SuppressLint("DefaultLocale")
            // Formata o resultado do IMC
            String resultado = String.format("%.2f", imc);
            txtTipoPeso.setText("");
            // Exibe o IMC
            txtImc.setText("IMC: " + resultado);

            // Determina o tipo de peso baseado no IMC e atualiza a imagem e texto correspondente
            if (imc < 18.5) {
                imgImc.setImageResource(R.drawable.baixo_peso);
                txtTipoPeso.setText("Peso Baixo");
            } else if (imc >= 18.5 && imc < 24.9) {
                imgImc.setImageResource(R.drawable.normal_peso);
                txtTipoPeso.setText("Peso Normal");
            } else if (imc >= 24.9 && imc < 29.9) {
                imgImc.setImageResource(R.drawable.excesso_peso);
                txtTipoPeso.setText("Excesso De Peso");
            } else if (imc >= 29.9 && imc < 34.9) {
                imgImc.setImageResource(R.drawable.obsidade);
                txtTipoPeso.setText("Obesidade Grau I");
            } else if (imc >= 34.9 && imc < 40.0) {
                imgImc.setImageResource(R.drawable.obsidade);
                txtTipoPeso.setText("Obesidade Grau II");
            } else {
                imgImc.setImageResource(R.drawable.obsidade_extrema);
                txtTipoPeso.setText("Obesidade Extrema");
            }
        }
    }
}
