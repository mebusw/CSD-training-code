package refactor.error;

import java.util.HashMap;

public class Inventory {
    private HashMap<String, Item> items = new HashMap<String, Item>();

    public Inventory() {
        Item milk = new Item("Milk", new Money(7000));
        items.put("1", new Item("Preserved Duck Eggs", new Money(150000)));
        items.put("3", milk);
    }

    public Item itemForBarcode(String barcode) {
        return items.get(barcode);
    }


}
