package tpe;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Parsed_Sent_pass {
//        public ArrayList<String> JSON_to_parsed(){
//            ArrayList<String> first = new ArrayList<>();
//            org.json.simple.parser.JSONParser parser = new JSONParser();
//            try{
//                Object obj = parser.parse(new FileReader("C:/Users/ruin/Desktop/data/train_neg_edit.json"));
//                JSONObject jsonObject = (JSONObject) obj;
//                JSONArray data = (JSONArray) jsonObject.get("items");
//
////                for (int i = 0; i < data.size(); i++){
////                    JSONObject result = (JSONObject) data.get(i);
////                    first.add(String.valueOf(result.get("parsed_sentence")));
////                }
//
//
//
//            } catch (ParseException e) {
//                e.printStackTrace();
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            return first;
//    }

    public static void main(String[] args) {
        JSONParser parser = new JSONParser();
        re_test myTest = new re_test();
        try{
            Object obj = parser.parse(new FileReader("C:/Users/ruin/Desktop/data/train_neg_edit.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray data = (JSONArray) jsonObject.get("items");
//            System.out.println(data);
                for (int i = 0; i < data.size(); i++){
                    JSONObject result = (JSONObject) data.get(i);
                    JSONArray data2 = (JSONArray) result.get("parsed_sentence");
                    for(int j = 0; j < data2.size(); j++){
                        System.out.println(myTest.replaced2((String) data2.get(j)));
                        System.out.println(data2.get(j));
                    }
                    System.out.println("----------------------------------------------------");
//                    first.add(String.valueOf(result.get("parsed_sentence")));
                }



        } catch (ParseException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        for(int i = 0; i < first.size(); i++){
//            System.out.println(first.get(1));
//        }
    }
}
