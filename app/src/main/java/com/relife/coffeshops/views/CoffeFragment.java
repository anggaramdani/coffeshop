package com.relife.coffeshops.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.relife.coffeshops.R;
import com.relife.coffeshops.adapters.CoffeListAdapter;
import com.relife.coffeshops.databinding.FragmentCoffeBinding;
import com.relife.coffeshops.models.ModelProduk;
import com.relife.coffeshops.viewmodels.ShopViewModel;

import java.util.List;

public class CoffeFragment extends Fragment implements CoffeListAdapter.CoffeInterface {

    private static final String TAG = "CoffeFragment";
    FragmentCoffeBinding fragmentCoffeBinding;
    private CoffeListAdapter coffeListAdapter;
    private ShopViewModel shopViewModel;
    private NavController navController;

    public CoffeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentCoffeBinding = FragmentCoffeBinding.inflate(inflater, container, false);
        return fragmentCoffeBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        coffeListAdapter = new CoffeListAdapter(this);
        fragmentCoffeBinding.coffeRecyclerView.setAdapter(coffeListAdapter);
        fragmentCoffeBinding.coffeRecyclerView.addItemDecoration(new DividerItemDecoration(requireContext(),
                DividerItemDecoration.VERTICAL));
        fragmentCoffeBinding.coffeRecyclerView.addItemDecoration(new DividerItemDecoration(requireContext(),
                DividerItemDecoration.HORIZONTAL));

        shopViewModel = new ViewModelProvider(requireActivity()).get(ShopViewModel.class);
        shopViewModel.getProduk().observe(getViewLifecycleOwner(), new Observer<List<ModelProduk>>() {
            @Override
            public void onChanged(List<ModelProduk> modelProduks) {
                coffeListAdapter.submitList(modelProduks);
            }
        });

        navController = Navigation.findNavController(view);

    }

    @Override
    public void tambahItem(ModelProduk modelProduk) {
        boolean isAdded = shopViewModel.addItemToCart(modelProduk);
        if (isAdded){
            Toast.makeText(requireActivity(), modelProduk.getNama() + " Telah ditambahkan", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(requireActivity(), "Max Quantity", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onItemClick(ModelProduk modelProduk) {
        shopViewModel.setProduk(modelProduk);
        navController.navigate(R.id.action_coffeFragment_to_produkDetailFragment);
    }
}