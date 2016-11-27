package com.sjcdigital.temis.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.sjcdigital.temis.R;
import com.sjcdigital.temis.domain.model.LawList;
import com.sjcdigital.temis.view.LawDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bruno.santiago on 17/09/2016.
 */
public class LawsAdapter extends RecyclerView.Adapter<LawsAdapter.LawViewHolder> {

    private List<LawList> lawsList = new ArrayList<>();
    private Context context;


    public LawsAdapter(Context context, List<LawList> lawsList) {
        this.lawsList = lawsList;
        this.context = context;
    }

    @Override
    public LawViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_law, parent, false);
        LawViewHolder viewHolder = new LawViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(LawViewHolder holder, int position) {
        LawList laws = lawsList.get(position);
        if (laws != null) {
            String title = laws.getSummary();

            if (title != null)
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    title = Html.fromHtml(title, Html.FROM_HTML_MODE_LEGACY).toString();
                } else {
                    title = Html.fromHtml(title).toString();
                }

            holder.tvTitle.setText(title);
            holder.tvData.setText(laws.getDate());
            holder.tvTag.setText(laws.getType());
           holder.tvAuthor.setText(laws.getmAuthor().getName());
        }
    }

    @Override
    public int getItemCount() {
        if (lawsList != null) {
            return lawsList.size();
        }
        return 0;
    }

    public class LawViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tvTitle)
        TextView tvTitle;
        @BindView(R.id.tvData)
        TextView tvData;
        @BindView(R.id.tvTag)
        TextView tvTag;
        @BindView(R.id.tvAuthor)
        TextView tvAuthor;

        public LawViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            LawList law = lawsList.get(getAdapterPosition());
            Intent intent = new Intent(context, LawDetailActivity.class);
            intent.putExtra("law", law);
            context.startActivity(intent);
        }
    }
}
