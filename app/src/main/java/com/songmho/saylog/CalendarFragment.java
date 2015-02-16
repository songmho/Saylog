package com.songmho.saylog;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.LinearLayout;

/**
 * Created by songmho on 2015-02-16.
 */
public class CalendarFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LinearLayout cur_container=(LinearLayout)inflater.inflate(R.layout.fragment_calendar, container, false);
        CalendarView calendarView=(CalendarView)cur_container.findViewById(R.id.calendarview);
        return cur_container;
    }
}
