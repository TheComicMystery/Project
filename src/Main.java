import data.TodoListItemDTO;
import java.util.Scanner;

import data.TodoListItemDTO;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome, sir. Enter 'Help' to get help");

        data.CRUD crud = new data.CRUD();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().toLowerCase();

            if (input.equals("help")) {
                System.out.println("'Exit' - quit");
                System.out.println("'ShowAll' - see all data");
                System.out.println("'Create' - create object");
                System.out.println("'Read' - see data of object");
                System.out.println("'Update' - update object");
                System.out.println("'Delete' - remove object");
                System.out.println("'Find' - search for objects");
                System.out.println("'Sort' - sort objects");
                System.out.println("'Serial' - serialize data");
                System.out.println("'SaveInfo' - save data to the file");
                System.out.println("'ReadInfo' - read data from the file");
            } else if (input.equals("exit")) {
                break;
            } else if (input.equals("showall")) {
                String result = crud.ShowAll();
                System.out.println(result);
            } else if (input.equals("create")) {
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
            } else if (input.equals("serial")) {
                String result = crud.Serialize();
                System.out.println("Done: " + result);
            } else if (input.equals("saveinfo")) {
                String result = crud.Serialize();
                crud.SaveInfo(result);
                System.out.println("Data saved to file.");
            } else if (input.equals("readinfo")) {
                crud.ReadInfo();
                System.out.println("Data read from file.");
            } else {
                System.out.println("This command is not supported");
            }
        }
    }
}