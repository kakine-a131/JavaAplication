package OrderSystem;

import java.util.ArrayList;
import java.util.List;

class Menu{
    private int price;
    private String name;
    public Menu(int price,String name){
        this.price = price;
        this.name = name;
    }
    public int getPrice(){
        return price;
    }
    public String getName(){
        return name;
    }
}

class Table{
    private int tableNumber;
    private int guestNumber;
    public Table(int tableNumber,int guestNumber){
        this.tableNumber = tableNumber;
        this.guestNumber = guestNumber;
    }
    public void getTable(){
        System.out.println(tableNumber+"番:"+guestNumber+"人");
    }
}

public class Order {
    private List<Menu> order;
    private int totalAmount;

    public Order() {
        order = new ArrayList<>();
        totalAmount = 0;
    }

    public void addMenuItem(Menu item) {
        order.add(item);
    }

    public int getTotalAmount() {
        for (Menu item : order) {
            totalAmount += item.getPrice();
        }
        return totalAmount;
    }

    public void display(){
        for(Menu item: order){
            System.out.println(item.getName()+":"+item.getPrice());
        }
        System.out.println("合計金額:"+getTotalAmount());
    }
}



class Main{
    public static void main(String[] args){
        Table tb = new Table(14,2);
        Order order = new Order();
        order.addMenuItem(new Menu(800,"みそカツパン"));
        order.addMenuItem(new Menu(540,"ホットコーヒー"));
        order.addMenuItem(new Menu(300,"ソフトクリーム"));
        tb.getTable();
        order.display();
    }
}

