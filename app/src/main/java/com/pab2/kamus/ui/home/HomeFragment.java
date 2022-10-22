package com.pab2.kamus.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.pab2.kamus.adapters.KamusViewAdapter;
import com.pab2.kamus.databases.KamusHelper;
import com.pab2.kamus.databinding.FragmentHomeBinding;
import com.pab2.kamus.models.Kamus;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private KamusViewAdapter kamusViewAdapter;
    private KamusHelper kamusHelper;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        kamusHelper = new KamusHelper(getContext());
        kamusViewAdapter = new KamusViewAdapter(getContext());

        binding.rvKamus.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvKamus.setAdapter(kamusViewAdapter);

        getAllData();

        binding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strSearch = binding.etSearch.getText().toString();

                if (TextUtils.isEmpty(strSearch)) {
                    getAllData();
                } else {
                    kamusHelper.open();
                    ArrayList<Kamus> kamusArrayList = kamusHelper.getAllDataEnglishIndonesiaByTitle(strSearch);
                    kamusHelper.close();

                    kamusViewAdapter.setKamusData(kamusArrayList);
                }
                hideKeyboard(getActivity());
            }
        });

//        final TextView textView = binding.textHome;
//        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    private void getAllData() {
        kamusHelper.open();

        ArrayList<Kamus> kamusArrayList = kamusHelper.getAllDataEnglishIndonesia();
        kamusViewAdapter.setKamusData(kamusArrayList);

        kamusHelper.close();
    }

    private void hideKeyboard(Context context){
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}