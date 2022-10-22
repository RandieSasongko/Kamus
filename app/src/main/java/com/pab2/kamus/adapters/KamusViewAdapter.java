package com.pab2.kamus.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.pab2.kamus.databinding.ItemKamusBinding;
import com.pab2.kamus.models.Kamus;

import java.util.ArrayList;

public class KamusViewAdapter extends RecyclerView.Adapter<KamusViewAdapter.ViewHolder> {
    private ArrayList<Kamus> kamusArrayList = new ArrayList<>();
    private Context context;

    public KamusViewAdapter(Context context) {
        this.context = context;
    }

    public void setKamusData(ArrayList<Kamus> data) {
        kamusArrayList = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public KamusViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                ItemKamusBinding.inflate(
                        LayoutInflater.from(parent.getContext()),
                        parent,
                        false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull KamusViewAdapter.ViewHolder holder, int position) {
        int pos = holder.getAdapterPosition();

        holder.binding.tvTitle.setText(kamusArrayList.get(pos).getTitle());
        holder.binding.tvDesription.setText(kamusArrayList.get(pos).getDescription());
    }

    @Override
    public int getItemCount() {
        return kamusArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemKamusBinding binding;

        public ViewHolder(ItemKamusBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
