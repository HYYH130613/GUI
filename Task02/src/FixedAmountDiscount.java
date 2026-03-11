public class FixedAmountDiscount extends Discount {

    private double amount;

    public FixedAmountDiscount(double amount){

        super(String.format("%.2f", amount));

        if(amount < 0){
            throw new IllegalArgumentException("Amount cannot be negative" + amount);
        }
        else{
            this.amount = amount;
        }
    }

    @Override
    public double apply(double originalPrice){
        if(Math.max(originalPrice, 0) == 0){
            throw new IllegalArgumentException("Original price less than zero: "+originalPrice);
        }
        double sub = originalPrice - amount;
        return Math.max(sub, 0);
    }

}
