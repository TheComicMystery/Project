import data.TodoListItemDTO;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome, sir. Print 'Help' to get help");
        data.CRUD crud = new data.CRUD();
        Scanner scanner = new Scanner(System.in);
        crud.ReadInfo();

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().toLowerCase();

            if (input.equals("help")) {
                System.out.println("'ShowAll' - see all tasks");
                System.out.println("'Add' - create task");
                System.out.println("'Read' - see task");
                System.out.println("'Update' - update task");
                System.out.println("'Delete' - remove task");
                System.out.println("'Find' - search for tasks");
                System.out.println("'Sort' - sort tasks");
                System.out.println("'SaveInfo' - save data to the file");
                System.out.println("'Exit' - quit");
            } else if (input.equals("showall")) {
                String result = crud.ShowAll();
                System.out.println(result);
            } else if (input.equals("add")) {
                TodoListItemDTO item = new TodoListItemDTO();
                System.out.println("Enter name of the task:");
                item.Name = scanner.nextLine();
                System.out.println("Enter status of the task:");
                item.Status = scanner.nextLine();
                crud.Create(item);
            } else if (input.equals("read")) {
                System.out.println("Enter Id of the task:");
                int id = Integer.parseInt(scanner.nextLine());
                String result = crud.Read(id);
                System.out.println(result);
            } else if (input.equals("update")) {
                TodoListItemDTO item = new TodoListItemDTO();
                System.out.println("Enter Id of the task:");
                item.Id = Integer.parseInt(scanner.nextLine());
                System.out.println("Enter name of the task:");
                item.Name = scanner.nextLine();
                System.out.println("Enter status of the task:");
                item.Status = scanner.nextLine();
                crud.Update(item);
            } else if (input.equals("delete")) {
                System.out.println("Enter Id of the task:");
                int id = Integer.parseInt(scanner.nextLine());
                crud.Delete(id);
            } else if (input.equals("find")) {
                System.out.println("Enter pattern:");
                String pattern = scanner.nextLine();
                String result = crud.Find(pattern);
                System.out.println(result);
            } else if (input.equals("sort")) {
                crud.Sort();
                String result = crud.ShowAll();
                System.out.println(result);
            } else if (input.equals("exit")) {
                    String result = crud.Serialize();
                    crud.SaveInfo(result);
                    break;
                }
              else {
                System.out.println("This command is not supported");
            }
        }
    }
}