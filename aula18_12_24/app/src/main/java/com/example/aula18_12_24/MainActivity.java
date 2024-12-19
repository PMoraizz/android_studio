package com.example.aula18_12_24;

import android.content.Intent;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Movie> movieList = new ArrayList<>();
        movieList.add(new Movie("Solo Leveling: ReAwakening", R.drawable.movie01, "05/12/2024", 8.1, "Over a decade after 'gates' connecting worlds appeared, awakening 'hunters' with superpowers, weakest hunter Sung Jinwoo encounters a double dungeon and accepts a mysterious quest, becoming the only one able to level up, changing his fate", R.drawable.categoria_18anos));
        movieList.add(new Movie("Moana 2", R.drawable.movie02, "28/11/2024", 3.7, "Moana viaja para os mares distantes depois de receber uma ligação inesperada de seus ancestrais.", R.drawable.categoria_livre));
        movieList.add(new Movie("Hellboy e o Homem Torto", R.drawable.movie03, "24/10/2024", 2.4, "Hellboy se une a um agente novato da B.P.D.P. na década de 1950 e são enviados para os Apalaches. Lá, descobrem uma remota e assombrada comunidade, dominada por bruxas e liderada pelo sinistro demônio local, conhecido como O Homem Torto. À medida que exploram os mistérios sombrios deste lugar isolado, Hellboy confronta segredos enterrados de seu próprio passado, levando-o a uma batalha épica contra forças malignas que ameaçam desencadear o caos sobre o mundo.", R.drawable.categoria_16anos));
        movieList.add(new Movie("Howl's Moving Castle", R.drawable.movie04, "15/07/2005", 8.2, "Uma bruxa lança uma terrível maldição sobre a jovem Sophie transformando-a em uma velha. Desesperada, ela embarca em uma odisseia em busca do mago Howl, um misterioso feiticeiro que pode ajudá-la a reverter o feitiço.", R.drawable.categoria_livre));
        movieList.add(new Movie("Transformers One", R.drawable.movie05, "02/12/2024", 7.5, "A história de origem dos Autobots e Decepticons, revelando a batalha que levou à guerra entre os dois grupos de robôs.", R.drawable.categoria_10anos));
        movieList.add(new Movie("The Super Mario Bros. Movie", R.drawable.movie06, "07/04/2023", 7.3, "Mario e Luigi, dois encanadores de Brooklyn, são transportados para um reino fantástico onde se tornam heróis em uma missão para salvar a Princesa Peach do vilão Bowser.", R.drawable.categoria_livre));
        movieList.add(new Movie("Barbie", R.drawable.movie07, "20/07/2023", 7.1, "Após ser expulsa do mundo perfeito de Barbie Land, Barbie se aventura no mundo real, onde aprende mais sobre si mesma e sobre o que significa ser mulher.", R.drawable.categoria_livre));
        movieList.add(new Movie("Shrek 5", R.drawable.movie08, "25/11/2026", 7.8, "Shrek e seus amigos se encontram em novas aventuras no pântano, com muito mais comédia e diversão para todas as idades.", R.drawable.categoria_livre));
        movieList.add(new Movie("Red One", R.drawable.movie09, "07/12/2024", 6.9, "Uma aventura épica com o Papai Noel como um herói, misturando ação, comédia e o espírito natalino.", R.drawable.categoria_livre));
        movieList.add(new Movie("Snow White", R.drawable.movie10, "22/04/2025", 8.0, "Uma nova adaptação do clássico conto de fadas, trazendo Snow White de volta com uma versão mais moderna e inclusiva da história.", R.drawable.categoria_livre));
        movieList.add(new Movie("Paddington in Peru", R.drawable.movie11, "25/11/2024", 7.5, "Paddington embarca em uma nova aventura no Peru, onde enfrenta novos desafios e encontra novos amigos.", R.drawable.categoria_livre));
        movieList.add(new Movie("Lilo & Stitch", R.drawable.movie12, "13/06/2025", 7.6, "Lilo e Stitch voltam à ação, enfrentando novas aventuras e perigos no Havai.", R.drawable.categoria_livre));
        movieList.add(new Movie("Venom: The Last Dance", R.drawable.movie13, "17/07/2025", 7.3, "Venom enfrenta seu maior inimigo até agora, levando a uma batalha épica por sua sobrevivência.", R.drawable.categoria_16anos));
        movieList.add(new Movie("A Minecraft Movie", R.drawable.movie14, "03/12/2025", 6.8, "Uma adaptação épica do popular jogo Minecraft, onde os jogadores enfrentam desafios gigantes e criam seu próprio mundo.", R.drawable.categoria_livre));
        movieList.add(new Movie("Mufasa: The Lion King", R.drawable.movie15, "15/07/2025", 7.9, "A história do icônico Mufasa, pai de Simba, revelando suas origens e desafios antes de ser rei.", R.drawable.categoria_livre));
        movieList.add(new Movie("Captain America: Brave New World", R.drawable.movie16, "02/05/2025", 8.2, "Steve Rogers retorna como Capitão América para enfrentar uma nova ameaça global que pode mudar o mundo para sempre.", R.drawable.categoria_12anos));
        movieList.add(new Movie("Godzilla: Minus One", R.drawable.movie17, "11/12/2025", 7.4, "Godzilla retorna, desta vez enfrentando uma nova ameaça que desafia até o rei dos monstros.", R.drawable.categoria_12anos));
        movieList.add(new Movie("Karate Kid: Legends", R.drawable.movie18, "24/09/2025", 6.7, "O legado do Karate Kid continua com novos jovens aprendizes enfrentando desafios e treinando para se tornarem mestres.", R.drawable.categoria_10anos));
        movieList.add(new Movie("Gladiator II", R.drawable.movie19, "13/11/2025", 8.0, "O épico retorno de Gladiador, onde o filho de Maximus busca vingança e honra no império romano.", R.drawable.categoria_16anos));
        movieList.add(new Movie("Joker: Folie à Deux", R.drawable.movie20, "14/10/2025", 8.4, "Uma continuação sombria da história do Joker, explorando sua relação com uma nova figura trágica.", R.drawable.categoria_18anos));

        MovieAdapter movieAdapter = new MovieAdapter(this, movieList);
        recyclerView.setAdapter(movieAdapter);

        movieAdapter.setOnItemClickListener(new MovieAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Movie movie) {
                Intent intent = new Intent(MainActivity.this, MovieDatailActivity.class);
                intent.putExtra("movieTitle", movie.getTitle());
                intent.putExtra("moviePosterImage", movie.getPosterImage());
                intent.putExtra("descricao", movie.getOverview());
                intent.putExtra("pontuacao", movie.getRating());
                intent.putExtra("categoriaImage", movie.getCategoria());

                startActivity(intent);
            }
        });
    }
}