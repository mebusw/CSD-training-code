package refactor.error;

import java.util.ArrayList;

public class Sale {
    private Inventory inventory = new Inventory();
    private ArrayList<Item> items = new ArrayList<Item>();



    public void addBarcode(String barcode) {
        Item item = inventory.itemForBarcode(barcode);
        items.add(item);
    }


    public void total() {
        Money sum = new Money();
        for (Item item : items) {
            sum = sum.add(item.getTaxedPrice(items));
        }
    }
}


