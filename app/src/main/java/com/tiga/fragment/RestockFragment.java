package com.tiga.fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;
import com.tiga.admin.LoginActivity;
import com.tiga.admin.R;
import com.tiga.firebase.FirebaseDB;
import com.tiga.recview.model.Agen;
import com.tiga.recview.model.InvoiceItems;
import com.tiga.recview.model.InvoiceTracking;
import com.tiga.recview.model.Stok_Request;

import org.w3c.dom.Text;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by adikwidiasmono on 15/11/17.
 */

public class RestockFragment extends Fragment {

    private static final String EXTRA_TEXT = "text";

    private FirebaseRecyclerAdapter fbAdapter;
    private RecyclerView recyclerView;

    private FloatingActionButton fabAdd;

    String prodSelected = "";

    public static RestockFragment createFor(String text) {
        RestockFragment fragment = new RestockFragment();
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
        View v = inflater
                .inflate(R.layout.fragment_restock, container, false);

        recyclerView = v.findViewById(R.id.rv_request);
        fabAdd = v.findViewById(R.id.fab_add_stok);

        /*btLogout = v.findViewById(R.id.bt_logout);
        btLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                getActivity().startActivity(intent);
            }
        });*/

        return v;
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        Query query = FirebaseDatabase.getInstance()
                .getReference().child(FirebaseDB.REF_STOK_REQUEST);

        FirebaseRecyclerOptions<Stok_Request> options = new FirebaseRecyclerOptions
                .Builder<Stok_Request>()
                .setQuery(query, Stok_Request.class)
                .build();

        fbAdapter = new FirebaseRecyclerAdapter<Stok_Request, ViewHolder>(options) {
            @Override
            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.card_restock, parent, false);

                return new ViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(ViewHolder holder, int position, Stok_Request request) {
                StringBuilder sb = new StringBuilder();
                for (InvoiceItems items : request.getItems()) {
                    Picasso.with(getActivity())
                            .load(items.getImageURL())
                            .placeholder(R.drawable.ic_loop_24dp)
                            .error(R.drawable.ic_error)
                            .into(holder.ivRestock);

                    sb.append(items.getProduct() + "\n" + "QTY: " + items.getQuantity() + "\n");
                }
                holder.tvRestockDetail.setText(sb.toString());

                Timestamp stamp = new Timestamp(request.getCreateDate());
                holder.tvRestockDate.setText(new SimpleDateFormat("dd MMMM yyyy")
                        .format(new Date(stamp.getTime())));
            }
        };

        recyclerView.setAdapter(fbAdapter);

        // Set layout manager to position the items
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        // Reverse adding new data
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);

        recyclerView.setLayoutManager(mLayoutManager);

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(getActivity());
                dialog.setTitle("Tambah Item Baru");
                dialog.setContentView(R.layout.input_request);
                final EditText et_qty = view.findViewById(R.id.et_qty);
                final EditText et_date = view.findViewById(R.id.et_sendate);
                final Button btn_restock = view.findViewById(R.id.btn_stok_ulang);

                Spinner prodSpinner = view.findViewById(R.id.product_list);
                String[] prodList = loadGasProduct();
                ArrayAdapter<String> prodAdapter = new ArrayAdapter<>(getActivity()
                        .getApplicationContext(), R.layout.simple_spinner_item, prodList);
                prodAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                prodSpinner.setAdapter(prodAdapter);
                prodSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        prodSelected = (String) adapterView.getItemAtPosition(i);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                btn_restock.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String prod = prodSelected;
                        Long qty = Long.valueOf(et_qty.getText().toString());

                        Stok_Request req = new Stok_Request();
                        req.setCreateDate(new Date().getTime());
                        req.setLastStatus("Perjalanan");

                        InvoiceItems items = new InvoiceItems();
                        items.setImageURL("http://cdn2.tstatic.net/bali/foto/bank/images/elpiji-3-kg_20150924_144235.jpg");
                        items.setProduct(prod);
                        items.setQuantity(qty);
                        req.setItems((List<InvoiceItems>) items);

                        InvoiceTracking tracks = new InvoiceTracking();
                        tracks.setCreateDate(new Date().getTime());
                        tracks.setDescription("Keluar Check Point 3 dari pukul 17.00");
                        tracks.setStatus("Perjalanan");
                        tracks.setTitle("Check Point 3");
                        req.setTracking((List<InvoiceTracking>) tracks);

                        FirebaseDB.init().addRequest(req);
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
    }

    private String[] loadGasProduct() {
        return getResources().getStringArray(R.array.ld_gasProduct);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivRestock;
        private TextView tvRestockDetail, tvRestockDate;

        public ViewHolder(View itemView) {
            super(itemView);

            tvRestockDetail = itemView.findViewById(R.id.tv_restock_detail);
            tvRestockDate = itemView.findViewById(R.id.tv_restock_date);

            ivRestock = itemView.findViewById(R.id.iv_restock);
        }
    }
}