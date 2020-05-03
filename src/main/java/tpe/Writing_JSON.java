package tpe;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;


public class Writing_JSON {
        public static void main(String[] args) {

            JSONObject obj = new JSONObject();
            obj.put("txt", "mkyong.com");
            obj.put("label", new Integer(0));

            JSONArray list = new JSONArray();
            list.add("msg 1");
            list.add("msg 2");
            list.add("msg 3");

            obj.put("messages", list);

            JSONArray list2 = new JSONArray();
            list2.add("test1");
            list2.add("test2");
            list2.add("test3");

            obj.put("test", list2);

            JSONArray data_list = new JSONArray();
            data_list.add(obj);

            JSONObject itemlist = new JSONObject();
            itemlist.put("items", data_list);

            try {

                FileWriter file = new FileWriter("/Users/ruin/Desktop/data/test.json");
                file.write(obj.toJSONString());
                file.flush();
                file.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.print(itemlist);

        }

}

