import java.time.LocalDate;

public class Expense {
    private LocalDate date;
    private String category;
    private double amount;
    private String description;

    public Expense(String date, String category, double amount, String description) {
        this.date = LocalDate.parse(date); // expects yyyy-MM-dd
        this.category = category;
        this.amount = amount;
        this.description = description;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = LocalDate.parse(date);
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
