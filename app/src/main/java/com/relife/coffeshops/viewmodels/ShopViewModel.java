package com.relife.coffeshops.viewmodels;

import com.relife.coffeshops.models.CartItem;
import com.relife.coffeshops.models.ModelProduk;
import com.relife.coffeshops.repositories.CartRepo;
import com.relife.coffeshops.repositories.CoffeRepo;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ShopViewModel extends ViewModel {

    CoffeRepo coffeRepo = new CoffeRepo();
    CartRepo cartRepo = new CartRepo();

    MutableLiveData<ModelProduk> mutableProduk = new MutableLiveData<>();

    public LiveData<List<ModelProduk>> getProduk() {
        return coffeRepo.getProduk();
    }

    public void setProduk(ModelProduk modelProduk){
        mutableProduk.setValue(modelProduk);
    }

    public LiveData<ModelProduk> getProduks(){
        return mutableProduk;
    }

    public LiveData<List<CartItem>> getCart() {
        return cartRepo.getCard();
    }

    public boolean addItemToCart(ModelProduk modelProduk) {
        return cartRepo.addItemToCart(modelProduk);
    }

    public void removeItem(CartItem cartItem) {
        cartRepo.removeItem(cartItem);
    }

    public void updateQuantity(CartItem cartItem, int quantity) {
        cartRepo.updateQuantity(cartItem, quantity);
    }

    public LiveData<Integer> getTotalHarga() {
        return cartRepo.getTotalHarga();
    }

    public void resetCart() {
        cartRepo.initCart();
    }

}
