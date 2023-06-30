package data;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;

public class CRUD {
    private ArrayList<TodoListItemDTO> Todolist;
    private int MaxIndex;

    public CRUD() {
        Todolist = new ArrayList<>();
        MaxIndex = 1;
    }

    public void Create(TodoListItemDTO item) {
        item.Id = MaxIndex++;
        Todolist.add(item);
        Serialize();
    }

    public void Update(TodoListItemDTO itemToUpdate) {
        for (TodoListItemDTO item : Todolist) {
            if (item.Id == itemToUpdate.Id) {
                item.Name = itemToUpdate.Name;
                item.Status = itemToUpdate.Status;
                break;
            }
        }
        Serialize();
    }

    public void Delete(int id) {
        Todolist.removeIf(item -> item.Id == id);
        Serialize();
    }

    public String Read(int id) {
        for (TodoListItemDTO item : Todolist) {
            if (item.Id == id) {
                return "Id: " + item.Id + " Name: " + item.Name + " Status: " + item.Status + " Date: " + item.Date;
            }
        }
        return "";
    }

    public String ShowAll() {
        StringBuilder result = new StringBuilder();
        for (TodoListItemDTO item : Todolist) {
            result.append(Read(item.Id)).append("\r\n");
        }
        return result.toString();
    }

    public String Find(String pattern) {
        StringBuilder result = new StringBuilder();
        for (TodoListItemDTO item : Todolist) {
            if (item.Name.contains(pattern) || item.Status.contains(pattern))   {
                result.append(Read(item.Id)).append("\r\n");
            }
        }
        return result.toString();
    }

    public void Sort() {
        Todolist.sort(Comparator.comparing(item -> item.Name));
    }

    public String Serialize() {
        Gson gson = new Gson();
        return gson.toJson(Todolist);
    }

    public void SaveInfo(String content) {
        FileHandler.writeToFile(content, "Todolist.json");
    }

    public void ReadInfo() {
        String jsonContent = FileHandler.readFromFile("Todolist.json");
        if (jsonContent != null) {
            Gson gson = new Gson();
            Type userListType = new TypeToken<ArrayList<TodoListItemDTO>>(){}.getType();
            Todolist = gson.fromJson(jsonContent, userListType);
            MaxIndex = Todolist.stream().mapToInt(item -> item.Id).max().orElse(0) + 1;
        } else {
            Todolist.clear();
            MaxIndex = 1;
        }
        Serialize();
    }
}