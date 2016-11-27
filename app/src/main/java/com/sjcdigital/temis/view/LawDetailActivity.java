package com.sjcdigital.temis.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;

import com.sjcdigital.temis.R;
import com.sjcdigital.temis.domain.model.LawList;
import com.sjcdigital.temis.view.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LawDetailActivity extends BaseActivity {

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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_law_detail);
        ButterKnife.bind(this);
        law = (LawList) getIntent().getExtras().getSerializable("law");
        setToolbar();
        initialize();
    }
    protected void setToolbar() {
        toolbar.setTitle(getString(R.string._law_number_field)+" "+law.getProjectLawNumber());
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    protected void initialize() {
        if (law != null) {

            tvLawNumber.setText(getString(R.string._law_number_field)+" "+law.getProjectLawNumber());
            tvData.setText(getString(R.string._data_field)+" "+law.getDate());
            tvTitle.setText(law.getTitle());

            String content;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                content = Html.fromHtml(law.getDesc(), Html.FROM_HTML_MODE_LEGACY).toString();
            } else {
                content = Html.fromHtml(law.getDesc()).toString();
            }
            tvContent.setText(content);
        }
    }

}
