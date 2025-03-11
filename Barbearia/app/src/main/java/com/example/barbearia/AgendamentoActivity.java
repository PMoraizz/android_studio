package com.example.barbearia;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class AgendamentoActivity extends AppCompatActivity {
    TextView txtTitulo, txtPreco, txtNomeP;
    ImageView imgP;
    private Button[] buttons;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_agendamento);

        txtTitulo = findViewById(R.id.txtTitulo);
        txtPreco = findViewById(R.id.txtPreco);
        txtNomeP = findViewById(R.id.txtNomeP);
        imgP = findViewById(R.id.imgP);

        buttons = new Button[] {
                findViewById(R.id.btn8h),
                findViewById(R.id.btn8_30h),
                findViewById(R.id.btn9h),
                findViewById(R.id.btn9_30h),
                findViewById(R.id.btn10h),
                findViewById(R.id.btn10_30h),
                findViewById(R.id.btn11h),
                findViewById(R.id.btn11_30h),
                findViewById(R.id.btn12h),
                findViewById(R.id.btn12_30h),
                findViewById(R.id.btn13h),
                findViewById(R.id.btn13_30h),
                findViewById(R.id.btn14h),
                findViewById(R.id.btn14_30h),
                findViewById(R.id.btn15h),
                findViewById(R.id.btn15_30h),
                findViewById(R.id.btn16h),
                findViewById(R.id.btn16_30h),
                findViewById(R.id.btn17h),
                findViewById(R.id.btn17_30h)
        };

        Intent intent = getIntent();
        String titulo = intent.getStringExtra("titulo");
        String preco = intent.getStringExtra("preco");
        int img = intent.getIntExtra("imgP", -1);
        String profissional = intent.getStringExtra("profissional");

        if (titulo != null && preco != null && img != -1 && profissional != null ) {
            txtTitulo.setText(titulo);
            txtPreco.setText(preco);
            txtNomeP.setText(profissional);
            imgP.setImageResource(img);
        }
        for (Button button : buttons) {
            button.setOnClickListener(this::onClickSelecionado);
        }
    }

    public void onClickSelecionado(View v) {

        for (Button button : buttons) {
            button.setBackgroundColor(Color.parseColor("#3F51B5"));
        }

        v.setBackgroundColor(Color.parseColor("#FF9800"));


//        // Verifica se existe o próximo botão
//        ViewGroup parent = (ViewGroup) v.getParent(); // Obtém o pai do botão (GridLayout)
//        int index = parent.indexOfChild(v); // Índice do botão clicado

//        if (index + 1 < parent.getChildCount()) {
//            // Altera a cor do próximo botão
//            View nextButton = parent.getChildAt(index + 1);
//            nextButton.setBackgroundColor(Color.parseColor("#FF5722")); // Exemplo: Vermelho
//        }
    }

}