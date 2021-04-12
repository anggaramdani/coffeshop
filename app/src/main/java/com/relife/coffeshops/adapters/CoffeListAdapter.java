package com.relife.coffeshops.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.relife.coffeshops.databinding.CustomRowShopBinding;
import com.relife.coffeshops.models.ModelProduk;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class CoffeListAdapter extends ListAdapter<ModelProduk, CoffeListAdapter.CoffeViewHoler> {

    CoffeInterface coffeInterface;

    public CoffeListAdapter(CoffeInterface coffeInterface) {
        super(ModelProduk.itemCallback);
        this.coffeInterface = coffeInterface;
    }

    @NonNull
    @Override
    public CoffeViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        CustomRowShopBinding customRowShopBinding = CustomRowShopBinding.inflate(layoutInflater, parent, false);
        customRowShopBinding.setCoffeInterface(coffeInterface);
        return new CoffeViewHoler(customRowShopBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CoffeViewHoler holder, int position) {
        ModelProduk modelProduk = getItem(position);
        holder.customRowShopBinding.setProduk(modelProduk);
        holder.customRowShopBinding.executePendingBindings();
    }

    class CoffeViewHoler extends RecyclerView.ViewHolder {

        CustomRowShopBinding customRowShopBinding;
        public CoffeViewHoler(CustomRowShopBinding binding) {
            super(binding.getRoot());
            this.customRowShopBinding = binding;
        }
    }

    public interface CoffeInterface {
        void tambahItem(ModelProduk modelProduk);
        void onItemClick(ModelProduk modelProduk);
    }

}
