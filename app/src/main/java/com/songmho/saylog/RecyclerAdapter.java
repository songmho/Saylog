package com.songmho.saylog;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import static android.support.v7.widget.RecyclerView.*;

/**
 * Created by songmho on 2015-02-17.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    Context context;
    private List<RecyclerItem> items;
    int itemLayout;

    RecyclerAdapter(Context context,List<RecyclerItem> items,int itemLayout){
        this.context=context;
        this.items=items;
        this.itemLayout=itemLayout;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public TextView saying;
        public TextView source;
        public TextView date;

        public ViewHolder(View itemView) {
            super(itemView);
            saying=(TextView)itemView.findViewById(R.id.saying);
            source=(TextView)itemView.findViewById(R.id.source);
            date=(TextView)itemView.findViewById(R.id.date);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RecyclerItem item=items.get(position);
        holder.itemView.setTag(item);
        holder.saying.setText(item.getSaying());
        holder.source.setText(item.getSource());
        holder.date.setText(item.getDate());
    }


    @Override
    public int getItemCount() {
        return items.size();
    }
}
