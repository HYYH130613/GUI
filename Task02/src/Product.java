public record Product (String name, double price, String category){

    public Product{
       if(name == null || name.isBlank()){
           throw new IllegalArgumentException("Product name cannot be empty");
       }

       if(price < 0){
           throw new IllegalArgumentException("Price must be greater than 0. Received: "+price);
       }

       if(category == null || category.isBlank()){
           throw new IllegalArgumentException("Product category cannot be empty");
       }
    }

    public String formatted(){
        String myStr = "%s (%s)- %.2f PLN";
        return String.format(myStr, name, category, price);
    }

}
