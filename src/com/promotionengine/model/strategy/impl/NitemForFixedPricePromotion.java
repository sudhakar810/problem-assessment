package com.promotionengine.model.strategy.impl;

import com.promotionengine.model.Cart;
import com.promotionengine.model.CartItem;
import com.promotionengine.model.strategy.PromotionRule;
import com.promotionengine.model.strategy.exception.ArgumentException;

public class NitemForFixedPricePromotion extends PromotionRule {
        
        public final String SKU;
        public final int numberOfItems;
        public final int fixedPrice;
        
        
        public NitemForFixedPricePromotion(String sku, int numberOfItems, int fixedPrice) throws ArgumentException {
        	super(sku);
            if (sku.isBlank()) {
                throw new ArgumentException("Invalid or missing SKU!");
            }
            
            if ((numberOfItems <= 0)) {
                throw new ArgumentException("Invalid number of items in promotion rule! It must be grater than zero!");
            }
            
            if ((fixedPrice <= 0)) {
                throw new ArgumentException("Invalid number for fixed price in promotion rule! It must be grater than zero!");
            }
            
            this.SKU = sku;
            this.numberOfItems = numberOfItems;
            this.fixedPrice = fixedPrice;
        }
        
        @Override
        public void execute(Cart cart) {
            var discountItemPrice = (this.fixedPrice / this.numberOfItems);
            var residue = 0;
            while (this.isApplicable(cart)) {
                residue = (this.fixedPrice 
                            - (this.numberOfItems * discountItemPrice));
//                for (var item : cart.Items.Where(() => {  }, (!i.promotionApplied 
//                                && this.SKU.equals(i.Item.ID))).Take(this.NumberOfItems)) {
//                    item.FinalPrice = (discountItemPrice + residue);
//                    item.PromotionApplied = true;
//                    residue = 0;
//                }
                
                for(int i=0;i<cart.getItems().size();i++) {
                //for(CartItem item : cart.getItems()) {
                	CartItem item = cart.getItems().get(i);
                	if(!item.getPromotionApplied() && this.SKU.equals(item.getItem().getId())) {
                		item.setFinalPrice(discountItemPrice + residue);
                		item.setPromotionApplied(true);
                		residue = 0;
                	}
                
            }
            
        }
        
    }
        @Override
        public Boolean isApplicable(Cart cart) {
//            return (!IsEmptyCart(cart) 
//                        && (cart.Items.Where(() => {  }, (!i.PromotionApplied 
//                            && this.SKU.Equals(i.Item.ID))).Count() >= this.NumberOfItems));
            return !isEmptyCart(cart) && cart.getItems().stream().filter(i->!i.getPromotionApplied() &&  this.SKU.equals(i.getItem().getId())).count()>=this.numberOfItems;
        }

}