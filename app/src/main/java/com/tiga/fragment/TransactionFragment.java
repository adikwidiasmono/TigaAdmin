package com.tiga.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;
import com.tiga.admin.R;
import com.tiga.recview.model.Penjualan;
import com.tiga.recview.model.Item;
import com.tiga.recview.model.TransactionItems;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TransactionFragment extends Fragment {

    private FirebaseRecyclerAdapter fbAdapter;
    private RecyclerView recyclerView;

    private String strAgentId;




    public static TransactionFragment createFor(String text) {
        TransactionFragment fragment = new TransactionFragment();
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
        View v = inflater.inflate(R.layout.fragment_transaction, container, false);

        recyclerView = v.findViewById(R.id.rv_transaction);
        Intent intent = getActivity().getIntent();
        strAgentId = intent.getStringExtra("AgentId");

        return v;
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        Query query = FirebaseDatabase.getInstance()
                .getReference().child("PENJUALAN")
                .orderByChild("AgentId")
                .equalTo(strAgentId);

        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions
                .Builder<Penjualan>()
                .setQuery(query, Penjualan.class)
                .build();

        fbAdapter = new FirebaseRecyclerAdapter<Penjualan, ViewHolder>(options) {
            @Override
            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.card_transaction, parent, false);

                return new ViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(ViewHolder holder, int position, Penjualan penjualan) {
                StringBuilder sb = new StringBuilder();
                for (TransactionItems ti : penjualan.getItems()) {
                    Picasso.with(getActivity())
                            .load(ti.getImageURL())
                            .placeholder(R.drawable.ic_loop_24dp)
                            .error(R.drawable.ic_error)
                            .into(holder.ivItem);

                    sb.append(ti.getQuantity() + " unit " + ti.getProduct() + "\n Rp " + ti.getPrice() + "\n");
                }
                holder.tvTransDetail.setText(sb.toString());

                Timestamp stamp = new Timestamp(penjualan.getTransactionDate());
                holder.tvTransDate.setText(new SimpleDateFormat("dd MMMM yyyy")
                        .format(new Date(stamp.getTime())));
                holder.tvTransDetail.setText(sb.toString());

                if (penjualan.getKKSOwner()!=null) {
                    holder.tvItemType.setText(getResources().getString(R.string.pso));
                } else holder.tvItemType.setText(getResources().getString(R.string.non_pso));

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


    private class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvItemType, tvTransDetail, tvTransDate;
        private ImageView ivItem;

        public ViewHolder(View itemView) {
            super(itemView);

            tvItemType = itemView.findViewById(R.id.tv_item_type);
            tvTransDetail = itemView.findViewById(R.id.tv_trans_detail);
            tvTransDate = itemView.findViewById(R.id.tv_trans_date);

            ivItem = itemView.findViewById(R.id.iv_item);

        }
    }
}
