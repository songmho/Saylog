package com.songmho.saylog;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by songmho on 2015-02-16.
 */
public class ListFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    String class_name;
    LinearLayout cur_container;
    SwipeRefreshLayout swiperefresh;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        cur_container=(LinearLayout)inflater.inflate(R.layout.fragment_list, container, false);
        recyclerView=(RecyclerView)cur_container.findViewById(R.id.my_recycler_view);
        swiperefresh=(SwipeRefreshLayout)cur_container.findViewById(R.id.swiperefresh);

        class_name= getClassname();

        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        swiperefresh.setColorSchemeColors(R.color.blue1, R.color.blue2, R.color.blue3, R.color.blue4);
        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                onResume();
            }
        });
        return cur_container;
    }

    @Override
    public void onResume() {
        super.onResume();
        makingList();
    }

    private String getClassname() {
        String class_name;
        SharedPreferences pref=getActivity().getSharedPreferences("login_info", Context.MODE_PRIVATE);
        class_name=pref.getString("classname","");

        return class_name;
    }

    private void makingList() {
        final List<RecyclerItem> items=new ArrayList<>();
        ParseQuery<ParseObject> road_data=ParseQuery.getQuery(class_name);
        road_data.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {
                for (int i = 0; i < parseObjects.size(); i++) {
                    if (parseObjects.get(i).getString("saying") != null) {
                        RecyclerItem item = new RecyclerItem(parseObjects.get(i).getString("saying"),
                                parseObjects.get(i).getString("source"), parseObjects.get(i).getString("date"));
                        items.add(item);
                    }
                }
                recyclerView.setAdapter(new RecyclerAdapter(getActivity(), items, R.layout.item_recycler));

                swiperefresh.setRefreshing(false);
            }
        });
    }

}
