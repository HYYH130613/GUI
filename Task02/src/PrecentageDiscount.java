public class PrecentageDiscount extends DIscount{

    private double precentage;

    PrecentageDiscount(double p){
        super(String.format("%.2f", p));

        if(p > 0 && p <= 100){
            this.precentage = p;
        }else{
            throw new IllegalArgumentException("Discount percentage must be in the range (0, 100]. Received: "+precentage);
        }
    }

    public double getPrecentage(){
        return precentage;
    }

    @Override
    public double apply(double originalPrice){

        if(Math.max(originalPrice, 0) == 0){
            throw new IllegalArgumentException("Original price less than zero: "+originalPrice);
        }

        double discount = originalPrice * (1-precentage/100.0);

        return Math.max(discount, 0);

    }

}
