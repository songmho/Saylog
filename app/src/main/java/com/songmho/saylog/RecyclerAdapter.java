package com.songmho.saylog;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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
    String[] drawer_list_list;

    RecyclerAdapter(Context context,List<RecyclerItem> items,int itemLayout){
        this.context=context;
        this.items=items;
        this.itemLayout=itemLayout;
    }

    RecyclerAdapter(Context context, String[] drawer_list_list, int itemLayout) {
        this.context=context;
        this.drawer_list_list=drawer_list_list;
        this.itemLayout=itemLayout;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public TextView saying;
        public TextView source;
        public TextView date;
        public CardView cardView;

        public LinearLayout container;
        public TextView title;
        public ViewHolder(View itemView, int itemLayout) {
            super(itemView);
            if(itemLayout==R.layout.item_recycler) {
                saying = (TextView) itemView.findViewById(R.id.saying);
                source = (TextView) itemView.findViewById(R.id.source);
                date = (TextView) itemView.findViewById(R.id.date);
                cardView = (CardView) itemView.findViewById(R.id.cardview);
            }
            else if(itemLayout==R.layout.item_drawerlist) {
                title = (TextView) itemView.findViewById(R.id.title);
                container=(LinearLayout)itemView.findViewById(R.id.container);
            }
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(itemLayout==R.layout.item_recycler){
            View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false);
            return new ViewHolder(v,itemLayout);}
        if(itemLayout==R.layout.item_drawerlist){
            View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_drawerlist,parent,false);
            return new ViewHolder(v,itemLayout);}
        return null;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if(itemLayout==R.layout.item_recycler) {
            RecyclerItem item = items.get(position);
            holder.itemView.setTag(item);
            holder.saying.setText(item.getSaying());
            holder.source.setText(item.getSource());
            holder.date.setText(item.getDate());
            holder.cardView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context,DetailActivity.class);
                    intent.putExtra("saying",holder.saying.getText());
                    intent.putExtra("source",holder.source.getText());
                    intent.putExtra("date",holder.date.getText());
                    context.startActivity(intent);
                }
            });
        }
        else if(itemLayout==R.layout.item_drawerlist){
            holder.title.setText(drawer_list_list[position]);
            holder.container.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(holder.title.getText()=="setup") {
                        Intent intent = new Intent(context, SetupActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                    if(holder.title.getText()=="about"){
                        Intent intent = new Intent(context, AboutActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        if(itemLayout==R.layout.item_recycler)
            return items.size();
        else if(itemLayout==R.layout.item_drawerlist)
            return drawer_list_list.length;
        return 0;
    }
}