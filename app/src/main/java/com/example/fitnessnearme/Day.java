package com.example.fitnessnearme;

import android.os.Parcel;
import android.os.Parcelable;

public class Day implements Parcelable {
    private int dayNumber;
    private String exerciseCount;

    public Day(int dayNumber, String exerciseCount) {
        this.dayNumber = dayNumber;
        this.exerciseCount = exerciseCount;
    }

    public int getDayNumber() {
        return dayNumber;
    }

    public void setDayNumber(int dayNumber) {
        this.dayNumber = dayNumber;
    }

    public String getExerciseCount() {
        return exerciseCount;
    }

    public void setExerciseCount(String exerciseCount) {
        this.exerciseCount = exerciseCount;
    }

    // Parcelable implementation
    protected Day(Parcel in) {
        dayNumber = in.readInt();
        exerciseCount = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(dayNumber);
        dest.writeString(exerciseCount);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Day> CREATOR = new Creator<Day>() {
        @Override
        public Day createFromParcel(Parcel in) {
            return new Day(in);
        }

        @Override
        public Day[] newArray(int size) {
            return new Day[size];
        }
    };
}
