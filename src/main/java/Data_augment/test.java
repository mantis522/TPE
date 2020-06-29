package Data_augment;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String[] args) {
        JSONParser parser = new JSONParser();
        JSONObject obj2 = new JSONObject();
//        ArrayList<String> augmented_data = new ArrayList<>();
        ArrayList<List<String>> augmented_data2 = new ArrayList<>();

        try{
            Object obj = parser.parse(new FileReader("C:/Users/ruin/Desktop/data/edited_data/py_train_neg_edit_full.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray array_parsed = (JSONArray) jsonObject.get("parsed_sentence");
            JSONArray array_splited = (JSONArray) jsonObject.get("splited_sentence");
            System.out.println(array_parsed);
            for(int i = 0; i < array_parsed.size(); i++){
                System.out.println(array_parsed.get(i));
//                ArrayList<String> augmented_data = new ArrayList<>();
//                JSONArray data2 = (JSONArray) array_parsed.get(i);
//                JSONArray data3 = (JSONArray) array_splited.get(i);
//                for(int j = 0; j < data2.size(); j++){
//                    String sentence = (String) data2.get(j);
//                    String origin_sentence = (String) data3.get(j);
//                    System.out.println(sentence);
//                }
            }




        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
