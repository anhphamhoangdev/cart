package murach.business;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class Cart implements Serializable {
    private ArrayList<LineItem> items;
    public Cart() {
        items = new ArrayList<LineItem>();
    }
    public ArrayList<LineItem> getItems() {
        return items;
    }
    public int getCount() {
        return items.size();
    }
    public void addItem(LineItem item, String type) {
        String code = item.getProduct().getCode();
        int quantity = item.getQuantity();
        String update = type.toString();
        if(update.equals("true"))
        {
            for (int i = 0; i < items.size(); i++) {
                LineItem lineItem = items.get(i);
                if (lineItem.getProduct().getCode().equals(code))
                {
                    lineItem.setQuantity(quantity);
                    return;
                }
            }
        }
        else // add
        {
            for (int i = 0; i < items.size(); i++) {
                LineItem lineItem = items.get(i);
                if (lineItem.getProduct().getCode().equals(code))
                {
                    lineItem.setQuantity(lineItem.getQuantity() + quantity);
                    return;
                }
            }
            items.add(item); // new
        }
    }
    public void removeItem(LineItem item) {
        String code = item.getProduct().getCode();
        for (int i = 0; i < items.size(); i++) {
            LineItem lineItem = items.get(i);
            if (lineItem.getProduct().getCode().equals(code)) {
                items.remove(i);
                return;
            }
        }
    }
    public double totalBill()
    {
        double sum = 0;
        for (var item: items)
        {
                sum += item.getTotal();
        }
        return sum;
    }
    public String getAmountCurrencyFormat()
    {
        Locale locale = new Locale("en", "US");
        NumberFormat currency = NumberFormat.getCurrencyInstance(locale);
        return currency.format(this.totalBill());
    }
    public double totalBillDiscount()
    {
        double a = totalBill();
        if(a>100) return a*0.8;
        if(a>50) return a*0.9;
        return a;
    }
    public String getAmountDiscountCurrencyFormat()
    {
        Locale locale = new Locale("en", "US");
        NumberFormat currency = NumberFormat.getCurrencyInstance(locale);
        return currency.format(this.totalBillDiscount());
    }
    public int getDiscount()
    {
        double a = (1 - (totalBillDiscount()/totalBill())) * 100;
        a = Math.round(a*100.0)/100.0;
        int discount = (int) a;
        return discount;
    }
}