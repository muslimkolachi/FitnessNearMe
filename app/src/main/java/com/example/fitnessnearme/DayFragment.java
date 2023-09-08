package com.example.fitnessnearme;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

public class DayFragment extends Fragment {
    private Day day;

    public DayFragment() {
        // Required empty public constructor
    }

    public static DayFragment newInstance(Day day) {
        DayFragment fragment = new DayFragment();
        Bundle args = new Bundle();
        args.putParcelable("day", day);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            day = getArguments().getParcelable("day");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.day_card, container, false);

        TextView dayNumberTextView = view.findViewById(R.id.dayNumberTextView);
        TextView exerciseCountTextView = view.findViewById(R.id.exerciseCountTextView);

        if (day != null) {
            dayNumberTextView.setText("Day " + day.getDayNumber());
            exerciseCountTextView.setText(day.getExerciseCount());
        } else {
            // Handle the case where 'day' is null, such as displaying a default message.
            dayNumberTextView.setText("Day N/A");
            exerciseCountTextView.setText("Exercise Count N/A");
        }

        return view;
    }
}
