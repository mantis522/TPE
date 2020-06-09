package Data_augment;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import tpe.MakeTree;
import tpe.Patterns;
import tpe.TPETree;
import tpe.TreeNode;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Data_augmentation_new {
    public static void main(String[] args) {
        JSONParser parser = new JSONParser();
        JSONObject obj2 = new JSONObject();
        ArrayList<List<String>> augmented_data = new ArrayList<>();

        try{
            Object obj = parser.parse(new FileReader("C:/Users/ruin/Desktop/data/edited_data/train_neg_edit_9940_9959.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray array_parsed = (JSONArray) jsonObject.get("parsed_sentence");
            JSONArray array_splited = (JSONArray) jsonObject.get("splited_sentence");
            for (int i = 0; i < array_parsed.size(); i++) {
                JSONArray data2 = (JSONArray) array_parsed.get(i);
                JSONArray data3 = (JSONArray) array_splited.get(i);
                for (int j = 0; j < data2.size(); j++) {
                    String sentence = (String) data2.get(j);
                    String origin_sentence = (String) data3.get(j);
                    List<String> list1 = new ArrayList<>();
                    List<String> list2 = new ArrayList<>();
                    List<String> list3 = new ArrayList<>();
                    list1 = data3.subList(0, j);
                    list2 = data3.subList(j+1, data2.size());
                    list3 = data3.subList(j, j+1);
                    // list3이 TPE를 뽑아내는 문장
//                    System.out.println(list3);
//                    System.out.println("첫번째 : " + list1);
//                    System.out.println("두번째 : " + list3);
//                    System.out.println("세번째 : " + list2);

                    String pattern = "{S <EX .+> {VP <VB.* .+> * <JJ .+> * <(PR.*|NN.*) .+> * <IN .+> * <(PR.*|NN.*) .+> *}*}";
                    Patterns p = new Patterns(pattern);

                    MakeTree tMT = new MakeTree();  // 필수
                    TPETree t = new TPETree();  // 필수
                    TreeNode tRoot = null;

                    String[] str2 = tMT.getStringArray(sentence);   // 필수
                    tRoot = tMT.getTree(str2);  // 필수
                    t.setRootNode(tRoot);   // 필수
                    t.initTree();   // 필수
                    t.printPostOrderingOfNodes(tRoot);

                    String relation = null;

                    try{
                        relation = p.patternMatching(t);    // 일단은 이 부분만 필수
                        if (relation == null) {
//                            System.out.println("일치하는 부분이 없습니다.");
                            relation = "::";
//                            return; // Not matched.
                        }
                    }catch(Exception e){

                        return;

                    }

                    ArrayList<String> fruits = new ArrayList<String>();

                    String[] rel = relation.split("::");
                    for(int k = 0 ; k < rel.length; k++){
                        String rel2[] = rel[k].split(",");
                        if(rel2.length!=2) continue;


                        if(p.p.getIthNode(Integer.parseInt(rel2[0])).isLeafNode()){
                            fruits.add(t.getIthNode(Integer.parseInt(rel2[1])).value);
                        }
                    }

                    Collections.reverse(fruits);


                    String sum_text = String.join(" ",fruits);

                    if(sum_text.length() > 2) {
                        String result_text = sum_text;
                        System.out.println(result_text);
                        List<String> newList = new ArrayList<String>(list1);
                        newList.add(result_text);
                        newList.addAll(list2);
                        augmented_data.add(newList);
                        obj2.put("label", 0);
                        obj2.put("augmented_text", augmented_data);
//                        System.out.println(result_text);

                        try {

                            FileWriter file = new FileWriter("/Users/ruin/Desktop/data/data_augmentation2/neg/test.json");
                            file.write(obj2.toJSONString());
                            file.flush();
                            file.close();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }


            }
//            System.out.println(augmented_data);



        } catch (ParseException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
