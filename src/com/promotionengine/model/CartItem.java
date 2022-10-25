package com.promotionengine.model;
public class CartItem
    {
        private SKUitem item;
        private Boolean promotionApplied;
        private float finalPrice;
		public SKUitem getItem() {
			return item;
		}
		public void setItem(SKUitem item) {
			this.item = item;
		}
		public Boolean getPromotionApplied() {
			return promotionApplied;
		}
		public void setPromotionApplied(Boolean promotionApplied) {
			this.promotionApplied = promotionApplied;
		}
		public float getFinalPrice() {
			return finalPrice;
		}
		public void setFinalPrice(float finalPrice) {
			this.finalPrice = finalPrice;
		}
		public CartItem(SKUitem item, Boolean promotionApplied, float finalPrice) {
			super();
			this.item = item;
			this.promotionApplied = promotionApplied;
			this.finalPrice = finalPrice;
		}
        
    }