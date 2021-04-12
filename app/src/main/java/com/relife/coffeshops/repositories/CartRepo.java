package com.relife.coffeshops.repositories;

import com.relife.coffeshops.models.CartItem;
import com.relife.coffeshops.models.ModelProduk;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class CartRepo {

    private MutableLiveData<List<CartItem>> mutableCart = new MutableLiveData<>();
    private MutableLiveData<Integer> mutableTotalHarga = new MutableLiveData<>();

    public LiveData<List<CartItem>> getCard(){
        if (mutableCart.getValue() == null){
            initCart();
        }
        return mutableCart;
    }

    public void initCart(){
        mutableCart.setValue(new ArrayList<CartItem>());
        calculateCartTotal();
    }

    public boolean addItemToCart(ModelProduk modelproduk){
        if (mutableCart.getValue() == null){
            initCart();
        }

        List<CartItem> cartItemList = new ArrayList<>(mutableCart.getValue());
        for (CartItem cartItem : cartItemList) {
            if (cartItem.getModelProduk().getId().equals(modelproduk.getId())) {
                if (cartItem.getQuantity() == 10) {
                    return false;
                }

                int index = cartItemList.indexOf(cartItem);
                cartItem.setQuantity(cartItem.getQuantity() + 1);
                cartItemList.set(index, cartItem);

                mutableCart.setValue(cartItemList);
                calculateCartTotal();
                return true;

            }
        }

        CartItem cartItem = new CartItem(modelproduk, 1);
        cartItemList.add(cartItem);
        mutableCart.setValue(cartItemList);
        calculateCartTotal();
        return true;

    }

    public void removeItem(CartItem cartItem) {
        if (mutableCart.getValue() == null){
            return;
        }
        List<CartItem> cartItemList = new ArrayList<>(mutableCart.getValue());
        cartItemList.remove(cartItem);
        mutableCart.setValue(cartItemList);
        calculateCartTotal();
    }

    public void updateQuantity(CartItem cartItem, int quantity) {
        if (mutableCart.getValue() == null)
            return;

        List<CartItem> cartItemList = new ArrayList<>(mutableCart.getValue());

        CartItem updateItem = new CartItem(cartItem.getModelProduk(), quantity);
        cartItemList.set(cartItemList.indexOf(cartItem), updateItem);
        mutableCart.setValue(cartItemList);
        calculateCartTotal();
    }

    private void calculateCartTotal() {
        if (mutableCart.getValue() == null) return;
        int total = 0;
        List<CartItem> cartItemList = mutableCart.getValue();

        for (CartItem cartItem : cartItemList) {
            total += cartItem.getModelProduk().getHarga() * cartItem.getQuantity();
        }
        mutableTotalHarga.setValue(total);
    }

    public LiveData<Integer> getTotalHarga() {
        if (mutableTotalHarga.getValue() == null) {
            mutableTotalHarga.setValue(0);
        }
        return mutableTotalHarga;
    }

}
