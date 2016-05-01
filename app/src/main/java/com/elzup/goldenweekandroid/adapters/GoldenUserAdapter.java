package com.elzup.goldenweekandroid.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.elzup.goldenweekandroid.R;
import com.elzup.goldenweekandroid.models.GoldenUser;

import java.util.List;

public class GoldenUserAdapter extends RecyclerView.Adapter<GoldenUserAdapter.ViewHolder> {
    private List<GoldenUser> mDataset;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public TextView idTextView;
        public TextView currentAnimeTextView;
        public TextView favoriteAnimeTextView;
        public TextView languageTextView;
        public ImageView iconImageView;

        public ViewHolder(View v) {
            super(v);
            nameTextView = (TextView) v.findViewById(R.id.user_name);
            idTextView = (TextView) v.findViewById(R.id.user_id);
            currentAnimeTextView = (TextView) v.findViewById(R.id.user_c_anime);
            favoriteAnimeTextView = (TextView) v.findViewById(R.id.user_f_anime);
            languageTextView = (TextView) v.findViewById(R.id.user_language);
            iconImageView = (ImageView) v.findViewById(R.id.user_icon);
        }
    }

    public GoldenUserAdapter(List<GoldenUser> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public GoldenUserAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                           int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.golden_user_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GoldenUser user = mDataset.get(position);
        holder.nameTextView.setText(user.getName());
        holder.idTextView.setText(user.getDisplayID());
        holder.currentAnimeTextView.setText( "　試聴アニメ: " + user.getCurrentAnime());
        holder.favoriteAnimeTextView.setText("好きなアニメ: " + user.getFavoriteAnime());
        holder.languageTextView.setText(     "　　　　言語: " + user.getLanguage());
        Glide.with(holder.iconImageView.getContext()).load(user.getImgURL()).into(holder.iconImageView);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
