package com.tiga.admin;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.tiga.fragment.AgentFragment;


public class CheckStockActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private String city;
    private String area;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_agent);

        Intent intent = getIntent();
        city = intent.getStringExtra("City");
        area = intent.getStringExtra("Area");

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.gas_stock) + " " + city + " " + area);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        Fragment searchResult = AgentFragment.createFor(city + " " + area);
        showFragment(searchResult);

    }

    private void showFragment(Fragment fragment) {
        getFragmentManager().beginTransaction()
                .replace(R.id.search_container, fragment)
                .commit();
    }
}
