package refactor.receipt;

import java.util.ArrayList;

public class Receipt
{
    private ArrayList<Double> Discounts;
    private ArrayList<Double> ItemTotals;
    
    public Receipt(ArrayList<Double> discounts, ArrayList<Double> itemTotals) {
        this.Discounts = discounts;
        this.ItemTotals = itemTotals;
    }
    public double CalculateGrandTotal()
    {
        double subTotal = 0;
        for (double itemTotal : ItemTotals)
            subTotal += itemTotal;
        if (Discounts.size() > 0)
        {
            for (double discount : Discounts)
                subTotal -= discount;
        }
        double tax = subTotal * 0.065;
        subTotal += tax;
        return subTotal;
    }
}
