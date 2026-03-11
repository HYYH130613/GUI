public class Main {
    public static void main(String[] args) {
        Product product = new Product("Cappucino", 13.4, "coffe");
        Customer customer = new Customer("Adelina", "s33281@pjstk.edu.pl", 200);

        String out = product.formatted();
        System.out.println(out);
        String out2 = customer.formatted();
        System.out.println(out2);


        DIscount dis = new PrecentageDiscount(50);
        DIscount dis2 = new FixedAmountDiscount(44);

        System.out.println(dis.apply(13.4));
        System.out.println(dis2.apply(13.4));

    }
}