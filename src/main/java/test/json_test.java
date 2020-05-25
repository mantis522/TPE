package test;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class json_test {
    public static void main(String[] args) {
        JSONParser parser = new JSONParser();
        JSONObject obj2 = new JSONObject();
        ArrayList<List<String>> augmented_data = new ArrayList<>();

        try{
            Object obj = parser.parse(new FileReader("C:/Users/ruin/Desktop/IPython/deps/Data_Augmentation/test.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray array_parsed = (JSONArray) jsonObject.get("parsed_sentence");
            JSONArray array_splited = (JSONArray) jsonObject.get("splited_sentence");
            for (int i = 0; i < array_parsed.size(); i++){
                System.out.println(array_parsed.get(i));
            }

//            JSONArray data = (JSONArray) jsonObject.get("item");
//            System.out.println(jsonObject);



        } catch (ParseException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
