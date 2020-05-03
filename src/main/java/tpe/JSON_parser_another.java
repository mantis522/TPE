package tpe;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class JSON_parser_another {
    public static void main(String[] args) {
        JSONParser parser = new JSONParser();
        ArrayList<String> first = new ArrayList<>();
        try {
            // myJson.json파일을 읽어와 Object로 파싱
            Object obj = parser.parse(new FileReader("/Users/ruin/Desktop/data/test3.json"));
            JSONObject jsonObject = (JSONObject) obj;

            JSONArray data = (JSONArray) jsonObject.get("items");
            for(int i = 0; i < data.size(); i++){
                JSONObject result = (JSONObject) data.get(i);
                JSONArray par = (JSONArray) result.get("parsed_sentence");
                JSONArray split = (JSONArray) result.get("splited_sentence");

                for (int j = 0; j < par.size(); j++){
                    System.out.println(par.get(j));
                    System.out.println(split.get(j));
                }
                System.out.println("------------------------------");

//                System.out.println("review_text :: " + result.get("txt"));
//                System.out.println("parsed_sentence :: " + result.get("parsed_sentence"));

            }

        } catch (ParseException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
