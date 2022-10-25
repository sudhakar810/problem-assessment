package com.promotionengine.model.strategy.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.promotionengine.model.strategy.exception.ArgumentException;

public class PromotionRuleExtensions {
        
        public static NitemForFixedPricePromotion ToNitemForFixedPricePromotion(String promotion) throws ArgumentException {
            // 3 A for 130
//            var promotionDetails = promotion.split(" ").Where(() => {  }, (!string.IsNullOrEmpty(p.Trim()) 
//                            && (!"for".Equals(p) 
//                            && !"of".Equals(p)))).ToList();
            List<String> promotionDetails = Arrays.stream(promotion.split(" ")).filter(i->!i.trim().isBlank() && !"for".equals(i) && !"of".equals(i)).collect(Collectors.toList());
            //var sku = promotionDetails.get(1).replace("\'s", "");
            var sku = String.join(" ", promotionDetails);
            var numberOfItems = Integer.parseInt(promotionDetails.get(0));
            var price = Integer.parseInt(promotionDetails.get(promotionDetails.size()-1));
            return new NitemForFixedPricePromotion(sku, numberOfItems, price);
        }
        
        public static CombinedItemFixedPricePromotion ToCombinedItemFixedPricePromotion(String promotion) throws ArgumentException {
            // C D for 30
//            var promotionDetails = promotion.Split(" ").Where(() => {  }, (!string.IsNullOrEmpty(p.Trim()) 
//                            && (!"for".Equals(p) 
//                            && !"&".Equals(p)))).ToList();
            
            List<String> promotionDetails = Arrays.stream(promotion.split(" ")).filter(i->!i.trim().isBlank() && !"for".equals(i) && !"&".equals(i)).collect(Collectors.toList());
            
            var skus = promotionDetails.subList(0, promotionDetails.size() - 1);
            //var sku = String.join(" ", promotionDetails);
            var price = Integer.parseInt(promotionDetails.get(promotionDetails.size()-1));
            return new CombinedItemFixedPricePromotion(skus, price);
        }
    }