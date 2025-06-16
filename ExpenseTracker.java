import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;


public class ExpenseTracker {
    private List<Expense> expenses;

    public void addExpense(Expense e) { 
        if (expenses == null) {
            expenses = new ArrayList<>();
        }
        expenses.add(e);

    }
    public void viewExpenses() { 
        if (expenses == null || expenses.isEmpty()) {
            System.out.println("No expenses recorded.");
            return;
        }
        for (int i = 0; i < expenses.size(); i++) {
            Expense e = expenses.get(i);
            System.out.printf("%d: %s | %s | %.2f | %s%n", i + 1, e.getDate(), e.getCategory(), e.getAmount(), e.getDescription());
        }
    }
    public void deleteExpense(int index) { 
        if (expenses == null || index < 0 || index >= expenses.size()) {
            System.out.println("Invalid index. No expense deleted.");
            return;
        }
        expenses.remove(index);
        System.out.println("Expense deleted successfully.");
    }
    public void saveToFile(String filename) { 
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filename))) {
            for (Expense e : expenses) {
                writer.write(String.format("%s,%s,%.2f,%s%n", e.getDate().format(DateTimeFormatter.ISO_LOCAL_DATE), e.getCategory(), e.getAmount(), e.getDescription()));
            }
            System.out.println("Expenses saved to " + filename);
        } catch (IOException e) {
            System.err.println("Error saving expenses: " + e.getMessage());
        }
    }
    public void loadFromFile(String filename) { 
        expenses = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length != 4) {
                    System.err.println("Invalid expense format: " + line);
                    continue;
                }
                try {
                    LocalDate date = LocalDate.parse(parts[0], DateTimeFormatter.ISO_LOCAL_DATE);
                    String category = parts[1];
                    double amount = Double.parseDouble(parts[2]);
                    String description = parts[3];
                    Expense e = new Expense(date.toString(), category, amount, description);
                    expenses.add(e);
                } catch (DateTimeParseException | NumberFormatException e) {
                    System.err.println("Error parsing expense: " + line + " - " + e.getMessage());
                }
            }
            System.out.println("Expenses loaded from " + filename);
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filename);
        }
    }
}
