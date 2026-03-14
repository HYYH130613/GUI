import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Order {
    private int id;
    private final OrderItem[] items;
    private final Customer customer;
    private final LocalDateTime createdAt;
    private Discount discount;

    private  Order(int id, OrderItem[] items, Customer customer, Discount discount){
        this.id = id;
        OrderItem[] temp = new OrderItem[items.length];

        for(int i = 0; i<temp.length; i++){
            temp[i] = items[i];
        }
        this.items = temp;
        this.customer = customer;
        this.discount = discount;
        createdAt = LocalDateTime.now();
    }

    public int getId(){
        return id;
    }
    public Customer getCustomer(){
        return customer;
    }
    public Discount getDiscount() {
        return discount;
    }
    public Discount setDiscount(Discount newDiscount) {
        discount = newDiscount;
        return discount;
    }

    public OrderItem[] getItems() {
        OrderItem[] temp = new OrderItem[items.length];
        for(int i = 0; i<temp.length; i++){
            temp[i] = items[i];
        }
        return temp;
    }

    public int getItemCount(){
        int count = 0;
        for(OrderItem item : items){
            count += item.quantity();
        }
        return count;
    }

    public int getLineCount(){
        return items.length;
    }

    public double calculateSubtotal(){
        double sum = 0;
        for(OrderItem item : items){
            sum+=item.totalPrice();
        }
        return sum;
    }

    public double calculateTotal(){
        double subtotal=0;
        if(discount != null){
            return discount.apply(subtotal);
        }
        return subtotal;
    }

    @Override
    public String toString(){
        return String.format("Order №%d, %s, total: %d, for value %d ", getId(),customer.name(), getItemCount(), calculateTotal());
    }

    class Receipt {
        private final String cachierName;
        private static final String CAFE_NAME = "CAFE 'UNDDER JAVA'";
        private static final int WIDTH = 42;

        public Receipt(String cachierName){
            if(cachierName == null || cachierName.isEmpty()){
                throw new IllegalArgumentException("Cashier name cannot be empty");
            }
            this.cachierName = cachierName;
        }

        private String center(String text){
            int num = (WIDTH - text.length())/2;
            if(num<0){ num = 0;}
            return " ".repeat(num) + text;
        }

        private String formatLine(String label, double amount){
            String left = label + ": ";
            String right = String.format("%.2f PLN", amount);

            int space = WIDTH - right.length() - left.length();
            if(space<0) { space = 1;}

            return left + " ".repeat(space) + right;
        }

        private String formatNegLine(String label, double amount){
            String left = label + ": ";
            String right = String.format("- %.2f", amount);

            int space = WIDTH - right.length() - left.length();
            if(space<0) { space = 1;}

            return left + " ".repeat(space) + right;
        }

        public String generate(){
            StringBuilder sb = new StringBuilder();
            String h1 = "=".repeat(WIDTH), h2 = "-".repeat(WIDTH);
            sb.append(h1).append("\n");
            int total = WIDTH-CAFE_NAME.length();
            int left = total/2;
            int right = total-left;
            sb.append(" ".repeat(left))
                    .append(CAFE_NAME)
                    .append(" ".repeat(right))
                    .append("\n");
            sb.append(h2).append("\n");

            DateTimeFormatter date = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
            sb.append(date.format(createdAt))
                    .append("\n")
                    .append(cachierName)
                    .append("\n")
                    .append(getId())
                    .append("\n")
                    .append(customer.name())
                    .append("\n")
                    .append(customer.loyaltyLevel())
                    .append("\n")
                    .append(" ");

            for(OrderItem item : items){
                sb.append(item.formatted())
                        .append("\n");
            }

            sb.append(" ")
                    .append(h2)
                    .append("\n");
            double subtotal = calculateSubtotal();
            sb.append( formatLine("Subtotal:", subtotal))
                    .append("\n");
            if(discount != null){
                double savings = discount.savings(subtotal);
                sb.append(formatNegLine("Discount: " + discount.getDescription(), savings))
                        .append("\n");
            }
            sb.append(h2)
                    .append("\n")
                    .append(formatLine("TOTAL DUE:", calculateTotal()))
                    .append("\n")
                    .append(h1)
                    .append("\n")
                    .append("Thank you!")
                    .append("\n")
                    .append(h1);

            return sb.toString();
        }
    }

    static class Builder {
        private final static int INITIAL_CAPACITY = 4;
        private final int id;
        private final Customer customer;
        private OrderItem[] items;
        private int size;
        private Discount discount;

        public Builder(int id, Customer customer){
            if(id<0){
                throw new IllegalArgumentException("Order ID must be greater than zero");
            }
            if(customer == null){
                throw new IllegalArgumentException("Customer cannot be equal to null");
            }
            this.id = id;
            this.customer = customer;
            this.items = new OrderItem[INITIAL_CAPACITY];
            this.size = 0;
        }

        private void grow(){
            OrderItem[] temp = new OrderItem[items.length*2];
            for(int i = 0; i<items.length; i++){
                temp[i] = items[i];
            }
            items = temp;
        }

        public Builder addItem(Product product, int quantity){
            if(size == items.length){
                grow();
            }
            OrderItem item = new OrderItem(product, quantity);
            items[size++] = item;

            return this;
        }

        public Builder addItem(Product product){
            return addItem(product, 1);
        }

        public Builder withDiscount(Discount discount){
            this.discount = discount;
            return this;
        }

        public Order build(){
            if(size<=0){
                throw new IllegalStateException("Order must contain at least one item");
            }
            OrderItem[] trim = new OrderItem[size];
            for(int i = 0; i<size; i++){
                trim[i] = items[i];
            }
            return new Order(id, trim, customer, discount);
        }
    }
}
