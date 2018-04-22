package com.tiga.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tiga.admin.LoginActivity;
import com.tiga.admin.R;
import com.tiga.firebase.FirebaseDB;
import com.tiga.recview.model.Agen;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.Date;

/**
 * Created by adikwidiasmono on 15/11/17.
 */

public class AccountFragment extends Fragment {

    private static final String EXTRA_TEXT = "text";

    private FirebaseRecyclerAdapter fbAdapter;
    private RecyclerView recyclerView;

    private TextView btLogout;

    public static AccountFragment createFor(String text) {
        AccountFragment fragment = new AccountFragment();
        Bundle args = new Bundle();
        args.putString(EXTRA_TEXT, text);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        fbAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        fbAdapter.stopListening();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_account, container, false);

        recyclerView = v.findViewById(R.id.rv_agen);

        btLogout = v.findViewById(R.id.bt_logout);
        btLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                getActivity().startActivity(intent);
            }
        });

        return v;
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        Query query = FirebaseDatabase.getInstance()
                .getReference().child(FirebaseDB.REF_AGEN)
                .orderByChild("CreateDate")
                .startAt(-1 * new Date().getTime());

        FirebaseRecyclerOptions<Agen> options = new FirebaseRecyclerOptions
                .Builder<Agen>()
                .setQuery(query, Agen.class)
                .build();

        fbAdapter = new FirebaseRecyclerAdapter<Agen, ViewHolder>(options) {
            @Override
            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_agent, parent, false);

                return new ViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(ViewHolder holder, int position, Agen agen) {
                Picasso.with(getActivity())
                        .load(agen.getImageURL())
                        .placeholder(R.drawable.ic_loop_24dp)
                        .error(R.drawable.ic_error)
                        .into(holder.ivAgen);

                holder.tvName.setText(agen.getAgentName());
                holder.tvStatus.setText(agen.getStatus());
                holder.tvAddress.setText(agen.getAgentAddress());
                holder.tvAgentId.setText(agen.getAgentId());
            }
        };

        recyclerView.setAdapter(fbAdapter);

        // Set layout manager to position the items
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        // Reverse adding new data
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);

        recyclerView.setLayoutManager(mLayoutManager);


    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName, tvStatus, tvAddress, tvAgentId;
        private ImageView ivAgen;

        public ViewHolder(View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_agent_name);
            tvAddress = itemView.findViewById(R.id.tv_agent_address);
            tvStatus = itemView.findViewById(R.id.tv_agent_status);
            tvAgentId = itemView.findViewById(R.id.tv_agent_id);

            ivAgen = itemView.findViewById(R.id.iv_agen_pic);
        }
    }
}