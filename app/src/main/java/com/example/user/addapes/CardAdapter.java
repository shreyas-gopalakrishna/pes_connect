package com.example.user.addapes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

// Usage to list a series of cards on an activity
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    List<Dashboard> list = new ArrayList<>();

    public CardAdapter(List<Dashboard> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.Dashboard = getItem(position);
        holder.cardtitle.setText(list.get(position).getName());
        holder.cardimage.setImageResource(list.get(position).id);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public Dashboard getItem(int i) {
        return list.get(i);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView cardimage;
        TextView cardtitle;
        Dashboard Dashboard;

        public ViewHolder(View itemView) {
            super(itemView);
            cardimage = (ImageView) itemView.findViewById(R.id.cardimage);
            cardtitle = (TextView) itemView.findViewById(R.id.cardtitle);
        }
    }
}