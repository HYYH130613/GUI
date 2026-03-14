public class FixedAmountDiscount extends Discount {

    private final double amount;

    public FixedAmountDiscount(double amount){

        super(String.format("%.2f", amount));

        if(amount < 0){
            throw new IllegalArgumentException("Amount cannot be negative " + amount);
        }
        this.amount = amount;
    }

    @Override
    public double apply(double originalPrice){
        if(originalPrice < 0){
            throw new IllegalArgumentException("Original price less than zero: "+originalPrice);
        }
        double discounted = originalPrice - amount;
        discounted = Math.max(discounted, 0);
        return discounted;
    }

}
