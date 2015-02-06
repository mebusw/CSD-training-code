package refactor.error;

import java.util.ArrayList;
import java.util.List;



public class Item {
	private String name;
	private Money price;

	public Item(String name, Money price) {
		this.name = name;
		this.price = price;
	}

	public Money getValue() {
		return price;
	}
	
	public String getDisplayLine() {
		return name + " " + price.asText();
	}

	public Money getPrice(List<Item> items) {
		return price;
	}

	public Money getPrice() {
		return getPrice(new ArrayList<Item>());
	}
	
	public Money getTaxedPrice(List<Item> items) {
		return getPrice(items);
	}

	public Money getTaxedPrice() {
		return getTaxedPrice(new ArrayList<Item>());
	}
	

	public String getName() {
		return name;
	}
	
}
