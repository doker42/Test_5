package myproject.automation.hometask5.model;

/**
 * Hold Product information that is used among tests.
 */
public class ProductData {
    public String name;
    private String qtyS;
    private String priceS;
    private int qty;
    private float price;

    public ProductData(){}

    public ProductData(String name, int qty, float price, String qtyS, String priceS) {
        this.name = name;
        this.qty = qty;
        this.qtyS = qtyS;
        this.priceS = priceS;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String nname){
        name = nname;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty){
        this.qty = qty;
    }

    public String getQtyS() {
        return qtyS;
    }

    public void setQtyS(String qtyS){
        this.qtyS = qtyS;
    }

    public float getPrice() {
        return price;
    }

    public String getPriceS() {
        return priceS;
    }

    public void setPriceS(String priceS){
        this.priceS = priceS;
    }


    /**
     * @return New Product object with random name, quantity and price values.
          */
 /*   public static int generateProduct(int size) {
        Random random = new Random();
        return random.nextInt(size);
    }

    public static ProductData generate() {
        Random random = new Random();

        return new ProductData(

                "New Product " + System.currentTimeMillis(),
                random.nextInt(100) + 1,
                (float) Math.round(random.nextInt(100_00) + 1) / 100);
    }  */
}
