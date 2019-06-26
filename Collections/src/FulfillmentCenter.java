import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Collections;

public class FulfillmentCenter implements Comparator<String> {
    List<Item> items = new ArrayList<>();

    String warehouse_name;
    double capacity;
    double actual_capacity = 0;

    public FulfillmentCenter() {
    }

    public FulfillmentCenter(String warehouse_name, double capacity) {
        this.warehouse_name = warehouse_name;
        this.capacity = capacity;

    }

    public void addProduct(Item item) {
        boolean check = true;
        if (actual_capacity + (item.mass * item.amount) <= capacity) {
            for (Item i : items) {
                if (i.compareTo(item) == 0) {
                    check = false;
                    i.amount = i.amount + item.amount;
                    actual_capacity = actual_capacity + item.amount * item.mass;
                }
            }
            if (check) {
                items.add(item);
                actual_capacity = actual_capacity + item.amount + item.mass;
            }
        } else System.out.println(System.err);
    }

    public void getProduct(Item item) {
        if (item.amount == 1)
            items.remove(item);
        else {
            item.amount--;
            actual_capacity = actual_capacity - item.mass;
        }
    }

    public void removeProduct(Item item) {
        items.remove(item);
        actual_capacity = actual_capacity - item.mass;
    }

    public Item search(String s) {
        Item a = new Item();
        for (Item i : items) {
            if (compare(i.name, s) == 0)
                a = new Item(i.name, i.condition, i.mass, i.amount);
        }
        return a;
    }

    //public Item[] searchPartial(String part_name) {
    public void searchPartial(String part_name) {

        for (Item i : items) {
            if (i.name.contains(part_name))
                i.print();
        }
       /*  int tab_size = 0;
        int j = 0;
        Item[] item = new Item[tab_size];
        for (Item i : items) {
            if (i.name.contains(part_name))
                item[j] = i;
            j++;
        }
        return item;*/
    }

    public void countByCondition() {
        int c_new = 0;
        int c_used = 0;
        int c_refurbished = 0;
        for (Item i : items) {
            if (i.condition == ItemCondition.NEW)
                c_new++;
            else if (i.condition == ItemCondition.USED)
                c_used++;
            else c_refurbished++;

        }
        System.out.println("New: " + c_new + " Used: " + c_used + " Refurbished: " + c_refurbished);
    }

    public void summary() {
        for (Item i : items)
            i.print();
    }

    public void sortByName() {
        List<Item> itemslist = new ArrayList<>();
        for (Item i : items) {
            itemslist.add(i);
        }
        Collections.sort(itemslist);
        for (Item i : itemslist)
            i.print();

        //return itemslist;
    }

    public List<Item> sortByAmount() {
        List<Item> itemslist = new ArrayList<>();
        for (Item i : items) {
            itemslist.add(i);
        }
        itemslist.sort(Comparator.comparing(Item::getamount));
        Collections.reverse(items);
        return itemslist;
    }

    public Item max() {
        Item a = Collections.max(items, Comparator.comparing(Item::getamount));
        return a;
    }


    @Override
    public int compare(String o1, String o2) {
        return o1.compareTo(o2);
    }
}





