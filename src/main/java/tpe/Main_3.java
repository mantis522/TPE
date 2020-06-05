package tpe;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Main_3 {
    public static void main(String[] args) {
        CoreNLP_Parser parser = new CoreNLP_Parser();
        JSON_parser parse = new JSON_parser();
        ArrayList<String> json_sentence = parse.json_parsing();
        Split_Sentence split = new Split_Sentence();
        JSONArray data_list = new JSONArray();
        JSONObject itemlist = new JSONObject();
        int count = 0;

        for (int i = 0; i < json_sentence.size(); i++){
            System.out.println(count);
            JSONObject obj = new JSONObject();
            JSONArray list = new JSONArray();
            JSONArray list2 = new JSONArray();
            String original_sentence = json_sentence.get(i);

            ArrayList<String> splited_origin = split.split_sentence(original_sentence);
            obj.put("index", i);
            obj.put("label", 0);
            obj.put("txt", original_sentence);
//            list.add(splited_origin);
//            obj.put("splited_sentence", list);
//            System.out.println(splited_origin);
            for (int j = 0; j < splited_origin.size(); j++){
//                if(parser.isBlank(splited_origin.get(j)) == false){
                    list.add(splited_origin.get(j));
                    list2.add(parser.Core_parser(splited_origin.get(j)));
//                }
//                else{
//                    list.add(splited_origin.get(j));
//                    list2.add("space");
//                }
            }
            obj.put("splited_sentence", list);
            obj.put("parsed_sentence", list2);
            data_list.add(obj);
            itemlist.put("items", data_list);
//            System.out.println(list);
            count = count + 1;
        }

//        System.out.println(list);
        try {

            FileWriter file = new FileWriter("src/main/resources/test_neg.json");
            file.write(itemlist.toJSONString());
            file.flush();
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
