package com.sjcdigital.temis.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.sjcdigital.temis.R;
import com.sjcdigital.temis.domain.model.Author;
import com.sjcdigital.temis.view.AuthorDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AuthorAdapter extends RecyclerView.Adapter<AuthorAdapter.AldermanViewHolder> {

    private List<Author> mAuthors = new ArrayList<>();
    private Context context;

    public AuthorAdapter(Context context, List<Author> authors) {
        this.mAuthors = authors;
        this.context = context;
    }

    @Override
    public void onBindViewHolder(AldermanViewHolder holder, int position) {
        Author author = mAuthors.get(position);

        if (author != null) {
            holder.tvName.setText(author.getName());
            holder.tvPartido.setText(author.getPoliticalParty());
            holder.tvLegislature.setText(author.getLegislature());
            if (!TextUtils.isEmpty(author.getPhoto())) {
                loadImage(author.getPhoto(), holder.ivPhoto);
            } else {
                holder.ivPhoto.setImageResource(R.drawable.sjc_digital);
            }
        }
    }

    @Override
    public AldermanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_alderman, parent, false);
        AldermanViewHolder viewHolder = new AldermanViewHolder(view);
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return mAuthors.size();
    }

    public class AldermanViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvPartido)
        TextView tvPartido;
        @BindView(R.id.tvLegislature)
        TextView tvLegislature;
        @BindView(R.id.ivPhoto)
        ImageView ivPhoto;
        public AldermanViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            Author author = mAuthors.get(getAdapterPosition());
            Intent intent = new Intent(context, AuthorDetailActivity.class);
            intent.putExtra("pAuthor",author);
            context.startActivity(intent);

        }
    }

    protected void loadImage(String imgUrl, ImageView ivProfile) {
        if (!TextUtils.isEmpty(imgUrl)) {
            Glide.with(context)
                    .load(imgUrl)
                    .crossFade()
                    .into(ivProfile);
        } else {
            Glide.with(context)
                    .load(R.drawable.sjc_digital)
                    .crossFade()
                    .into(ivProfile);
        }
    }
}
