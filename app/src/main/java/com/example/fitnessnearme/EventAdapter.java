package com.example.fitnessnearme;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    private List<Event> eventList;

    public EventAdapter(List<Event> eventList) {
        this.eventList = eventList;
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
        private TextView titleTextView;
        private TextView dateTextView;
        private TextView venueTextView;
        private TextView priceTextView;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.eventTitleTextView);
            dateTextView = itemView.findViewById(R.id.eventDateTextView);
            venueTextView = itemView.findViewById(R.id.eventVenueTextView);
            priceTextView = itemView.findViewById(R.id.eventPriceTextView);
        }

        public void bind(Event event) {
            titleTextView.setText(event.getTitle());
            dateTextView.setText(event.getDate());
            venueTextView.setText(event.getVenue());
            priceTextView.setText(String.valueOf(event.getPrice()));
        }

    }
}
