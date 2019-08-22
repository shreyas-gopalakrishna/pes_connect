package com.example.user.addapes;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;


public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.ViewHolder> {

    List<Placement> list = new ArrayList<>();

    public PlaceAdapter(List<Placement> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_placement, parent, false);
        return new ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.Placement = getItem(position);
        holder.name.setText(list.get(position).getName());
        holder.date.setText("Date : "+list.get(position).getDate());
        holder.time.setText("Time : "+list.get(position).getTime());
        holder.branch.setText(list.get(position).getBranch());
        holder.image.setImageResource(list.get(position).getId());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public Placement getItem(int i) {
        return list.get(i);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;
        TextView date;
        TextView time;
        TextView branch;
        Placement Placement;

        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
            name = (TextView) itemView.findViewById(R.id.name);
            date = (TextView) itemView.findViewById(R.id.date);
            time = (TextView) itemView.findViewById(R.id.time);
            branch = (TextView) itemView.findViewById(R.id.branch);
        }
    }
}