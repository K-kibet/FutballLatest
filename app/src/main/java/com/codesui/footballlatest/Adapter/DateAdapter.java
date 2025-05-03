package com.codesui.footballlatest.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codesui.footballlatest.R;
import com.codesui.footballlatest.data.DateModel;

import java.util.ArrayList;
import java.util.Date;

public class DateAdapter extends RecyclerView.Adapter<DateAdapter.DateViewHolder> {
    private final ArrayList<DateModel> dates;
    private int selectedPosition;

    public DateAdapter(ArrayList<DateModel> dates, int defaultSelectedPosition) {
        this.dates = dates;
        this.selectedPosition = defaultSelectedPosition;
    }

    // Listener interface
    public interface OnDateSelectedListener {
        void onDateSelected(Date selectedDate);
    }

    private OnDateSelectedListener listener;

    public void setOnDateSelectedListener(OnDateSelectedListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public DateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_date, parent, false);
        return new DateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DateViewHolder holder, int position) {
        DateModel dateModel = dates.get(position);

        holder.dateText.setText(dateModel.getDayNumber());
        holder.dayText.setText(dateModel.getDayName());

        if (position == selectedPosition) {
            holder.innerLayout.setBackgroundResource(R.drawable.selected_background);
        } else {
            holder.innerLayout.setBackground(null); // only reset the inner layout background
        }

        holder.itemView.setOnClickListener(v -> {
            int adapterPosition = holder.getAdapterPosition();  // Use getAdapterPosition() here
            if (adapterPosition == RecyclerView.NO_POSITION) return; // Check if the item is no longer valid

            int previousSelected = selectedPosition;
            selectedPosition = adapterPosition;

            notifyItemChanged(previousSelected);
            notifyItemChanged(selectedPosition);

            if (listener != null) {
                listener.onDateSelected(dateModel.getFullDate());
            }
        });
    }


    @Override
    public int getItemCount() {
        return dates.size();
    }

    static class DateViewHolder extends RecyclerView.ViewHolder {
        TextView dateText, dayText;
        View innerLayout;
        public DateViewHolder(@NonNull View itemView) {
            super(itemView);
            dateText = itemView.findViewById(R.id.dateText);
            dayText = itemView.findViewById(R.id.dayText);
            innerLayout = itemView.findViewById(R.id.cardInnerLayout);
        }
    }
}
