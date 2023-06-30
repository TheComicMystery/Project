import data.TodoListItemDTO;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome, sir. Type 'Help' to get help");
        data.CRUD crud = new data.CRUD();
        Scanner scanner = new Scanner(System.in);
        crud.ReadInfo();

        label:
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().toLowerCase();

            switch (input) {
                case "help" -> {
                    System.out.println("'List' - see all tasks");
                    System.out.println("'Add' - create task");
                    System.out.println("'Read' - see task");
                    System.out.println("'Update' - update task");
                    System.out.println("'Delete' - remove task");
                    System.out.println("'Find' - search for tasks");
                    System.out.println("'Sort' - sort tasks by name");
                    System.out.println("'Exit' - quit");
                }
                case "list" -> {
                    String result = crud.ShowAll();
                    System.out.println(result);
                }
                case "add" -> {
                    TodoListItemDTO item = new TodoListItemDTO();
                    System.out.println("Type name of the task:");
                    item.Name = scanner.nextLine();
                    System.out.println("Type detail of the task:");
                    item.Status = scanner.nextLine();
                    System.out.println("Type date (dd.mm.yyyy):");
                    String dateString = scanner.nextLine();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                    LocalDate date = LocalDate.parse(dateString, formatter);
                    item.Date = date.format(formatter);
                    crud.Create(item);
                }
                case "update" -> {
                    TodoListItemDTO item = new TodoListItemDTO();
                    System.out.println("ID of the task?");
                    item.Id = Integer.parseInt(scanner.nextLine());
                    System.out.println("Type name of the task:");
                    item.Name = scanner.nextLine();
                    System.out.println("Type detail of the task:");
                    item.Status = scanner.nextLine();
                    System.out.println("Type date (dd.mm.yyyy):");
                    String dateString = scanner.nextLine();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                    LocalDate date = LocalDate.parse(dateString, formatter);
                    item.Date = date.format(formatter);
                    crud.Update(item);
                }
                case "read" -> {
                    System.out.println("What is the task ID?");
                    int id = Integer.parseInt(scanner.nextLine());
                    String result = crud.Read(id);
                    System.out.println(result);
                }
                case "delete" -> {
                    System.out.println("What is the task ID?");
                    int id = Integer.parseInt(scanner.nextLine());
                    crud.Delete(id);
                }
                case "find" -> {
                    System.out.println("Type search pattern:");
                    String pattern = scanner.nextLine();
                    String result = crud.Find(pattern);
                    System.out.println(result);
                }
                case "sort" -> {
                    crud.Sort();
                    String result = crud.ShowAll();
                    System.out.println(result);
                }
                case "exit" -> {
                    String result = crud.Serialize();
                    crud.SaveInfo(result);
                    break label;
                }
                default -> System.out.println("This command is not supported");
            }
        }
    }
}