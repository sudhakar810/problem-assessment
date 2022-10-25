package com.promotionengine.test;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import com.promotionengine.model.strategy.exception.ArgumentException;
import com.promotionengine.model.strategy.impl.NitemForFixedPricePromotion;
import com.promotionengine.model.strategy.impl.PromotionRuleExtensions; 

 public class PromotionRuleTests
    {
        @Test
        public void NitemForFixedPricePromotion_for_valid_data_should_create_instance() throws ArgumentException
        {
        	NitemForFixedPricePromotion promotion = new NitemForFixedPricePromotion("A", 3, 130);
        	
        	 String CandDfor30 = "C & D for 30";
             String ThreeAfor130 = "3 of A's for 130";
             
            Assert.assertNotNull(promotion);
            Assert.assertEquals("A", promotion.SKU);
            
            promotion = PromotionRuleExtensions.ToNitemForFixedPricePromotion(ThreeAfor130);
            
            Assert.assertEquals(3, promotion.numberOfItems);
            Assert.assertEquals(130, promotion.fixedPrice);
            Assert.assertEquals("3 A's 130", promotion.getName());
        }

	/*
	 * [Test] [TestCase(null)] [TestCase("")] [TestCase(" ")] [TestCase("\t")]
	 * [TestCase("\n")] public void
	 * NitemForFixedPricePromotion_for_invalid_SKU_should_throw_ArgumentException(
	 * string sku) { Assert.Throws<ArgumentException>(() => new
	 * NitemForFixedPricePromotion(sku, 3, 130)); }
	 * 
	 * [Test] [TestCase(-1)] [TestCase(0)] public void
	 * NitemForFixedPricePromotion_for_invalid_numberOfItems_should_throw_ArgumentException
	 * (int numberOfItems) { Assert.Throws<ArgumentException>(() => new
	 * NitemForFixedPricePromotion("A", numberOfItems, 130)); }
	 * 
	 * [Test] [TestCase(-1)] [TestCase(0)] public void
	 * NitemForFixedPricePromotion_for_invalid_fixedPrice_should_throw_ArgumentException
	 * (int fixedPrice) { Assert.Throws<ArgumentException>(() => new
	 * NitemForFixedPricePromotion("A", 3, fixedPrice)); }
	 * 
	 * [Test] public void
	 * NitemForFixedPricePromotion_IsApplicable_for_empty_cart_should_return_false()
	 * { var promotion = new NitemForFixedPricePromotion("A", 3, 130);
	 * Assert.False(promotion.IsApplicable(null));
	 * Assert.False(promotion.IsApplicable(new Cart())); }
	 * 
	 * [Test] public void
	 * CombinedItemFixedPricePromotion_for_valid_data_should_create_instance() { var
	 * promotion = new CombinedItemFixedPricePromotion(new List<string> { "C", "D"
	 * }, 30); Assert.NotNull(promotion); Assert.AreEqual(new List<string> { "C",
	 * "D" }, promotion.SKUs); Assert.AreEqual(30, promotion.FixedPrice);
	 * Assert.AreEqual("C & D for 30", promotion.Name); }
	 * 
	 * [Test] [TestCase("")] [TestCase(" ")] [TestCase("\t")] [TestCase("\n")]
	 * public void
	 * CombinedItemFixedPricePromotion_for_invalid_SKUs_should_throw_ArgumentException
	 * (string sku) { Assert.Throws<ArgumentException>(() => new
	 * CombinedItemFixedPricePromotion(sku.Split(" ").ToList(), 30)); }
	 * 
	 * [Test] [TestCase(-1)] [TestCase(0)] public void
	 * CombinedItemFixedPricePromotion_for_invalid_fixedPrice_should_throw_ArgumentException
	 * (int fixedPrice) { Assert.Throws<ArgumentException>(() => new
	 * CombinedItemFixedPricePromotion(new List<string> { "C", "D" }, fixedPrice));
	 * }
	 * 
	 * [Test] public void
	 * CombinedItemFixedPricePromotion_IsApplicable_for_empty_cart_should_return_false
	 * () { var promotion = new CombinedItemFixedPricePromotion(new List<string>{
	 * "C", "D" }, 30); Assert.False(promotion.IsApplicable(null));
	 * Assert.False(promotion.IsApplicable(new Cart())); }
	 */
    }