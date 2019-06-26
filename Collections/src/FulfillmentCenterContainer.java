import java.util.*;


public class FulfillmentCenterContainer {
    Map<String, FulfillmentCenter> warehouse = new TreeMap<>();

    void addCenter(String warehouse_name, double capacity) {
        FulfillmentCenter fulfillment = new FulfillmentCenter(warehouse_name, capacity);
        warehouse.put(warehouse_name, fulfillment);
    }

    void removeCenter(String name) {
        warehouse.remove(name);
    }

    void findEmty() {
        for( Map.Entry<String, FulfillmentCenter> entry: warehouse.entrySet())
           if (entry.getValue().actual_capacity == 0){
               System.out.println("Empty fulfillment center: ");
                System.out.print("Name: "+ entry.getKey());}
    }

    void summary() {
        for( Map.Entry<String, FulfillmentCenter> entry: warehouse.entrySet()){
            System.out.println("Name of fulfilment center: "+ entry.getKey());
            double fraction = 100*entry.getValue().actual_capacity/entry.getValue().capacity;
            System.out.print("Capacity fraction : "+fraction+"%\n");
        }
    }
}
