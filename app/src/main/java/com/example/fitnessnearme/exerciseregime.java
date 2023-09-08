package com.example.fitnessnearme;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;

public class exerciseregime extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exerciseregime);

        ViewPager viewPager = findViewById(R.id.viewPager);
        ExercisePagerAdapter pagerAdapter = new ExercisePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
    }

    private class ExercisePagerAdapter extends FragmentPagerAdapter {
        ExercisePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // Return the appropriate fragment for each difficulty level
            switch (position) {
                case 0:
                    return BeginnerExerciseFragment.newInstance();
                case 1:
                    return IntermediateExerciseFragment.newInstance();
                case 2:
                    return ExpertExerciseFragment.newInstance();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 3; // Three difficulty levels
        }

        @Override
        public CharSequence getPageTitle(int position) {
            // Set tab titles for each difficulty level
            switch (position) {
                case 0:
                    return "Beginner";
                case 1:
                    return "Intermediate";
                case 2:
                    return "Expert";
                default:
                    return "";
            }
        }
    }
}
