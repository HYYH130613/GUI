public record Customer(String name, String email, int loyaltyPoints) {
    public Customer{

        if(name == null || name.isEmpty()){
            throw new IllegalArgumentException("Customer name cannot be empty");
        }

        if(email == null || !email.contains("@")){
            throw new IllegalArgumentException("Email address is invalid "+ email);
        }

        if(loyaltyPoints < 0){
            throw new IllegalArgumentException("Loyalty points cannot be negative");
        }
    }
    public String loyaltyLevel(){
        if(loyaltyPoints >= 200){
            return "Loyalty level GOLD";
        }
        else if(loyaltyPoints >= 100){
            return "Loyalty level SILVER";
        }
        else if(loyaltyPoints >= 50){
            return "Loyalty level BRONZE";
        }
        else{
            return "Loyalty level STANDARD";
        }
    }

    public String formatted(){
        String myStr = "Customer %s with loyalty points %d \n";
        return String.format(myStr, name, loyaltyPoints) + loyaltyLevel();
    }
}
