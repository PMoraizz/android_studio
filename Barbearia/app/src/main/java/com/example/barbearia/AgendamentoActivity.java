package com.example.barbearia;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Date;

public class AgendamentoActivity extends AppCompatActivity {
    TextView txtTitulo, txtPreco, txtNomeP;
    ImageView imgP;
    CalendarView calendarView;
    private Button[] buttons;
    FirebaseAuth auth;
    DatabaseReference databaseReference;
    String selectedHour = null;
    String selectedDate = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_agendamento);

        txtTitulo = findViewById(R.id.txtTitulo);
        txtPreco = findViewById(R.id.txtPreco);
        txtNomeP = findViewById(R.id.txtNomeP);
        imgP = findViewById(R.id.imgP);
        calendarView = findViewById(R.id.calendarView);

        auth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Barbeiros");

        buttons = new Button[]{
                findViewById(R.id.btn8h), findViewById(R.id.btn8_30h), findViewById(R.id.btn9h),
                findViewById(R.id.btn9_30h), findViewById(R.id.btn10h), findViewById(R.id.btn10_30h),
                findViewById(R.id.btn11h), findViewById(R.id.btn11_30h), findViewById(R.id.btn12h),
                findViewById(R.id.btn12_30h), findViewById(R.id.btn13h), findViewById(R.id.btn13_30h),
                findViewById(R.id.btn14h), findViewById(R.id.btn14_30h), findViewById(R.id.btn15h),
                findViewById(R.id.btn15_30h), findViewById(R.id.btn16h), findViewById(R.id.btn16_30h),
                findViewById(R.id.btn17h), findViewById(R.id.btn17_30h)
        };

        Intent intent = getIntent();
        String titulo = intent.getStringExtra("titulo");
        String preco = intent.getStringExtra("preco");
        int img = intent.getIntExtra("imgP", -1);
        String profissional = intent.getStringExtra("profissional");


        if (titulo != null && preco != null && img != -1 && profissional != null) {
            txtTitulo.setText(titulo);
            txtPreco.setText(preco);
            txtNomeP.setText(profissional);
            imgP.setImageResource(img);
        }

        for (Button button : buttons) {
            button.setOnClickListener(this::onClickSelecionado);
        }

        calendarView.setMinDate(System.currentTimeMillis());

        long selectedDate_aux = calendarView.getDate();  // get the date as a long (timestamp)
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        // Convert the long timestamp to a Date object
        Date date = new Date(selectedDate_aux);
        // Format the Date object into the desired string format
        String selectedDate_aux2 = dateFormat.format(date);

        verificarHorariosIndisponiveis(profissional, selectedDate_aux2);

        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            //SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            selectedDate = dateFormat.format(new java.util.Date(year - 1900, month, dayOfMonth));
            resetarBotoes();
            verificarHorariosIndisponiveis(profissional, selectedDate);
        });
    }

    private void resetarBotoes() {
        for (Button button : buttons) {
            button.setEnabled(true);
            button.setBackgroundColor(Color.parseColor("#3F51B5")); //Blue
        }
    }

    public void onClickSelecionado(View v) {
        for (Button button : buttons) {
            if(button.isEnabled()){
                button.setBackgroundColor(Color.parseColor("#3F51B5"));
            }
        }
        v.setBackgroundColor(Color.parseColor("#FF9800"));
        selectedHour = ((Button) v).getText().toString();
    }

    public void onClickAgendamento(View v) {
        if (selectedDate == null) {
            Toast.makeText(this, "Por favor, selecione uma data.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (selectedHour == null) {
            Toast.makeText(this, "Por favor, selecione um horário.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (auth.getCurrentUser() == null) {
            Toast.makeText(this, "Usuário não autenticado. Faça login novamente.", Toast.LENGTH_SHORT).show();
            return;
        }

        String userId = auth.getCurrentUser().getUid();
        String profissional = txtNomeP.getText().toString();
        String titulo = txtTitulo.getText().toString();
        String preco = txtPreco.getText().toString();

        HashMap<String, Object> agendamento = new HashMap<>();
        agendamento.put("titulo", titulo);
        agendamento.put("preco", preco);
        agendamento.put("data", selectedDate);
        agendamento.put("horario", selectedHour);
        agendamento.put("profissional", profissional);

        DatabaseReference userRef = FirebaseDatabase.getInstance()
                .getReference("user")
                .child(userId)
                .child("agendamentos");
        userRef.push().setValue(agendamento)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        salvarHorarioBarbeiro(profissional, selectedDate, selectedHour);
                        Toast.makeText(this, "Agendamento realizado com sucesso!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(this, PerfilActivity.class));
                    } else {
                        Toast.makeText(this, "Erro ao salvar agendamento.", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void salvarHorarioBarbeiro(String barbeiro, String data, String horario) {
        DatabaseReference barbeirosRef = FirebaseDatabase.getInstance()
                .getReference("Barbeiros")
                .child(barbeiro);

        barbeirosRef.child(data).child(horario).setValue(true)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(this, "Horário reservado para o barbeiro!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Erro ao reservar horário para o barbeiro.", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void verificarHorariosIndisponiveis(String barbeiro, String dataSelecionada) {
        DatabaseReference barbeiroRef = databaseReference.child(barbeiro).child(dataSelecionada);
        barbeiroRef.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot horarioSnapshot : snapshot.getChildren()) {
                    String horarioOcupado = horarioSnapshot.getKey();
                    for (Button button : buttons) {
                        if (button.getText().toString().equals(horarioOcupado)) {
                            button.setEnabled(false);
                            button.setBackgroundColor(Color.GRAY);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(AgendamentoActivity.this, "Erro ao verificar horários.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
