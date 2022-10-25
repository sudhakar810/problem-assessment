package com.promotionengine.model.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.promotionengine.model.Cart;
import com.promotionengine.model.SKUitem;
import com.promotionengine.model.strategy.exception.ArgumentException;
import com.promotionengine.model.strategy.impl.PromotionRuleExtensions;


public class Store implements IStore {
	
	public Cart cart;
    public List<PromotionRule> promotions;
    public List<SKUitem> items;
    

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public List<PromotionRule> getPromotions() {
		return promotions;
	}

	public void setPromotions(List<PromotionRule> promotions) {
		this.promotions = promotions;
	}

	public List<SKUitem> getItems() {
		return items;
	}

	public void setItems(List<SKUitem> items) {
		this.items = items;
	}
	
	public Store() {
		this.promotions = new ArrayList<>();
		this.cart = new Cart();
	}

	public Store(Cart cart, List<PromotionRule> promotions, List<SKUitem> items) {
		super();
		this.cart = cart;
		this.promotions = promotions;
		this.items = items;
	}

	@Override
	public Store addSKUitem(SKUitem item) {
		if( item != null ) items.add(item);
        return this;
	}

	@Override
	public Store addPromotions(List<PromotionRule> promotions) {
		if ( promotions != null && promotions.size() > 0 )
			this.promotions.addAll(promotions);
        return this;
	}

	@Override
	public Store addPromotion(PromotionRule promotion) {
		if ( promotion != null ) this.promotions.add(promotion);
        return this;
	}

	@Override
	public Store addPromotion(String promotion) throws ArgumentException {
//		String patternString = "^\\d";
//        Pattern pattern = Pattern.compile(patternString);
//
//        Matcher matcher = pattern.matcher(promotion);
//        boolean matches = matcher.matches();
		if (!promotion.contains("&"))
        {
            addPromotion(PromotionRuleExtensions.ToNitemForFixedPricePromotion(promotion));
        }
        else
        {
            addPromotion(PromotionRuleExtensions.ToCombinedItemFixedPricePromotion(promotion));
        }
        return this;
	}

	@Override
	public Store addItemToCart(String itemSKU) throws ArgumentException {
		if (!isValidSKU(itemSKU)) throw new ArgumentException("SKU not found!");
        if ( !itemSKU.isBlank() )
        	this.cart.addItem(items.stream().filter(i -> itemSKU.equals(i.getId())).findFirst().orElse(null));
        return this;
	}

	@Override
	public void deletePromotion(String promotion) throws ArgumentException {
		   //var promotionIndex = promotions.FindIndex(p => promotion.Equals(p.ToString()));
		   
		   Boolean promotionIndex = this.promotions.stream().anyMatch(p->promotion.equals(p.getName()));
		   
           if (!promotionIndex) 
        	   throw new ArgumentException("Promotion not found!");
           this.promotions.remove(new PromotionRule(promotion) {
			
			@Override
			public Boolean isApplicable(Cart cart) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public void execute(Cart cart) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}

	@Override
	public Store emptyCart() {
        return this;
	}

	@Override
	public Store checkout() {
		this.promotions.forEach(p -> {
			if (p.isApplicable(this.cart)) 
				p.execute(this.cart); 
			});
        return this;
	}

	@Override
	public void deleteItemFromCart(String sku) throws ArgumentException {
		if (!isValidSKU(sku)) throw new ArgumentException("SKU not found!");
        try {
			this.cart.removeItem(sku);
		} catch (ArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public float getCartTotal() {
		return this.cart.getTotalPrice();
	}

	@Override
	public List<SKUitem> getSKUitems() {
		return this.items;
	}

	@Override
	public void updateSKUitemUnitPrice(String sku, float price) throws ArgumentException {
		if (!isValidSKU(sku)) throw new ArgumentException("SKU not found!");

        for (SKUitem item : this.items)
        {
            if(sku.equals(item.getId()))
            {
                item.updateUnitPrice(price);
            }
        }
		
	}

	@Override
	public void deleteSKUitem(String sku) throws ArgumentException {
		if (!isValidSKU(sku)) throw new ArgumentException("SKU not found!");
        //items.removeAt(Items.FindIndex(i => sku.Equals(i.ID)));	
		
		SKUitem skItem = this.items.stream().filter(i->i.getId().equals(sku)).collect(Collectors.toList()).get(0);
		this.items.remove(skItem);
	}



	@Override
	public List<SKUitem> getAllItems() {
		 return this.items;
	}

	@Override
	public SKUitem getItem(String sku) throws ArgumentException {
		if (!isValidSKU(sku)) throw new ArgumentException("SKU not found!");
        //return items.First(i => sku.Equals(i.ID));
        return this.items.stream().filter(i->sku.equals(i.getId())).collect(Collectors.toList()).get(0);
	}
	
	 private Boolean isValidSKU(String sku)
     {
		 return this.items.stream().anyMatch(i->sku.equals(i.getId()));
     }

}
