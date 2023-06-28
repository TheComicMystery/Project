import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;

public class CRUD {

    public CRUD(){
        Todolist = new ArrayList<>();
    }
    private ArrayList<TodoListItemDTO> Todolist;
    private int MaxIndex = 1;
    public void Create(TodoListItemDTO item) {
        item.Id = MaxIndex;
        Todolist.add(item);
        MaxIndex++;
    }
    public void Update(TodoListItemDTO itemToUpdate) {
        for (TodoListItemDTO item:
                Todolist) {
            if(item.Id == itemToUpdate.Id) {
                item.Name = itemToUpdate.Name;
                item.Status = itemToUpdate.Status;
                break;
            }
        }
    }
    public void Delete(int id) {
        for (TodoListItemDTO item:
                Todolist) {
            if(item.Id == id) {
                Todolist.remove(item);
                break;
            }
        }
    }

    public String Read(int id) {
        String result = "";
        for (TodoListItemDTO item:
                Todolist) {
            if(item.Id == id) {
                result = "Id: " + item.Id + " Name: " + item.Name  + " Status: " + item.Status;
                break;
            }
        }
        return result;
    }

    public String ShowAll(){
        String result = "";
        for (TodoListItemDTO item:
                Todolist) {
            result += Read(item.Id) + "\r\n";
        }
        return result;
    }
    public String Find(String pattern) {

        String result = "";
        for (TodoListItemDTO item:
                Todolist) {
            if(item.Name.contains(pattern)||item.Status.contains(pattern)) {
                result += Read(item.Id) + "\r\n";
            }
        }
        return result;
    }

    public void Sort() {
        Todolist.sort(Comparator.comparing(item -> item.Name));
    }
    public String Serialize() {
        Gson gson = new Gson();
        String jsonString = gson.toJson(Todolist);
        return jsonString;
    }
    public void SaveInfo(String content) {
        File file = new File("Todolist.json");
        try (var fw = new FileWriter(file)) {
            fw.write(content);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void ReadInfo() {
        Gson gson = new Gson();
        try {
            String lines = Files.readString(Paths.get("Todolist.json"));
            Todolist.clear();
            Type userListType = new TypeToken<ArrayList<TodoListItemDTO>>(){}.getType();
            Todolist = gson.fromJson(lines, userListType);
            MaxIndex = Todolist.size() + 1;
        }
        catch(Exception e) {
            e.getStackTrace();
        }
    }
}