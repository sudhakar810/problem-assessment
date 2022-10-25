package com.promotionengine.test;
import com.promotionengine.model.Cart;
import com.promotionengine.model.CartItem;
import com.promotionengine.model.SKUitem;
import com.promotionengine.model.strategy.PromotionRule;
import com.promotionengine.model.strategy.Store;
import com.promotionengine.model.strategy.exception.ArgumentException;
import com.promotionengine.model.strategy.impl.CombinedItemFixedPricePromotion;
import com.promotionengine.model.strategy.impl.NitemForFixedPricePromotion;
import com.promotionengine.model.strategy.impl.PromotionRuleExtensions;

import junit.framework.Assert;



import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;  
public class StoreTests {
        
        Store store;
        
        @Before
        public void beforeEachTestMethod() throws ArgumentException {
        	
        	   String CandDfor30 = "C & D for 30";
               String ThreeAfor130 = "3 of A's for 130";
               
        	   SKUitem a = new SKUitem("A", 50);
               SKUitem b = new SKUitem("B", 30);
               SKUitem c = new SKUitem("C", 20);
               SKUitem d = new SKUitem("D", 15);
               NitemForFixedPricePromotion pr1 = new NitemForFixedPricePromotion("A", 3, 130);
               NitemForFixedPricePromotion pr2 = new NitemForFixedPricePromotion("B", 2, 45);
               
               
               CombinedItemFixedPricePromotion pr3 = new CombinedItemFixedPricePromotion(Arrays.asList(new String[]{"C","D"}), 30) ;
               
               List<PromotionRule> promotions = Arrays.asList(new PromotionRule[] { pr1, pr2, pr3 }) ;
              
           
               
            List<SKUitem> skuList = Arrays.asList(new SKUitem[] {a,b,c,d});
            
            store = new Store();
            
            store.addPromotion(CandDfor30);
            store.addPromotion(ThreeAfor130);
            
            this.store.setItems(skuList);
            this.store.addPromotions(promotions);
            
        }
        
        
        @Test
        public final void Store_with_items_added_to_cart_after_checkout_should_apply_promotions() throws ArgumentException {
        	store.addItemToCart("A")
            .addItemToCart("A")
            .addItemToCart("A")
            .addItemToCart("A")
            .addItemToCart("A")
            .addItemToCart("B")
            .addItemToCart("B")
            .addItemToCart("B")
            .addItemToCart("B")
            .addItemToCart("B")
            .addItemToCart("C");
        	
        Assert.assertEquals(420.0, store.cart.getTotalPrice(),1);
        Assert.assertEquals(11, store.cart.getItems().size());

        store.checkout();
        Assert.assertEquals(347.0, store.cart.getTotalPrice(),1);
        }
    }