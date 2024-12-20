package com.example.aula18_12_24;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MovieDatailActivity extends AppCompatActivity {
    TextView titleTextView, descricaoTextView, pontuacoTextView;
    ImageView posterImageView, categoriaImageView;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        titleTextView =         findViewById(R.id.txtNomeFilme);
        posterImageView =       findViewById(R.id.imgFilme);
        descricaoTextView =     findViewById(R.id.txtDescricao);
        pontuacoTextView =      findViewById(R.id.txtPontuacao);
        categoriaImageView =    findViewById(R.id.imgCategoria);

        Intent intent = getIntent();
        String movieTitle =     intent.getStringExtra("movieTitle");
        String descricaoMovie = intent.getStringExtra("descricao");
        double pontuacaoMovie = intent.getDoubleExtra("pontuacao", -1.0);
        int movieImage =        intent.getIntExtra("moviePosterImage", -1);
        int categoriaImage =    intent.getIntExtra("categoriaImage", -1);

        if(movieTitle != null && descricaoMovie != null && pontuacaoMovie != -1.0 && movieImage != -1 && categoriaImage != -1){
            titleTextView.setText(movieTitle);
            descricaoTextView.setText(descricaoMovie);
            pontuacoTextView.setText(String.valueOf(pontuacaoMovie));
            posterImageView.setImageResource(movieImage);
            categoriaImageView.setImageResource(categoriaImage);
        }
    }
}
