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
    public static void main(String[] args) {
        ArrayList<String> first = new ArrayList<>();
        ArrayList<ArrayList<String>> second = new ArrayList<>();

        JSONParser parser = new JSONParser();

        try {
            // myJson.json파일을 읽어와 Object로 파싱
            Object obj = parser.parse(new FileReader("C:/Users/ruin/Desktop/data/for_augmentation/train_neg.json"));
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

        for(int j=0; j < first.size(); j++){
            System.out.println("text review :: " + first.get(j));
        }

    }
}
