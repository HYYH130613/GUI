public class PercentageDiscount extends Discount {

    private final double percentage;

    public PercentageDiscount(double p){
        super(String.format("%.2f%%", p));

        this.percentage = p;

        if(p <= 0 || p > 100){
            throw new IllegalArgumentException("Discount percentage must be in the range (0, 100]. Received: "+ percentage);
        }
    }

    public double getPercentage(){
        return percentage;
    }

    @Override
    public double apply(double originalPrice){

        if(originalPrice < 0){
            throw new IllegalArgumentException("Original price less than zero: "+originalPrice);
        }

        double discounted = originalPrice * (1 - percentage / 100.0);
        discounted = Math.max(discounted, 0);
        return discounted;
    }

}
