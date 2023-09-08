package com.example.fitnessnearme;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import java.util.ArrayList;
import java.util.List;

public class DayPagerAdapter extends FragmentPagerAdapter {
    private List<Day> days = new ArrayList<>();

    public DayPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        // Populate the 'days' list with Day objects for each day
        for (int dayNumber = 1; dayNumber <= 30; dayNumber++) {
            String exerciseCount = "15 exercises"; // Replace with the actual exercise count
            days.add(new Day(dayNumber, exerciseCount));
        }
    }

    @Override
    public Fragment getItem(int position) {
        // Create a new DayFragment for the given day
        return DayFragment.newInstance(days.get(position));
    }

    @Override
    public int getCount() {
        return days.size();
    }
}
