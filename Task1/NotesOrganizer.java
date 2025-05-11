import java.io.*;
import java.util.Scanner;

public class NotesOrganizer {

    static final String DIRECTORY = "notes";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Create notes folder if it doesn't exist
        File dir = new File(DIRECTORY);
        if (!dir.exists()) {
            dir.mkdir();
        }

        while (true) {
            System.out.println("\n==== Smart Notes Organizer ====");
            System.out.println("1. Create Note");
            System.out.println("2. Read Note");
            System.out.println("3. Update Note");
            System.out.println("4. Delete Note");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    createNote(sc);
                    break;
                case 2:
                    readNote(sc);
                    break;
                case 3:
                    updateNote(sc);
                    break;
                case 4:
                    deleteNote(sc);
                    break;
                case 5:
                    System.out.println("Thank you for using Notes Organizer!");
                    return;
                default:
                    System.out.println("Invalid choice. Try again!");
            }
        }
    }

    static void createNote(Scanner sc) {
        try {
            System.out.print("Enter note title: ");
            String title = sc.nextLine();
            String fileName = DIRECTORY + "/" + title + ".txt";
            FileWriter writer = new FileWriter(fileName);
            System.out.println("Enter your note content (end with a single line 'EOF'):");
            StringBuilder content = new StringBuilder();
            while (true) {
                String line = sc.nextLine();
                if (line.equals("EOF")) break;
                content.append(line).append("\n");
            }
            writer.write(content.toString());
            writer.close();
            System.out.println("Note saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving note: " + e.getMessage());
        }
    }

    static void readNote(Scanner sc) {
        try {
            System.out.print("Enter note title to read: ");
            String title = sc.nextLine();
            String fileName = DIRECTORY + "/" + title + ".txt";
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            System.out.println("\n--- Note Content ---");
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error reading note: " + e.getMessage());
        }
    }

    static void updateNote(Scanner sc) {
        try {
            System.out.print("Enter note title to update: ");
            String title = sc.nextLine();
            String fileName = DIRECTORY + "/" + title + ".txt";

            File file = new File(fileName);
            if (!file.exists()) {
                System.out.println("Note does not exist.");
                return;
            }

            System.out.println("Enter new content for the note (end with 'EOF'):");
            StringBuilder newContent = new StringBuilder();
            while (true) {
                String line = sc.nextLine();
                if (line.equals("EOF")) break;
                newContent.append(line).append("\n");
            }

            FileWriter writer = new FileWriter(fileName);
            writer.write(newContent.toString());
            writer.close();
            System.out.println("Note updated successfully.");
        } catch (IOException e) {
            System.out.println("Error updating note: " + e.getMessage());
        }
    }

    static void deleteNote(Scanner sc) {
        System.out.print("Enter note title to delete: ");
        String title = sc.nextLine();
        String fileName = DIRECTORY + "/" + title + ".txt";
        File file = new File(fileName);

        if (file.exists()) {
            if (file.delete()) {
                System.out.println("Note deleted successfully.");
            } else {
                System.out.println("Failed to delete the note.");
            }
        } else {
            System.out.println("Note not found.");
        }
    }
}
