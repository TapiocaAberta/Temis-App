package com.sjcdigital.temis.view;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;

import com.sjcdigital.temis.R;
import com.sjcdigital.temis.domain.api.ITemis;
import com.sjcdigital.temis.domain.api.TemisApi;
import com.sjcdigital.temis.domain.model.Author;
import com.sjcdigital.temis.domain.model.LawList;
import com.sjcdigital.temis.domain.service.LawsService;
import com.sjcdigital.temis.view.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LawDetailActivity extends BaseActivity {

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvContent)
    TextView tvContent;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ratingBar)
    RatingBar ratingBar;
    private LawList law;
    private LawsService lawsService;
    private ITemis iTemis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_law_detail);
        ButterKnife.bind(this);
        law = (LawList) getIntent().getExtras().getSerializable("law");
        setToolbar();
        initialize();
        lawsService = new LawsService(this);
        iTemis = TemisApi.getRetrofit().create(ITemis.class);

        ratingBar.setOnRatingBarChangeListener((bar, rating, fromUser) -> {
            //avaliacao((int)rating);
        });

    }

    protected void setToolbar() {
        toolbar.setTitle(getString(R.string._law_number_field));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    protected void initialize() {
        if (law != null) {
            tvTitle.setText(law.getTitle());

            String content;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                content = Html.fromHtml(law.getDesc(), Html.FROM_HTML_MODE_LEGACY).toString();
            } else {
                content = Html.fromHtml(law.getDesc()).toString();
            }
            tvContent.setText(content);
            ratingBar.setRating(law.getRating());
        }
    }

}
