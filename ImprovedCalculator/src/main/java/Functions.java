public class Functions {

    public String name;
    public String shortName;
    public Functions(String name, String shortName){
        this.name = name;
        this.shortName = shortName;

    }
    @Override
    public String toString() {
        return name;
    }
}
