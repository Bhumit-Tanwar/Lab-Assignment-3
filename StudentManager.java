import java.util.Scanner;

public class StudentManager implements RecordActions {

    private Integer rollNo;   // using wrapper class
    private String name;
    private String email;
    private String course;
    private Double marks;     // wrapper class

    Scanner sc = new Scanner(System.in);

    @Override
    public void addStudent() {
        try {
            System.out.print("Enter Roll No (Integer): ");
            rollNo = Integer.parseInt(sc.nextLine().trim()); // wrapper + autoboxing

            System.out.print("Enter Name: ");
            name = sc.nextLine().trim();
            if (name.isEmpty()) throw new Exception("Name cannot be empty!");

            System.out.print("Enter Email: ");
            email = sc.nextLine().trim();
            if (email.isEmpty()) throw new Exception("Email cannot be empty!");

            System.out.print("Enter Course: ");
            course = sc.nextLine().trim();
            if (course.isEmpty()) throw new Exception("Course cannot be empty!");

            System.out.print("Enter Marks (Double): ");
            marks = Double.parseDouble(sc.nextLine().trim()); // wrapper

            // Marks validation
            if (marks < 0 || marks > 100)
                throw new Exception("Marks must be between 0 and 100!");

            // MULTITHREADING → loading animation
            Thread t = new Thread(new Loader());
            t.start();
            t.join();

            System.out.println("Student added successfully!");

        } catch (NumberFormatException e) {
            System.out.println("Invalid number format!");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void displayStudent() {
        try {
            if (rollNo == null)
                throw new StudentNotFoundException("No student record found!");

            System.out.println("\n--- Student Details ---");
            System.out.println("Roll No: " + rollNo);
            System.out.println("Name: " + name);
            System.out.println("Email: " + email);
            System.out.println("Course: " + course);
            System.out.println("Marks: " + marks);

            // Calculate Grade
            String grade = "";
            if (marks >= 90) grade = "A";
            else if (marks >= 75) grade = "B";
            else if (marks >= 60) grade = "C";
            else if (marks >= 40) grade = "D";
            else grade = "F";

            System.out.println("Grade: " + grade);

        } catch (StudentNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        StudentManager sm = new StudentManager();
        Scanner sc = new Scanner(System.in);

        int choice;
        do {
            System.out.println("\n===== Student Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. Display Student");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");

            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> sm.addStudent();
                case 2 -> sm.displayStudent();
                case 3 -> System.out.println("Program execution completed.");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 3);

        sc.close();
    }
}
