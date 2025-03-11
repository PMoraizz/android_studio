package com.example.barbearia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AgendamentosAdapter extends RecyclerView.Adapter<AgendamentosAdapter.AgendamentoViewHolder>{
    private List<Agendamento> agendamentoList;
    private Context context;
    private ItensAdapter.OnItemClickListener onItemClickListener;

    public AgendamentosAdapter(Context context, List<Agendamento> agendamentoList) {
        this.context = context;
        this.agendamentoList = agendamentoList;
    }


    @Override
    public AgendamentosAdapter.AgendamentoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.lista_agendamento, parent, false);
        return new AgendamentosAdapter.AgendamentoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AgendamentosAdapter.AgendamentoViewHolder holder, int position) {
        Agendamento agendamento = agendamentoList.get(position);

        holder.titulo.setText(agendamento.getTitulo());
        holder.profissional.setText(agendamento.getProfissional());
        holder.data.setText(agendamento.getData());
        holder.hora.setText(agendamento.getHora());
    }

    @Override
    public int getItemCount() {
        return agendamentoList.size();
    }

    public static class AgendamentoViewHolder extends RecyclerView.ViewHolder {
        TextView titulo, profissional, data, hora;

        public AgendamentoViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.txtTituloAgendamento);
            profissional = itemView.findViewById(R.id.txtProfissionalAgendamento);
            data = itemView.findViewById(R.id.txtDataAgendamento);
            hora = itemView.findViewById(R.id.txtHoraAgendamento);
        }
    }
}
