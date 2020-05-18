package test;

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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class test {
    public static void main(String[] args) {
        JSONParser parser = new JSONParser();
        try {
//            Object obj = parser.parse(new FileReader("/Users/ruin/IdeaProjects/TPE/src/main/resources/train_neg_edit.json"));
            Object obj = parser.parse(new FileReader("C:/Users/ruin/IdeaProjects/core-nlp-example/src/main/resources/train_pos_edit.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray data = (JSONArray) jsonObject.get("items");
            for (int i = 0; i < data.size(); i++) {
                JSONObject result = (JSONObject) data.get(i);
                JSONArray data2 = (JSONArray) result.get("parsed_sentence");
                JSONArray data3 = (JSONArray) result.get("splited_sentence");

//                System.out.println(data2);
                for(int j = 0; j < data2.size(); j++) {
                    String sentence = (String) data2.get(j);
                    String origin_sentence = (String) data3.get(j);
                    List<String> list1 = new ArrayList<String>();
                    List<String> list2 = new ArrayList<String>();
                    list1 = data3.subList(0, j);
                    list2 = data3.subList(j+1, data2.size());
//                    List<String> newList = new ArrayList<String>(list1);


                    /* /////////// 패턴 관련 부분 //////////////// */
                    String pattern = "{ROOT {S {NP <(PR.*|NN.*) .+>}* {VP <VB.* .+> {NP <(PR.*|NN.*) .+> *}*}*}*}";
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
                        List<String> newList = new ArrayList<String>(list1);
                        newList.add(result_text);
//                        System.out.println();
//                        System.out.println(result_text);
                        newList.addAll(list2);
//                        System.out.println(newList);
                        System.out.println();
                        for(int o = 0; o < newList.size(); o++){
                            System.out.print(newList.get(o) + " ");
//                            System.out.println("\n");
                        }
                    }


//                    newList.add(sentence + "신문장");
//                    newList.addAll(list2);
//                    System.out.println(newList);
                }
                System.out.println("\n");
                System.out.println("--------------------------------------------");
            }

            // 리스트에 있는 문장들을 하나씩 빼서 요소 바꾼다음 다른 문장들과 연결



        } catch (ParseException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}