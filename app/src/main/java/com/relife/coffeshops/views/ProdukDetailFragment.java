package com.relife.coffeshops.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.relife.coffeshops.R;
import com.relife.coffeshops.databinding.FragmentProdukDetailBinding;
import com.relife.coffeshops.viewmodels.ShopViewModel;

public class ProdukDetailFragment extends Fragment {

    FragmentProdukDetailBinding fragmentProdukDetailBinding;
    ShopViewModel shopViewModel;

    public ProdukDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentProdukDetailBinding = FragmentProdukDetailBinding.inflate(inflater, container, false);
        return fragmentProdukDetailBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        shopViewModel = new ViewModelProvider(requireActivity()).get(ShopViewModel.class);
        fragmentProdukDetailBinding.setShopViewModel(shopViewModel);

    }
}