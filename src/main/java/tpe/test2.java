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
            Object obj = parser.parse(new FileReader("C:/Users/ruin/IdeaProjects/core-nlp-example/src/main/resources/train_pos_edit.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray data = (JSONArray) jsonObject.get("items");
//            System.out.println(data);
            for (int i = 0; i < data.size(); i++){
                JSONObject result = (JSONObject) data.get(i);
                JSONArray data2 = (JSONArray) result.get("parsed_sentence");
                for(int j = 0; j < data2.size(); j++){
                    String sentence = (String) data2.get(j);
//                    String pattern = "<S <NP <(NN.*|PRP) .+> *>  <VP <VB.* .+> <PP <IN .+> <NP <NN.* .+> * > *> * > *>";
//                    String pattern = "<S * <NP <(NN.*|PRP) .+>> <VP <VB.* .+> <NP <NP *  <(NN.*|PRP) .+> *> *> *> *>";
//                    String pattern = "<S <NP.* * <NN.*|PR.* .+> *> * <VP.* * <VB.* .+> <NP * <NN.*|RP.* .+> * > * > * >";
//                    String pattern = "<NP <NP * <JJ .+> <NN .+> *> <PP <IN .+> <NP * <JJ .+> <NN .+> *> *> *>";
//                    String pattern = "<NP <NP *  <NN.* .+> *> <PP <IN .+> <NP * <VB.* .+> <NN.* .+> * > *> *>";
//                    String pattern = "<S <NP <NP <PR.* .+> <JJ.* .+> <NN.* .+> *> *> <VP <VB.* .+> <NP <NP <DT.* .+> <JJ.* .+> <NN.* .+> *> *> *> *>";
                    String pattern = "<S <NP <NP *  <NN.* .+> *> *> <VP <VB.* .+> <NP <NP * <NN.* .+> *> *> *> *>";




//                    String pattern = "<NP * <JJ .+> <NN .+> *>";
//                    String pattern = "";
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
