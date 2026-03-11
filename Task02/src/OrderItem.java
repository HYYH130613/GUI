public record OrderItem (Product product, int quantity) {
    public OrderItem {
        if(product == null ){
            throw new IllegalArgumentException("product cannot be empty");
        }
        if(quantity < 1){
            throw new IllegalArgumentException("Quantity must be at least 1. Received: "+quantity);
        }
    }

    public double totalPrice() {
        double p = product.price();
        return p*quantity;
    }

    public String formatted(){
        String left = String.format("%s: %d", product.name(), quantity);
        String right = String.format("%.2f", product.price());
        int line_width = 40;
        int space = line_width - right.length() - left.length();

        if(space<0) { space = 0;}

        return left + " ".repeat(space) + right;

    }

}
