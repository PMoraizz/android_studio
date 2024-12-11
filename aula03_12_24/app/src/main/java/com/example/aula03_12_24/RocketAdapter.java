package com.example.aula03_12_24;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RocketAdapter extends RecyclerView.Adapter<RocketAdapter.ViewHolder> {
    ArrayList<RocketModel> rocketModals;

    // Comentar
    public RocketAdapter(ArrayList<RocketModel> rocketModals) {
        this.rocketModals = rocketModals;
    }

    // Comentar
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView rocket_image;
        TextView rocket_name;
        TextView launch_date;
        TextView launch_success;
        TextView payload;

        // Comentar
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            rocket_image = itemView.findViewById(R.id.rocket_image);
            rocket_name = itemView.findViewById(R.id.rocket_name);
            launch_date = itemView.findViewById(R.id.launch_date);
            launch_success = itemView.findViewById(R.id.launch_success);
            payload = itemView.findViewById(R.id.payload);
        }
    }

    @NonNull
    @Override
    public RocketAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RocketAdapter.ViewHolder holder, int position) {

        // Comentar
        RocketModel rocketModal = rocketModals.get(position);

        // Comentar
        holder.rocket_image.setImageResource(rocketModal.getRocketImage());
        holder.rocket_name.setText("Rocket: " + rocketModal.getRocketName());
        holder.launch_date.setText("Launch Date: " + rocketModal.getLaunchDate());

        // Comentar
        if (rocketModal.isLaunchSuccess()) {
            holder.launch_success.setText("Launch Succeeded");
        } else {
            holder.launch_success.setText("Launch Failed");
        }

        // Comentar
        holder.payload.setText("Payload: " + rocketModal.getPayload());
    }

    @Override
    public int getItemCount() {
        return rocketModals.size();
    }
}
