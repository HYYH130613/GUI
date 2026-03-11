abstract class DIscount {
    private final String description;

    DIscount(String description) {
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Discount cannot be null or empty");
        } else {
            this.description = description;
        }
    }

    public String getDescription() {
        return description;
    }

    abstract double apply(double originalPrice);

    public double savings(double originalPrice) {
        double withDiscount = apply(originalPrice);
        double savings = originalPrice - withDiscount;
        return savings;
    }

    @Override
    public String toString() {
        return "Discount: " + description;
    }

}
