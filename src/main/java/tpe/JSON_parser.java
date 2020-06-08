package tpe;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class JSON_parser {

    public ArrayList<String> json_parsing(){
        ArrayList<String> first = new ArrayList<>();
        ArrayList<ArrayList<String>> second = new ArrayList<>();
        JSONParser parser = new JSONParser();

        try {
            // myJson.json파일을 읽어와 Object로 파싱
            Object obj = parser.parse(new FileReader("src/main/resources/train_pos_full.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray data = (JSONArray) jsonObject.get("data");
            for(int i = 0; i < data.size(); i++){
                JSONObject result = (JSONObject) data.get(i);
                first.add(result.get("txt").toString());
//                System.out.println("review_text :: " + result.get("txt"));
//                System.out.println("label :: " +result.get("label"));
            }

        } catch (ParseException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return first;
    }


    public static void main(String[] args) {
        JSON_parser parse = new JSON_parser();
        ArrayList<String> first = parse.json_parsing();
//        System.out.println(first.get((1)));
        for (int i = 0; i < first.size() ; i++){
            System.out.println("review text " + i + " :: " + first.get(i));
        }
    }
}
