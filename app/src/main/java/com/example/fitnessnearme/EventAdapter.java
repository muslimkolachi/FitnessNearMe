package com.example.fitnessnearme;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    private List<Event> eventList;
    private boolean isSortedByDate; // Flag to check if the list is sorted by date
    private boolean isSortedByTitle; // Flag to check if the list is sorted by title

    public EventAdapter(List<Event> eventList) {
        this.eventList = eventList;
        isSortedByDate = false;
        isSortedByTitle = false;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event, parent, false);
        return new EventViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        Event event = eventList.get(position);
        holder.bind(event);
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public class EventViewHolder extends RecyclerView.ViewHolder {
        private ImageView eventImageView;
        private TextView eventTitleTextView;
        private TextView eventDateTextView;
        private TextView eventVenueTextView;
        private TextView eventPriceTextView;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            eventImageView = itemView.findViewById(R.id.eventImageView);
            eventTitleTextView = itemView.findViewById(R.id.eventTitleTextView);
            eventDateTextView = itemView.findViewById(R.id.eventDateTextView);
            eventVenueTextView = itemView.findViewById(R.id.eventVenueTextView);
            eventPriceTextView = itemView.findViewById(R.id.eventPriceTextView);
        }

        public void bind(Event event) {
            eventTitleTextView.setText(event.getTitle());
            eventDateTextView.setText(event.getDate());
            eventVenueTextView.setText(event.getVenue());
            eventPriceTextView.setText(String.valueOf(event.getPrice()));

            // Load the image using Picasso
            Picasso.get()
                    .load(event.getImageUrl())
                    .into(eventImageView);
        }
    }

    // Method to sort events by date
    public void sortByDate() {
        isSortedByDate = !isSortedByDate;
        isSortedByTitle = false; // Reset title sorting flag
        if (isSortedByDate) {
            Collections.sort(eventList, new Comparator<Event>() {
                @Override
                public int compare(Event event1, Event event2) {
                    // Compare event dates and return the result
                    return event1.getDate().compareTo(event2.getDate());
                }
            });
        } else {
            // If already sorted, reverse the list to show in descending order
            Collections.reverse(eventList);
        }
        // Notify the adapter of the data change
        notifyDataSetChanged();
    }

    // Method to sort events by title
    public void sortByTitle() {
        isSortedByTitle = !isSortedByTitle;
        isSortedByDate = false; // Reset date sorting flag
        if (isSortedByTitle) {
            Collections.sort(eventList, new Comparator<Event>() {
                @Override
                public int compare(Event event1, Event event2) {
                    // Compare event titles and return the result
                    return event1.getTitle().compareTo(event2.getTitle());
                }
            });
        } else {
            // If already sorted, reverse the list to show in descending order
            Collections.reverse(eventList);
        }
        // Notify the adapter of the data change
        notifyDataSetChanged();
    }
}
