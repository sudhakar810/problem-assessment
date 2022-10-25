package com.promotionengine.model.strategy;

import java.util.List;

import com.promotionengine.model.Cart;
import com.promotionengine.model.SKUitem;
import com.promotionengine.model.strategy.exception.ArgumentException;

public interface IStore
    {
        public Store addSKUitem(SKUitem item);
        public Store addPromotions(List<PromotionRule> promotions);
        public Store addPromotion(PromotionRule promotion);
        public Store addPromotion(String promotion) throws ArgumentException;
        public Store addItemToCart(String itemSKU) throws ArgumentException;
        public void deletePromotion(String promotion) throws ArgumentException;
        public Store emptyCart();
        public Store checkout();
        public void deleteItemFromCart(String sku) throws ArgumentException;
        public float getCartTotal();
        public List<SKUitem> getSKUitems();
        public void updateSKUitemUnitPrice(String sku, float price) throws ArgumentException;
        public void deleteSKUitem(String sku) throws ArgumentException;
        public List<PromotionRule> getPromotions();
        public List<SKUitem> getAllItems();
        public SKUitem getItem(String sku) throws ArgumentException;
        public Cart getCart();

    }