package com.if5a.kamus.ui.home;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.if5a.kamus.adapters.KamusViewAdapter;
import com.if5a.kamus.databases.KamusHelper;
import com.if5a.kamus.databinding.FragmentHomeBinding;
import com.if5a.kamus.models.Kamus;

import java.util.ArrayList;

import kotlin.jvm.internal.markers.KMappedMarker;

public class HomeFragment extends Fragment {

    private KamusViewAdapter kamusViewAdapter;
    private FragmentHomeBinding binding;
    private KamusHelper kamusHelper;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        kamusHelper = new KamusHelper(getActivity());
        kamusViewAdapter = new KamusViewAdapter(getActivity());
        binding.rvKamus.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.rvKamus.setAdapter(kamusViewAdapter);

        getAllData();
        binding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strSearch =  binding.etSearch.getText().toString();

                if (TextUtils.isEmpty(strSearch)){
                    getAllData();
                }else{
                    kamusHelper.open();
                    ArrayList<Kamus> kamus = kamusHelper.getAllDataEnglishIndonesiaByTitle(strSearch);
                    kamusHelper.close();
                    kamusViewAdapter.setData(kamus);
                }
            }
        });

        return root;
    }

    private void getAllData() {
        kamusHelper.open();
        ArrayList<Kamus> kamus = kamusHelper.getAllDataEnglishIndonesia();
        kamusHelper.close();
        kamusViewAdapter.setData(kamus);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}