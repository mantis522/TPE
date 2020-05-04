package tpe;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class test {
    public static void main(String[] args) {
        JSONParser parser = new JSONParser();
        re_test myTest = new re_test();
        ArrayList<String> first = new ArrayList<>();
        try {
            Object obj = parser.parse(new FileReader("C:/Users/ruin/Desktop/data/train_neg_edit.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray data = (JSONArray) jsonObject.get("items");
//            System.out.println(data);
            for (int i = 0; i < data.size(); i++) {
                JSONObject result = (JSONObject) data.get(i);
                JSONArray data2 = (JSONArray) result.get("parsed_sentence");
                for(int j = 0; j < data2.size(); j++) {
                    String sentence = (String) data2.get(j);
                    System.out.println(myTest.replaced2(sentence));
                }
            }

        }catch (ParseException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
