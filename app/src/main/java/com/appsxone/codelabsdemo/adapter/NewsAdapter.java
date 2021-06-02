package com.appsxone.codelabsdemo.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.appsxone.codelabsdemo.model.NewsModel;
import com.appsxone.codelabsdemo.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {
    Context context;
    ArrayList<NewsModel> newsModelArrayList;

    public NewsAdapter(Context c, ArrayList<NewsModel> message) {
        context = c;
        newsModelArrayList = message;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_news, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvTitle.setText(newsModelArrayList.get(position).title);
        holder.tvSummary.setText(newsModelArrayList.get(position).summary);
        holder.tvLink.setText(newsModelArrayList.get(position).link);
        holder.tvPublish.setText(newsModelArrayList.get(position).published);
        holder.tvId.setText(newsModelArrayList.get(position).id);
        holder.imgArrow.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onClick(View view) {
                holder.imgArrow.setFocusable(false);
                if (holder.imgArrow.getDrawable().getConstantState() == context.getResources().getDrawable(R.drawable.ic_up).getConstantState()) {
                    holder.imgArrow.setImageResource(R.drawable.ic_down);
                    holder.tvSummary.setVisibility(View.GONE);
                    holder.tvLink.setVisibility(View.GONE);
                    holder.tvPublish.setVisibility(View.GONE);
                } else {
                    holder.imgArrow.setImageResource(R.drawable.ic_up);
                    holder.tvSummary.setVisibility(View.VISIBLE);
                    holder.tvLink.setVisibility(View.VISIBLE);
                    holder.tvPublish.setVisibility(View.VISIBLE);
                }
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.imgArrow.setImageResource(R.drawable.ic_down);
                holder.tvSummary.setVisibility(View.GONE);
                holder.tvLink.setVisibility(View.GONE);
                holder.tvPublish.setVisibility(View.GONE);
            }
        });

    }

    @Override
    public int getItemCount() {
        return newsModelArrayList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvSummary, tvLink, tvPublish, tvId;
        ImageView imgArrow;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvSummary = itemView.findViewById(R.id.tvSummary);
            tvLink = itemView.findViewById(R.id.tvLink);
            tvPublish = itemView.findViewById(R.id.tvPublish);
            tvId = itemView.findViewById(R.id.tvId);
            imgArrow = itemView.findViewById(R.id.imgArrow);
        }
    }
}
