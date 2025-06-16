public class Main {
    public static void main(String[] args) {
        // Console menu: switch-case to interact with user
        ExpenseTracker tracker = new ExpenseTracker();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Expense Tracker Menu:");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Delete Expense");
            System.out.println("4. Save Expenses to File");
            System.out.println("5. Load Expenses from File");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            
            switch (choice) {
                case 1:
                    System.out.print("Enter date (yyyy-MM-dd): ");
                    String date = scanner.nextLine();
                    System.out.print("Enter category: ");
                    String category = scanner.nextLine();
                    System.out.print("Enter amount: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine(); // consume newline
                    System.out.print("Enter description: ");
                    String description = scanner.nextLine();
                    
                    Expense expense = new Expense(date, category, amount, description);
                    tracker.addExpense(expense);
                    break;
                case 2:
                    tracker.viewExpenses();
                    break;
                case 3:
                    System.out.print("Enter index of expense to delete: ");
                    int index = scanner.nextInt() - 1; // convert to zero-based index
                    tracker.deleteExpense(index);
                    break;
                case 4:
                    System.out.print("Enter filename to save expenses: ");
                    String saveFilename = scanner.nextLine();
                    tracker.saveToFile(saveFilename);
                    break;
                case 5:
                    System.out.print("Enter filename to load expenses: ");
                    String loadFilename = scanner.nextLine();
                    tracker.loadFromFile(loadFilename);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
