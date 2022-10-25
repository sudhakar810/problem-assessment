package com.promotionengine.model.strategy.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.promotionengine.model.Cart;
import com.promotionengine.model.CartItem;
import com.promotionengine.model.strategy.PromotionRule;
import com.promotionengine.model.strategy.exception.ArgumentException;

public class CombinedItemFixedPricePromotion extends PromotionRule {
        
        public final List<String> SKUs;
        
        public final int fixedPrice;
        
        
        public List<String> getSKUs() {
			return SKUs;
		}

		public int getFixedPrice() {
			return fixedPrice;
		}

		public CombinedItemFixedPricePromotion(List<String> skus, int fixedPrice) throws ArgumentException {
            super(skus.get(0));
			if (skus == null
                        || (skus.size() < 2) 
                        || skus.stream().anyMatch(i->i.isBlank())) {
                throw new ArgumentException("Invalid or missing SKUs! At least 2 SKU must be provided for this promotion");
            }
            
            if ((fixedPrice <= 0)) {
                throw new ArgumentException("Invalid number for fixed price in promotion rule! It must be grater than zero!");
            }
            
            this.SKUs = skus;
            this.fixedPrice = fixedPrice;
        }
        
        @Override
        public void execute(Cart cart) {
            while (this.isApplicable(cart)) {
                List<String> unusedSKUs = new ArrayList<String>(this.SKUs);
                List<CartItem> cartItemsWithoutPromotion= cart.getItems().stream().filter(i->!i.getPromotionApplied()).collect(Collectors.toList());
                
//                for (var item : cart.Items.Where(() => {  }, !i.PromotionApplied)) {
//                    if (unusedSKUs.Contains(item.Item.ID)) {
//                        item.FinalPrice = (this.FixedPrice / this.SKUs.Count);
//                        item.PromotionApplied = true;
//                        unusedSKUs.Remove(item.Item.ID);
//                    }
//                    
//                }
                
                for(CartItem item : cartItemsWithoutPromotion) {
                	if(unusedSKUs.contains(item.getItem().getId())) {
                		item.setFinalPrice(this.fixedPrice/this.SKUs.size());
                		item.setPromotionApplied(true);
                		unusedSKUs.remove(item.getItem().getId());
                	}
                }
                
            }
            
        }
        
        @Override
        public Boolean isApplicable(Cart cart) {
            if (isEmptyCart(cart)) {
                return false;
            }
            
            //var cartItemsWithoutPromotion = cart.Items.Where(() => {  }, !i.PromotionApplied);
            List<CartItem> cartItemsWithoutPromotion= cart.getItems().stream().filter(i->!i.getPromotionApplied()).collect(Collectors.toList());
            
            var applicable = true;
            for (String sku : this.SKUs) {
                applicable = applicable && cartItemsWithoutPromotion.stream().anyMatch(i->sku.equals(i.getItem().getId()));
            }
            
            return applicable;
        }
    
}