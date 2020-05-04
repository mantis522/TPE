package tpe;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class test2 {
    public static void main(String[] args) {
        JSONParser parser = new JSONParser();
        re_test myTest = new re_test();
        ArrayList<String> first = new ArrayList<>();
        try{
            Object obj = parser.parse(new FileReader("C:/Users/ruin/Desktop/data/train_neg_edit.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray data = (JSONArray) jsonObject.get("items");
//            System.out.println(data);
            for (int i = 0; i < data.size(); i++){
                JSONObject result = (JSONObject) data.get(i);
                JSONArray data2 = (JSONArray) result.get("parsed_sentence");
                for(int j = 0; j < data2.size(); j++){
                    String sentence = (String) data2.get(j);

                    String pattern = "<NP <NP * <JJ .+> <NN .+> *> <PP <IN .+> <NP * <JJ .+> <NN .+> *> *> *>";
//                    String pattern = "<NP * <JJ .+> <NN .+> *>";
//                    String pattern = "";
                    Patterns p = new Patterns(pattern);

                    MakeTree tMT = new MakeTree();  // �ʼ�
                    TPETree t = new TPETree();  // �ʼ�
                    TreeNode tRoot = null;

                    String[] str2 = tMT.getStringArray(sentence);   // �ʼ�
                    tRoot = tMT.getTree(str2);  // �ʼ�
                    t.setRootNode(tRoot);   // �ʼ�
                    t.initTree();   // �ʼ�
                    t.printPostOrderingOfNodes(tRoot);

                    String relation = null;

                    try{
                        relation = p.patternMatching(t);    // �ϴ��� �� �κи� �ʼ�
                        if (relation == null) {
//                            System.out.println("��ġ�ϴ� �κ��� �����ϴ�.");
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
                        System.out.println(sum_text);
                    }
                }
            }



        } catch (ParseException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}