package tpe;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Writing_JSON2 {
    public static void main(String[] args) {
        CoreNLP_Parser parser = new CoreNLP_Parser();
        JSON_parser parse = new JSON_parser();
        ArrayList<String> json_sentence = parse.json_parsing();
        Split_Sentence split = new Split_Sentence();
        ArrayList<String> text = split.split_sentence(json_sentence.get(1));
        System.out.println(json_sentence.get(1));


        JSONObject obj = new JSONObject();
        JSONArray list = new JSONArray();
        JSONArray list2 = new JSONArray();
        JSONArray data_list = new JSONArray();


        obj.put("txt", json_sentence.get(1));

        for (int i = 0; i < text.size(); i++){

            list.add(text.get(i));
//            list2.add(parser.Core_parser(text.get(i)));

        }
        obj.put("split_sentence", list);
//        obj.put("parsed_sentence", list2);
        data_list.add(obj);
        JSONObject itemlist = new JSONObject();
        itemlist.put("itemlist", data_list);




        try {

            FileWriter file = new FileWriter("/Users/ruin/Desktop/data/test2.json");
            file.write(itemlist.toJSONString());
            file.flush();
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
