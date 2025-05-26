import java.util.ArrayList; 
import java.util.List; 

public class Order { 
    private List<MenuItem> items = new ArrayList<>(); 

    public void addItem(MenuItem item) { 
        items.add(item); 
    } 

    public double getTotal() { 
        double total = 0; 
        for (MenuItem item : items) { 
            total += item.getPrice(); 
        } 
        return total; 
    } 

    public void displayBill() { 
        System.out.println("------- BILL -------"); 
        for (MenuItem item : items) { 
            System.out.println(item.getName() + " - $" + item.getPrice()); 
        } 
        System.out.println("--------------------"); 
        System.out.println("Total: $" + getTotal()); 
        System.out.println("--------------------\n"); 
    } 

    public List<MenuItem> getItems() { 
        return items; 
    } 
}