package com.relife.coffeshops.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.relife.coffeshops.databinding.CustomCartRowBinding;
import com.relife.coffeshops.models.CartItem;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class CartListAdapter extends ListAdapter<CartItem, CartListAdapter.CartViewHolder> {

    private CartInterface cartInterface;
    public CartListAdapter(CartInterface cartInterface) {
        super(CartItem.itemCallback);
        this.cartInterface = cartInterface;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        CustomCartRowBinding cartRowBinding = CustomCartRowBinding.inflate(layoutInflater, parent, false);
        return new CartViewHolder(cartRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        holder.customCartRowBinding.setCartItem(getItem(position));
        holder.customCartRowBinding.executePendingBindings();
    }

    class CartViewHolder extends RecyclerView.ViewHolder {

        CustomCartRowBinding customCartRowBinding;
        public CartViewHolder(@NonNull CustomCartRowBinding cartRowBinding) {
            super(cartRowBinding.getRoot());
            this.customCartRowBinding = cartRowBinding;

            cartRowBinding.deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cartInterface.deleteItem(getItem(getAdapterPosition()));
                }
            });

            cartRowBinding.quantitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    int quantity = position + 1;
                    if (quantity == getItem(getAdapterPosition()).getQuantity()) {
                        return;
                    }
                    cartInterface.updateQuantity(getItem(getAdapterPosition()), quantity);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        }
    }

    public interface CartInterface {
        void deleteItem (CartItem cartItem);

        void updateQuantity (CartItem cartItem, int quantity);
    }

}
