package com.songmho.saylog;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by songmho on 2015-02-16.
 */
public class ListFragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LinearLayout cur_container=(LinearLayout)inflater.inflate(R.layout.fragment_list, container, false);
        recyclerView=(RecyclerView)cur_container.findViewById(R.id.my_recycler_view);

        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new RecyclerAdapter(getActivity(),makingList(),R.layout.item_recycler));
        return cur_container;
    }

    private List<RecyclerItem> makingList() {
        List<RecyclerItem> items=new ArrayList<>();
        for(int i=0;i<20;i++) {
            RecyclerItem recyclerItem = new RecyclerItem("sound body, sound mind.", "-saying       ", "2015.02.16");
            items.add(recyclerItem);
        }
        return items;
    }

}
