package com.promotionengine.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.promotionengine.model.strategy.exception.ArgumentException;

public class Cart {
	
	private List<CartItem> items;
	private float totalPrice;
	
	public List<CartItem> getItems() {
		return items;
	}
	public void setItems(List<CartItem> items) {
		this.items = items;
	}
	public float getTotalPrice() {
		this.totalPrice = 0;
		for(CartItem i : this.getItems()) {
			this.totalPrice +=i.getFinalPrice();
		}
		return this.totalPrice;
	}
	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public Cart() {
		this.items = new ArrayList<>();
	}
	public Cart(List<CartItem> items, float totalPrice) {
		super();
		this.items = items;
		this.totalPrice = totalPrice;
	}
	
	public void addItems(SKUitem item, int numberOfItems)
    {
        for (int i = 0; i < numberOfItems; i++)
        {
//            items.add(new CartItem { 
//            	this.item = item;
//            			finalPrice = item.unitPrice;
//            			promotionApplied = false });
            items.add(new CartItem(item, false, item.getUnitPrice()));
        }
    }

    public void addItem(SKUitem item)
    {
        items.add(new CartItem(item, false, item.getUnitPrice()));
    }

    public void removeItem(String skuItemId) throws ArgumentException
    {
        if (!isValidSKU(skuItemId)) throw new ArgumentException("Item not found on cart!");

        //Items.remove(Items.FirstOrDefault(t => skuItemId.Equals(t.Item.ID)));
        items.remove(items.stream().anyMatch(t->skuItemId.equals(t.getItem().getId())));
    }

    private Boolean isValidSKU(String sku)
    {
    	return items.stream().anyMatch(i->sku.equals(i.getItem().getId()));
        //return Items.Any(i => sku.Equals(i.Item.ID));
    }
	
	

}
