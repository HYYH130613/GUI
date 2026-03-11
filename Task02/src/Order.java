import java.time.LocalDateTime;

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

}
