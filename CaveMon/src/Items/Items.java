package src.Items;

public abstract class Items {
    
    private String name;
    private int amount;

    public Items(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }
     /**
      * Returns the name of the item
      * @return name of the item
      */
    public String getName() {
        return name;
    }

    /**
     * Adds the @param cant to current amount
     */
    public void addAmount(int cant){
        amount += cant;
    }

    /**
     * Substract @param cant
     */
    public void subsAmount(int cant){
        amount -= cant;
    }
    
    /**
     * Returns the amount of the item
     * @return amount of the item
     */
    public int getAmount(){
        return amount;
    }

    public abstract void use();

    public String toString() {
        return name + " ["+amount+"]";
    }

}
