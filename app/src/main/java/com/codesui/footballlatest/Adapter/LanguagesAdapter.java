package com.codesui.footballlatest.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codesui.footballlatest.R;
import com.codesui.footballlatest.data.Language;

import java.util.List;

public class LanguagesAdapter extends RecyclerView.Adapter<LanguagesAdapter.ViewHolder> {
    private final List<Language> languageList;
    private int selectedPosition = -1;

    public LanguagesAdapter(List<Language> list) {
        this.languageList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_language, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Language language = this.languageList.get(position);
        holder.text.setText(language.getName());
        holder.radioButton.setChecked(position == selectedPosition);

        // Handle click event for the item
        holder.itemView.setOnClickListener(v -> {
            int previousPosition = selectedPosition;
            selectedPosition = holder.getAdapterPosition();
            notifyItemChanged(previousPosition);
            notifyItemChanged(selectedPosition);
        });
    }

    @Override
    public int getItemCount() {
        return this.languageList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView text;
        private final RadioButton radioButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.text = itemView.findViewById(R.id.text);
            this.radioButton = itemView.findViewById(R.id.radio_button);
        }
    }
}