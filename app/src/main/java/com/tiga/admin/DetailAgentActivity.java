package com.tiga.admin;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.tiga.fragment.DetailAgentFragment;

public class DetailAgentActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private String agentName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_agent);

        Intent intent = getIntent();
        agentName = intent.getStringExtra("agentName");

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.detail_agent) + agentName);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        Fragment detailResult = DetailAgentFragment.createFor(agentName);
        showFragment(detailResult);
    }

    private void showFragment(Fragment fragment) {
        getFragmentManager().beginTransaction()
                .replace(R.id.detail_container, fragment)
                .commit();
    }
}
