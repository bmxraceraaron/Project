package sample;

public class Item {

    private String Item_Type;
    private int Item_Code;
    private String Item_Name;
    private String Brand;
    private int Quantity;
    private double Price;
    private double Discount;

    public Item(){
        this.Item_Type = "";
        this.Item_Code = 0;
        this.Item_Name = "";
        this.Brand = "";
        this.Quantity = 0;
        this.Price = 0;
        this.Discount = 0;
    }

    public Item(String Item_Type, int Item_Code, String Item_Name, String Brand, int Quantity, double Price, double Discount){
        this.Item_Type = Item_Type;
        this.Item_Code = Item_Code;
        this.Item_Name = Item_Name;
        this.Brand = Brand;
        this.Quantity = Quantity;
        this.Price = Price;
        this.Discount = Discount;
    }

    public String getItem_Type() {
        return Item_Type;
    }

    public void setItem_Type(String item_Type) {
        Item_Type = item_Type;
    }

    public int getItem_Code() {
        return Item_Code;
    }

    public void setItem_Code(int item_Code) {
        Item_Code = item_Code;
    }

    public String getItem_Name() {
        return Item_Name;
    }

    public void setItem_Name(String item_Name) {
        Item_Name = item_Name;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public double getDiscount() {
        return Discount;
    }

    public void setDiscount(double discount) {
        Discount = discount;
    }
}
