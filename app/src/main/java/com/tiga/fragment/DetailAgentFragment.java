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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;
import com.tiga.admin.R;
import com.tiga.admin.TransactionHistoryActivity;
import com.tiga.recview.model.Agen;


public class DetailAgentFragment extends Fragment {

    private FirebaseRecyclerAdapter fbAdapter;
    private RecyclerView recyclerView;

    private String strAgentName;

    public static DetailAgentFragment createFor(String text) {
        DetailAgentFragment fragment = new DetailAgentFragment();
        Bundle args = new Bundle();
        args.putString("text", text);
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
        View v = inflater.inflate(R.layout.fragment_detail, container, false);

        recyclerView = v.findViewById(R.id.rv_detail_agen);
        Intent intent = getActivity().getIntent();
        strAgentName = intent.getStringExtra("AgentName");
        System.out.println("agent name: " + strAgentName);

        return v;
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        Query query = FirebaseDatabase.getInstance()
                .getReference().child("AGEN")
                .orderByChild("AgentName")
                .equalTo(strAgentName);

        FirebaseRecyclerOptions<Agen> options = new FirebaseRecyclerOptions
                .Builder<Agen>()
                .setQuery(query, Agen.class)
                .build();

        fbAdapter = new FirebaseRecyclerAdapter<Agen, ViewHolder>(options) {
            @Override
            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.card_agent_detail, parent, false);

                return new ViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(ViewHolder holder, int position, final Agen agen) {
                holder.tvAgentName.setText(agen.getAgentName());
                holder.tvAgentStatus.setText(agen.getStatus());
                holder.tvStokElpiji3Kg.setText(agen.getStokElpiji3Kg()+"");
                holder.tvStokElpiji12Kg.setText(agen.getStokElpiji12Kg()+"");
                holder.tvStokBright5Kg.setText(agen.getStokBright5Kg()+"");
                holder.tvStokBright12Kg.setText(agen.getStokBright12Kg()+"");
                holder.tvStokEase3Kg.setText(agen.getStokEase14Kg()+"");
                holder.tvStokEase9Kg.setText(agen.getStokEase9Kg()+"");

                Picasso.with(getActivity())
                        .load(agen.getImageURL())
                        .placeholder(R.drawable.ic_loop_24dp)
                        .error(R.drawable.ic_error)
                        .into(holder.ivAgentPic);

                holder.btn_history.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), TransactionHistoryActivity.class);
                        intent.putExtra("AgentName", agen.getAgentName());
                        intent.putExtra("AgentId", agen.getAgentId());
                        getActivity().startActivity(intent);

                    }
                });
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
        public TextView tvAgentName, tvAgentStatus,
                tvStokBright12Kg, tvStokBright5Kg,
                tvStokEase3Kg, tvStokEase9Kg,
                tvStokElpiji12Kg, tvStokElpiji3Kg;
        public ImageView ivAgentPic;
        public Button btn_history;

        public ViewHolder(View itemView) {
            super(itemView);

            tvAgentName = itemView.findViewById(R.id.tv_namaAgen);
            tvAgentStatus = itemView.findViewById(R.id.tv_statusAgen);
            tvStokBright5Kg = itemView.findViewById(R.id.tv_bright5);
            tvStokBright12Kg = itemView.findViewById(R.id.tv_bright12);
            tvStokEase3Kg = itemView.findViewById(R.id.tv_ease3);
            tvStokEase9Kg = itemView.findViewById(R.id.tv_ease9);
            tvStokElpiji3Kg = itemView.findViewById(R.id.tv_elpiji3);
            tvStokElpiji12Kg = itemView.findViewById(R.id.tv_elpiji12);
            ivAgentPic = itemView.findViewById(R.id.iv_agenPic);
            btn_history = itemView.findViewById(R.id.btn_history);
        }
    }
}