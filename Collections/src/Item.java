
public class Item implements Comparable<Item>{
    String name;
    ItemCondition condition;
    double mass;
    int amount;

    public Item(){}

    public Item(String name, ItemCondition condition, double mass, int amount){
        this.name = name;
        this.condition = condition;
        this.mass = mass;
        this.amount = amount;
    }
    public void print(){
        System.out.println("Name: " + this.name + " Condition: " + this.condition + " Mass: " + this.mass + " Amount: " + this.amount);
    }

    public int getamount(){
        return amount;
    }
    @Override
    public int compareTo(Item item){
        return name.compareTo(item.name);
    }

}
