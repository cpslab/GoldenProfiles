package com.elzup.goldenweekandroid.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.elzup.goldenweekandroid.R;
import com.elzup.goldenweekandroid.models.GoldenUser;

import java.util.List;

public class GoldenUserAdapter extends RecyclerView.Adapter<GoldenUserAdapter.ViewHolder> {
    private List<GoldenUser> mDataset;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView dateTextView;
        public TextView dayTextView;
        public TextView mainTextView;

        public ViewHolder(View v) {
            super(v);
            dateTextView = (TextView) v.findViewById(R.id.date_text);
            dayTextView = (TextView) v.findViewById(R.id.day_text);
            mainTextView = (TextView) v.findViewById(R.id.info_text);
        }
    }

    public GoldenUserAdapter(List<GoldenUser> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public GoldenUserAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                           int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_text_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GoldenUser user = mDataset.get(position);
        holder.dayTextView.setText(user.getName());
        holder.dateTextView.setText(user.getCurrentAnime());
        holder.mainTextView.setText(user.getFavoriteAnime());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
