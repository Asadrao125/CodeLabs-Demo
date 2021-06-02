package com.asadrao.codelabsdemo.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.asadrao.codelabsdemo.R;
import com.asadrao.codelabsdemo.model.ConverterModel;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ConverterAdapter extends RecyclerView.Adapter<ConverterAdapter.MyViewHolder> {
    Context context;
    ArrayList<ConverterModel> converterModelArrayList;

    public ConverterAdapter(Context c, ArrayList<ConverterModel> list) {
        context = c;
        converterModelArrayList = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_converter, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvCount.setText("" + converterModelArrayList.get(position).count);
        holder.tvRoman.setText(converterModelArrayList.get(position).roman);
        holder.imgCross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return converterModelArrayList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvCount, tvRoman;
        ImageView imgCross;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCount = itemView.findViewById(R.id.tvCount);
            tvRoman = itemView.findViewById(R.id.tvRoman);
            imgCross = itemView.findViewById(R.id.imgCross);
        }
    }

    public void removeAt(int position) {
        converterModelArrayList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, converterModelArrayList.size());
    }

    private void showDialog(int pos) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Are you sure you want to delete?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        removeAt(pos);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

        final AlertDialog alert = builder.create();
        alert.show();
    }

}
