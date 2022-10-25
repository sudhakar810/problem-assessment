package com.promotionengine.model.strategy;

import com.promotionengine.model.Cart;

public abstract class PromotionRule
    {
        public String name;
        
        public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public abstract Boolean isApplicable(Cart cart);
        public abstract void execute(Cart cart);
        protected static Boolean isEmptyCart(Cart cart)
        {
            return (cart == null) || cart.getItems() == null || cart.getItems().size() == 0;
        }
		public PromotionRule(String name) {
			super();
			this.name = name;
		}
        
        
    }