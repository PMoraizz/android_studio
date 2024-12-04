package com.example.aula03_12_24;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RocketAdapter extends RecyclerView.Adapter<RocketAdapter.ViewHolder> {
    ArrayList<RocketModal> rocketModals;

    public RocketAdapter(ArrayList<RocketModal> rocketModals){
        this.rocketModals = rocketModals;
    }

    public static class  ViewHolder extends  RecyclerView.ViewHolder {
        ImageView rocket_image;
        TextView rocket_name;
        TextView launch_date;
        TextView launch_success;
        TextView payload;

        public ViewHolder(@NonNull View itemView){
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

    @Override
    public void onBindViewHolder(@NonNull RocketAdapter.ViewHolder holder, int position) {
        RocketModal rocketModal = rocketModals.get(position);

        holder.rocket_image.setImageResource(rocketModal.getRocketImage());

        holder.rocket_name.setText("Rocket: " +rocketModal.getRocketName());
        holder.launch_date.setText("Launch Date: " + rocketModal.getLauchDate());

        if(rocketModal.isLauchSuccess()){
            holder.launch_success.setText("Launch Succeded");
        } else {
            holder.launch_success.setText("Launch Failed");
        }

        holder.payload.setText("Payload: " + rocketModal.getPayload());
    }

    @Override
    public int getItemCount() {
        return rocketModals.size();
    }
}
