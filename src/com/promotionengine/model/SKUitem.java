package com.promotionengine.model;

import com.promotionengine.model.strategy.exception.ArgumentException;

public class SKUitem
    {
	
	private String id;
    private float unitPrice;
    
    
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}

	public SKUitem(String id, float unitPrice) throws ArgumentException
    {
        if (id.isBlank()) 
        	throw new ArgumentException("Invalid or missing SKU id!");
        if (unitPrice <= 0) throw new ArgumentException("Invalid unit price! It must be grater than zero!");

        this.id = id;
        this.unitPrice = unitPrice;
    }

    public void updateUnitPrice(float price)
    {
        this.unitPrice = price;
    }
    
    }