package com.sjcdigital.temis.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Window;
import android.widget.TextView;

import com.sjcdigital.temis.R;
import com.sjcdigital.temis.domain.model.LawList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LawDetailActivity extends AppCompatActivity {

    @BindView(R.id.tvLawNumber)
    TextView tvLawNumber;
    @BindView(R.id.tvData)
    TextView tvData;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvContent)
    TextView tvContent;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private LawList law;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_law_detail);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        initialize();
    }

    protected void initialize() {
        law = getIntent().getExtras().getParcelable("law");
        if (law != null) {
            toolbar.setTitle(law.author.get(0).name);

            tvLawNumber.setText(getString(R.string._law_number_field)+" "+law.projectLawNumber);
            tvData.setText(getString(R.string._data_field)+" "+law.date);
            tvTitle.setText(law.title);

            String content;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                content = Html.fromHtml(law.desc, Html.FROM_HTML_MODE_LEGACY).toString();
            } else {
                content = Html.fromHtml(law.desc).toString();
            }
            tvContent.setText(content);
        }
    }

}
