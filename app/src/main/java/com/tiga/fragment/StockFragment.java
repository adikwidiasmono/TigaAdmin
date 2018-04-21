package com.tiga.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.tiga.admin.R;
import com.tiga.admin.CheckStockActivity;

public class StockFragment extends Fragment {
    private static final int JAKARTA = 0;
    private static final int BANTEN = 1;
    private static final int JAWABARAT = 2;
    private static final int JAWATENGAH = 3;
    private static final int JAWATIMUR = 4;
    private static final int YOGYAKARTA = 5;


    private String[] provinceList;
    private String[] cityList;
    private String[] areaList;

    private Spinner provSpinner;
    private Spinner citySpinner;
    private Spinner areaSpinner;

    private ArrayAdapter<String> provinceAdapter;
    private ArrayAdapter<String> cityAdapter;
    private ArrayAdapter<String> areaAdapter;

    private int provId;
    private int cityId;
    private int areaId;

    String provSelected;
    String citySelected;
    String areaSelected;

    private Button btCekStok;

    public static StockFragment createFor(String text) {
        StockFragment fragment = new StockFragment();
        Bundle args = new Bundle();
        args.putString("text", text);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search, container, false);
        btCekStok = v.findViewById(R.id.bt_cekStok);

        provSpinner = v.findViewById(R.id.province_list);
        citySpinner = v.findViewById(R.id.city_list);
        areaSpinner = v.findViewById(R.id.area_list);

        return v;
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {

        provinceList = loadProvinceList();
        provinceAdapter = new ArrayAdapter<>(getActivity().getApplicationContext(), R.layout.simple_spinner_item, provinceList);
        provinceAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        provSpinner.setAdapter(provinceAdapter);
        provSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                provSelected = (String) adapterView.getItemAtPosition(i);

                citySpinner.setEnabled(true);
                switch (provSelected) {
                    case "DKI Jakarta": {
                        provId = JAKARTA;
                        populateCity();
                        break;
                    }
                    case "Banten": {
                        provId = BANTEN;
                        populateCity();
                        break;
                    }
                    case "Jawa Barat": {
                        provId = JAWABARAT;
                        populateCity();
                        break;
                    }
                    case "Jawa Tengah": {
                        provId = JAWATENGAH;
                        populateCity();
                        break;
                    }
                    case "Jawa Timur": {
                        provId = JAWATIMUR;
                        populateCity();
                        break;
                    }
                    case "DI Yogyakarta": {
                        provId = YOGYAKARTA;
                        populateCity();
                        break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                citySelected = (String) adapterView.getItemAtPosition(i);
                cityId = (int) adapterView.getItemIdAtPosition(i);

                populateArea();
                areaSpinner.setAdapter(areaAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        areaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                areaSelected = (String) adapterView.getItemAtPosition(i);
                areaId = (int) adapterView.getItemIdAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btCekStok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CheckStockActivity.class);
                intent.putExtra("City", citySelected);
                intent.putExtra("Area", areaSelected);
                getActivity().startActivity(intent);
                getActivity().finish();
            }
        });
    }

    private String[] loadProvinceList() {
        return getResources().getStringArray(R.array.ld_provinceList);
    }

    private String[] loadJakartaCity() {
        return getResources().getStringArray(R.array.ld_JakartaCity);
    }

    private String[] loadBantenCity() {
        return getResources().getStringArray(R.array.ld_BantenCity);
    }

    private String[] loadJabarCity() {
        return getResources().getStringArray(R.array.ld_JawaBaratCity);
    }

    private String[] loadJatengCity() {
        return getResources().getStringArray(R.array.ld_JawaTengahCity);
    }

    private String[] loadJatimCity() {
        return getResources().getStringArray(R.array.ld_JawaTimurCity);
    }

    private String[] loadYogyaCity() {
        return getResources().getStringArray(R.array.ld_YogyaCity);
    }

    private String[] loadAreaList() {
        return getResources().getStringArray(R.array.ld_areaList);
    }

    void populateCity() {
        switch (provId) {
            case 0: {
                cityList = loadJakartaCity();
                cityAdapter = new ArrayAdapter<>(getActivity().getApplicationContext(), R.layout.simple_spinner_item, cityList);
                break;
            }
            case 1: {
                cityList = loadBantenCity();
                cityAdapter = new ArrayAdapter<>(getActivity().getApplicationContext(), R.layout.simple_spinner_item, cityList);
                break;
            }
            case 2: {
                cityList = loadJabarCity();
                cityAdapter = new ArrayAdapter<>(getActivity().getApplicationContext(), R.layout.simple_spinner_item, cityList);
                break;
            }
            case 3: {
                cityList = loadJatengCity();
                cityAdapter = new ArrayAdapter<>(getActivity().getApplicationContext(), R.layout.simple_spinner_item, cityList);
                break;
            }
            case 4: {
                cityList = loadJatimCity();
                cityAdapter = new ArrayAdapter<>(getActivity().getApplicationContext(), R.layout.simple_spinner_item, cityList);
                break;
            }
            case 5: {
                cityList = loadYogyaCity();
                cityAdapter = new ArrayAdapter<>(getActivity().getApplicationContext(), R.layout.simple_spinner_item, cityList);
                break;
            }
        }
        citySpinner.setAdapter(cityAdapter);
    }

    void populateArea() {
        areaList = loadAreaList();
        areaAdapter = new ArrayAdapter<>(getActivity().getApplicationContext(), R.layout.simple_spinner_item, areaList);
    }
}