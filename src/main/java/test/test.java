package test;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class test {
    public static void main(String[] args) {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("/Users/ruin/IdeaProjects/TPE/src/main/resources/train_neg_edit.json"));
//            Object obj = parser.parse(new FileReader("C:/Users/ruin/IdeaProjects/core-nlp-example/src/main/resources/train_neg_edit.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray data = (JSONArray) jsonObject.get("items");
            for (int i = 0; i < data.size(); i++) {
                JSONObject result = (JSONObject) data.get(i);
                JSONArray data2 = (JSONArray) result.get("splited_sentence");
//                System.out.println(data2);
                for(int j = 0; j < data2.size(); j++) {
                    String sentence = (String) data2.get(j);
//                    System.out.println(data2.subList(0,j) + data2.subList());
//                    data2.subList(1,3);
                }
                System.out.println("--------------------------------------------");
            }

            // ����Ʈ�� �ִ� ������� �ϳ��� ���� ��� �ٲ۴��� �ٸ� ������ ����



        } catch (ParseException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}