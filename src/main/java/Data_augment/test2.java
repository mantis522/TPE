package Data_augment;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class test2 {
    public static void main(String[] args) {
        JSONParser parser = new JSONParser();
        JSONObject obj2 = new JSONObject();
        ArrayList<List<String>> augmented_data2 = new ArrayList<>();

        try{
            Object obj = parser.parse(new FileReader("C:/Users/ruin/Desktop/data/edited_data/py_train_neg_edit_full.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray array_parsed = (JSONArray) jsonObject.get("parsed_sentence");
            JSONArray array_splited = (JSONArray) jsonObject.get("splited_sentence");
            ArrayList<String> test = (ArrayList<String>) array_parsed.get(0);


//            for(int i = 0; i< test.size(); i++){
//                System.out.println(test.get(i));
//            }

//            for(int i = 0; i < test.size(); i++){
//////                System.out.println(array_parsed.get(i));
////                ArrayList<String> augmented_data = new ArrayList<>();
////                String data2 = test.get(i);
//////                JSONArray data3 = (JSONArray) test.get(i);
////                System.out.println(data2);
//                System.out.println(test.get(i));
//            }




        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
