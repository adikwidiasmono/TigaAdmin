package com.tiga.admin;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.tiga.fragment.TransactionFragment;


public class TransactionHistoryActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        Intent intent = getIntent();
        String agentId = intent.getStringExtra("AgentId");
        String agentName = intent.getStringExtra("AgentName");

        System.out.print("Agent ID: " + agentId);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.history_trans));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        Fragment transaction = TransactionFragment.createFor(agentId);
        showFragment(transaction);
    }

    private void showFragment(Fragment fragment) {
        getFragmentManager().beginTransaction()
                .replace(R.id.history_container, fragment)
                .commit();
    }
}
