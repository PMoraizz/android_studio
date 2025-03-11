package com.example.barbearia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItensAdapter extends RecyclerView.Adapter<ItensAdapter.ItensViewHolder> {
    private List<Itens> itensList;
    private Context context;
    private OnItemClickListener onItemClickListener;

    public ItensAdapter(Context context, List<Itens> itensList) {
        this.context = context;
        this.itensList = itensList;
    }


    @Override
    public ItensViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.itens, parent, false);
        return new ItensViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItensViewHolder holder, int position) {
        Itens item = itensList.get(position);

        holder.titulo.setText(item.getTitulo());
        holder.descricao.setText(item.getDescricao());
        holder.preco.setText(item.getPreco());
        holder.tempo.setText(item.getTempo());
        holder.img.setImageResource(item.getImg());

        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itensList.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(Itens itens);
    }

    public static class ItensViewHolder extends RecyclerView.ViewHolder {
        TextView titulo, descricao, preco, tempo;
        ImageView img;

        public ItensViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.txtTitulo);
            descricao = itemView.findViewById(R.id.txtDescricao);
            preco = itemView.findViewById(R.id.txtPreco);
            tempo = itemView.findViewById(R.id.txtTempo);
            img = itemView.findViewById(R.id.imgItem);
        }
    }
}
