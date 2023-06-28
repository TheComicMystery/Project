import java.io.FileWriter;
import java.util.Scanner;
import com.google.gson.Gson;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome, sir. Enter Help to get help");
        CRUD crud = new CRUD();
        while  (true) {
            TodoListItemDTO item = new TodoListItemDTO();
            Scanner in = new Scanner(System.in);
            String Input = String.valueOf(in.next());
            if (Input.equals("Help")) {
                System.out.println("'Exit' - quit; " + "'ShowAll' - see all data; " + "'Create' - create object; " + "'Read' - see data of object; " + "'Update' - update object; " + "'Delete' - remove object; " + "'Find' - search for objects; " + "'Sort' - sort objects; " + "'Serial' - serialize data; " + "'SaveInfo' - safe data to the file; " + "'ReadInfo' - read data from the file.");
            }

            else if (Input.equals("Exit")) {
                return;
            }
            else if (Input.equals("ShowAll")) {
                String result = crud.ShowAll();
                System.out.println(result);
            }
            else if (Input.equals("Create")) {
                System.out.println("Enter name of the task");
                item.Name = String.valueOf(in.next());
                System.out.println("Enter status of the task");
                item.Status = (String.valueOf(in.next()));
                crud.Create(item);
            }
            else if (Input.equals("Read")) {

                System.out.println("Enter Id of the task");
                int id = Integer.parseInt(String.valueOf(in.next()));
                String result = crud.Read(id);
                System.out.println(result);
            }
            else if (Input.equals("Update")) {
                System.out.println("Enter Id of the task");
                item.Id = Integer.parseInt(String.valueOf(in.next()));
                System.out.println("Enter name of the task");
                item.Name = String.valueOf(in.next());
                System.out.println("Enter status of the task");
                item.Status = (String.valueOf(in.next()));
                crud.Update(item);
            }
            else if (Input.equals("Delete")) {
                System.out.println("Enter Id of the task");
                int id = Integer.parseInt(String.valueOf(in.next()));
                crud.Delete(id);
            }
            else if (Input.equals("Find")) {
                System.out.println("Enter pattern");
                String pattern = (String.valueOf(in.next()));
                String result = crud.Find(pattern);
                System.out.println(result);
            }
            else if (Input.equals("Sort")) {
                crud.Sort();
                String result = crud.ShowAll();
                System.out.println(result);
            }
            else if (Input.equals("Serial")) {
                String result = crud.Serialize();
                System.out.println("Done:" + result);
            }
            else if (Input.equals("SaveInfo")) {
                String result = crud.Serialize();
                crud.SaveInfo(result);
            } else if (Input.equals("ReadInfo")) {
                crud.ReadInfo();
            } else {
                System.out.println("This command is not supported");
            }}
    }}