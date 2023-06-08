import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        String fileName = "data.json";
        String json = readString(fileName);
        List<Employee> list = jsonToList(json);
        for (Employee e : list) {
            System.out.println(e);
        }
    }

    public static String readString(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        return br.readLine();
    }

    public static List<Employee> jsonToList(String json) throws ParseException {
        List<Employee> list = new ArrayList<>();
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(json);
        JSONArray jsonArray = (JSONArray) obj;
        for (Object empl : jsonArray) {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            JSONObject jsonObject = (JSONObject) empl;
            Employee employee = gson.fromJson(String.valueOf(jsonObject), Employee.class);
            list.add(employee);
        }
        return list;
    }
}
