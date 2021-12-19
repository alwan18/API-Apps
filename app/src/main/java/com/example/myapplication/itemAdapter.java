package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.awt.font.TextAttribute;
import java.util.List;

public class itemAdapter extends RecyclerView.Adapter<itemAdapter.itemHolder> {

    private Context context;
    private List<itemActivity> iData;

    public itemAdapter(Context context, List<itemActivity> iData) {
        this.context = context;
        this.iData = iData;
    }

    @NonNull
    @Override
    public itemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        LayoutInflater inflater = LayoutInflater.from(context);
        v = inflater.inflate(R.layout.item, parent, false);

        return new itemHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull itemHolder holder, int position) {

        holder.title.setText(iData.get(position).getTitle());
        holder.desc.setText(iData.get(position).getDesc());
        holder.example.setText(iData.get(position).getExmple());
        holder.link.setText(iData.get(position).getLink());

        // get data image from glide library
        Glide.with(context)
                .load(iData.get(position).getLogo())
                .into(holder.logo);
    }

    @Override
    public int getItemCount() {
        return iData.size();
    }

    public static class itemHolder extends RecyclerView.ViewHolder {


        TextView title;
        TextView desc;
        ImageView logo;
        TextView example;
        TextView link;

        public itemHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            desc = itemView.findViewById(R.id.desc);
            logo = itemView.findViewById(R.id.imageview); // logo id
            example = itemView.findViewById(R.id.example);
            link = itemView.findViewById(R.id.link);
        }
    }

}
