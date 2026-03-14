public record Customer(String name, String email, int loyaltyPoints) {
    public Customer{

        if(name == null || name.isEmpty()){
            throw new IllegalArgumentException("Customer name cannot be empty");
        }

        if(email == null){
            throw new IllegalArgumentException("Email address is invalid ");
        }
        if(!email.contains("@")){
            throw new IllegalArgumentException("Email address is invalid:  no-at-sign");
        }

        if(loyaltyPoints < 0){
            throw new IllegalArgumentException("Loyalty points cannot be negative");
        }
    }
    public String loyaltyLevel(){
        if(loyaltyPoints >= 200){
            return "GOLD";
        }
        else if(loyaltyPoints >= 100){
            return "SILVER";
        }
        else if(loyaltyPoints >= 50){
            return "BRONZE";
        }
        else{
            return "STANDARD";
        }
    }

    public String formatted(){
        String myStr = " %s [%s] (%d pts) \n";
        return String.format(myStr, name, loyaltyLevel(),loyaltyPoints);
    }
}
