package tpe;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class test {
    public static void main(String[] args) {
        CoreNLP_Parser parser = new CoreNLP_Parser();
        JSON_parser parse = new JSON_parser();
        ArrayList<String> json_sentence = parse.json_parsing();
        Split_Sentence split = new Split_Sentence();
//        JSONArray data_list = new JSONArray();
//        JSONObject itemlist = new JSONObject();

        for(int i = 0; i < json_sentence.size(); i++){
            JSONObject obj = new JSONObject();
            JSONArray list = new JSONArray();
            JSONArray list2 = new JSONArray();
            String original_sentence = json_sentence.get(i);

            ArrayList<String> splited_origin = split.split_sentence(original_sentence);
            System.out.println(splited_origin);

            for (int j = 0; j < splited_origin.size(); j++){
                System.out.println(splited_origin.get(j));
//                list.add(splited_origin.get(j));
//                list2.add(parser.Core_parser(splited_origin.get(j)));
            }
            System.out.println();
        }
    }
}
