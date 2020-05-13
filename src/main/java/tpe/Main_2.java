package tpe;

import java.util.ArrayList;
import java.util.Collections;

public class Main_2 {
    public static void main(String[] args) {
        CoreNLP_Parser Cparser = new CoreNLP_Parser();
        ArrayList<String> first = new ArrayList<>();
//        ArrayList<ArrayList<String>> second = new ArrayList<>();
        Split_Sentence split_s = new Split_Sentence();
        ArrayList<String> splited_sentence = split_s.split_sentence("I like the girls who like soccer a lot.");
        for (int j =0; j < splited_sentence.size(); j++){
            String pattern = "<NP .+>";
            Patterns p1 = new Patterns(pattern);
            MakeTree tMT1 = new MakeTree();
            TPETree t1 = new TPETree();
            TreeNode tRoot1 = null;

            String[] str3 = tMT1.getStringArray(Cparser.Core_parser(splited_sentence.get(j)));
            tRoot1 = tMT1.getTree(str3);
            t1.setRootNode(tRoot1);
            t1.initTree();
            t1.printPostOrderingOfNodes(tRoot1);

            String relation1 = null;
            relation1 = p1.patternMatching(t1);
            String[] rel1 = relation1.split("::");
            ArrayList<String> apple = new ArrayList<String>();


            for(int i=0 ; i < rel1.length; i++){
                String rel3[] = rel1[i].split(",");
                if(rel3.length!=2) continue;
//        	System.out.print(rel[i]+" --> ");
//        	System.out.println(p.p.getIthNode(Integer.parseInt(rel2[0])).value+", "+t.getIthNode(Integer.parseInt(rel2[1])).value);

//            int to = Integer.parseInt(rel2[1]);

                if(p1.p.getIthNode(Integer.parseInt(rel3[0])).isLeafNode()){
//                System.out.println(to + " -----> " + t.getIthNode(Integer.parseInt(rel2[1])).value);
//                    System.out.println(t1.getIthNode(Integer.parseInt(rel3[1])).value);
                    apple.add(t1.getIthNode(Integer.parseInt(rel3[1])).value);

                }
//            System.out.print(t.getIthNode(Integer.parseInt(rel2[1])).value + " ");
                //System.exit(0);
            }
            Collections.reverse(apple);
            String sum_text = String.join(" ", apple);
            first.add(sum_text);
            first.add(splited_sentence.get(j));
        }
        System.out.println(first);
    }
}
