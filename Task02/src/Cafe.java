public class Cafe {

    private final String name;
    private Product[] menu;
    private int menuSize;
    private Order[] orders;
    private int orderCount;

    public Cafe(String name, int menuCapacity, int orderCapacity){
        if(name == null || name.isBlank()){
            throw new IllegalArgumentException("Cafe name cannot be empty");
        }

        if(menuCapacity <= 0 || orderCapacity < 0){
            throw new IllegalArgumentException("Capacities must be greater than zero");
        }

        this.name = name;
        this.menu = new Product[menuCapacity];
        this.menuSize = 0;
        this.orders = new Order[orderCapacity];
        this.orderCount = 0;
    }

    public void addProduct(Product product){
        if(product == null){
            throw new IllegalArgumentException("The product cannot be equal to null");
        }
        if(menuSize == menu.length){
            Product[] newMenu = new Product[menuSize * 2];
            for(int i = 0; i < menu.length; i++){
                newMenu[i] = menu[i];
            }
            menu = newMenu;
        }

        menu[menuSize++] = product;
    }

    public boolean removeProduct(String productName) {
        int index = -1;
        for (int i = 0; i < menuSize; i++) {
            if (menu[i].name().equalsIgnoreCase(productName)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            return false;
        }

        for (int i = index; i < menuSize - 1; i++) {
            menu[i] = menu[i + 1];
        }
        menu[menuSize - 1] = null;
        menuSize--;
        return true;
    }

    public Product[] getProductsByCategory(String category){
        int counter = 0;
        for(int i = 0; i < menuSize; i++){
            if(menu[i].category().equalsIgnoreCase(category)){
                counter+=1;
            }
        }
        Product[] p = new Product[counter];
        int idx = 0;
        for(int i = 0; i < menuSize; i++){
            if(menu[i].category().equalsIgnoreCase(category)){
                p[idx++] = menu[i];
            }
        }
        return p;
    }

    public void sortMenuByPrice(){
        for(int i = 1; i<menuSize; i++){
            Product key = menu[i];
            int j = i-1;
            while(j >= 0 && menu[j].price() > key.price()){
                menu[j+1] = menu[j];
                j--;
            }
            menu[j+1] = key;
        }
    }

    public void displayMenu(){
        System.out.println("=== MENU: "+name.toUpperCase()+" ===");
        System.out.println();
        for(int i = 0; i < menuSize; i++){
            System.out.println((i+1)+". "+menu[i].formatted()+"\n");
        }
    }

    public void addOrder(Order order){
        if(order == null){
            throw new IllegalArgumentException("The order cannot be equal to null");
        }
        if(orderCount == orders.length){
            Order[] temp = new Order[orders.length * 2];
            for(int i = 0; i < orderCount-1; i++){
                temp[i] = orders[i];
            }
            orders = temp;
        }
        orders[orderCount++] = order;
    }

    public Order[] getOrdersByCustomer(String customerName){
        int count = 0;
        for (int i = 0; i < orderCount; i++) {
            if (orders[i].getCustomer().name().equalsIgnoreCase(customerName)) {
                count++;
            }
        }

        Order[] newOrders = new Order[count];
        int idx = 0;
        for (int i = 0; i < orderCount; i++) {
            if (orders[i].getCustomer().name().equalsIgnoreCase(customerName)) {
                newOrders[idx++] = orders[i];
            }
        }

        return newOrders;
    }

    public void sortOrdersByTotal(){
        Order temp;
        for(int i = 0; i < orderCount; i++){
            for(int j = 0; j < i+1; j++){
                if(orders[j].calculateTotal() > orders[i].calculateTotal()){
                    temp = orders[i];
                    orders[i] = orders[j];
                    orders[j] = temp;
                }
            }
        }
    }

    public String getName(){
        return name;
    }

    public int getMenuSize(){
        return menuSize;
    }

    public int getOrderCount(){
        return orderCount;
    }

    public Order[] getOrders(){
        Order[] copyOrders = new Order[orderCount];
        for(int i = 0; i < orderCount; i++){
            copyOrders[i] = orders[i];
        }
        return copyOrders;
    }

    public static class Statistics{
        private final Order[] orders;
        private final int count;

        public Statistics(Order[] orders, int count){
            if(orders == null || count < 0){
                throw new IllegalArgumentException("No orders to analyze");
            }
            this.orders = orders;
            this.count = count;
        }

        public double totalRevenue(){
            double sum = 0;
            for(int i = 0; i < count; i++){
                sum += orders[i].calculateTotal();
            }
            return sum;
        }

        public double averageOrderValue(){
            return totalRevenue()/count;
        }

        public Order mostExpensiveOrder(){
            Order max = orders[0];
            for(int i = 1; i < count; i++){
                if(orders[i].calculateTotal() > max.calculateTotal()){
                    max = orders[i];
                }
            }
            return max;
        }

        public Order cheapestOrder(){
            Order min = orders[0];
            for(int i = 1; i < count; i++){
                if(orders[i].calculateTotal() < min.calculateTotal()){
                    min = orders[i];
                }
            }
            return min;
        }

        public int totalItemsSold(){
            int sum = 0;
            for(int i = 0; i < count; i++){
                sum += orders[i].getItemCount();
            }
            return sum;
        }

        public String summary(){
            StringBuilder sb = new StringBuilder();
            sb.append("=== Statistics ===")
                    .append(String.format("\nNumber of orders: %d\n", count))
                    .append(String.format("Units sold: %d\n", totalItemsSold()))
                    .append(String.format("Total revenue: %.2f PLN\n", totalRevenue()))
                    .append(String.format("Average value: %.2f PLN\n", averageOrderValue()))
                    .append(String.format("Most expensive: #%d, (%.2f PLN)\n", mostExpensiveOrder().getId(), mostExpensiveOrder().calculateTotal()))
                    .append(String.format("Cheapest: #%d, (%.2f PLN)\n", cheapestOrder().getId(), cheapestOrder().calculateTotal()));

            return sb.toString();
        }
    }

    public class DailyReport{
        private final String reportDate;

        public DailyReport(String reportDate){
            if(reportDate == null || reportDate.isBlank()){
                throw new IllegalArgumentException("Report date cannot be empty");
            }
            this.reportDate = reportDate;
        }

        public String generate(){
            Statistics stats = new Statistics(orders, orderCount);
            StringBuilder sb = new StringBuilder();
            String sep = "=".repeat(50);
            sb.append(sep)
                    .append("\nDAILY REPORT: "+name)
                    .append("\nDate: "+reportDate)
                    .append("\n"+sep)
                    .append("\n")
                    .append("\nNumber of menu products: "+menuSize)
                    .append("\nNumber of orders: "+orderCount)
                    .append("\n");

            if(orderCount>0){
                sb.append("--- Order list---\n");
                for (int i = 0; i < orderCount; i++) {
                    Order order = orders[i];
                    sb.append(String.format("#%s | %s     | %.2f PLN | %d units %n",
                            order.getId(),
                            order.getCustomer().name(),
                            order.calculateTotal(),
                            order.getItemCount()));
                }
                sb.append("\n")
                        .append(String.format("Total revenue: %.2f PLN %n",stats.totalRevenue()))
                        .append(String.format("Average value: %.2f PLN %n", stats.averageOrderValue()))
                        .append("\n")
                        .append(sep)
                        .append("\n");
            }
            else {
                sb.append("No orders available.\n");
            }
            sb.append("\n").append(sep).append("\n");
            return sb.toString();
        }
    }
}
