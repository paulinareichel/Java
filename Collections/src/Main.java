
public class Main {
    public static void main(String[] args) {
        FulfillmentCenterContainer container = new FulfillmentCenterContainer();
        container.addCenter("Clothes", 20);
        container.addCenter("Dishes", 35);
        container.addCenter("Toys", 35);

        Item tshirt = new Item ("T-shirt", ItemCondition.NEW, 0.15, 2);
        Item trousers = new Item ("Trousers", ItemCondition.USED, 3.15, 1);
        Item cup = new Item ("Cup", ItemCondition.NEW, 1.78, 3);
        Item plate = new Item("Plate", ItemCondition.REFURBISHED, 1.5,4);
        tshirt.print();

        container.warehouse.get("Clothes").addProduct(tshirt);
        container.warehouse.get("Clothes").addProduct(trousers);
        container.warehouse.get("Dishes").addProduct(cup);
        container.warehouse.get("Dishes").addProduct(plate);

        container.summary();
        container.findEmty();
        container.removeCenter("Toys");
        container.summary();
        System.out.println("Clothes: ");
        container.warehouse.get("Clothes").countByCondition();
        container.warehouse.get("Clothes").searchPartial("T");
        System.out.println("Dishes: ");
        container.warehouse.get("Dishes").search("Cup");
        container.warehouse.get("Dishes").sortByName();
        container.warehouse.get("Dishes").sortByAmount();
        container.warehouse.get("Dishes").max();
        container.warehouse.get("Dishes").getProduct(plate);
        container.warehouse.get("Dishes").summary();
        System.out.println("Clothes: ");
        container.warehouse.get("Clothes").removeProduct(trousers);
        container.warehouse.get("Clothes").summary();

    }
}
