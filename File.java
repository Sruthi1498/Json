import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class File {

    public static void main(String[] args) throws IOException, FileNotFoundException, ParseException {
        Scanner scan = new Scanner(System.in);
        File obj = new File();
        System.out.println("1.Create");
        System.out.println("2. Read");
        int n = scan.nextInt();
        switch (n) {
            case 1:
                obj.create();
                break;
            case 2:
                System.out.println("Enter the key to be deleted:");
                String key1 = scan.next();
                obj.read(key1);
                break;
            default:
                System.out.println("wrong option");
        }
    }

    void create() throws IOException, FileNotFoundException, ParseException {

        Scanner scan = new Scanner(System.in);
        String key = scan.next();
        int ans=check(key);
        if(ans<0)
        {
            System.out.println("Key already taken");
        }
        String name = scan.next();
        JSONObject user = new JSONObject();
        user.put("key", key);
        user.put("name", name);

        JSONObject Obj = new JSONObject();
        Obj.put("user", user);

        JSONArray userList = new JSONArray();
        userList.add(Obj);

        try (FileWriter file = new FileWriter("user.json", true)) {

            file.write(userList.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    int check(String key) throws FileNotFoundException, IOException, ParseException
    {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("user.json")) {
            Object obj = jsonParser.parse(reader);
            JSONArray userList = (JSONArray) obj;
            int index = userList.indexOf(key);
            return index;
        }
    }
    void read(String key) throws FileNotFoundException, IOException, ParseException {
        System.out.println("hello");
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("user.json")) {
            Object obj = jsonParser.parse(reader);
            JSONArray userList = (JSONArray) obj;
            int index = userList.indexOf(key);
            System.out.println(userList.get(index));
        }
    }



}