package Data_augment;

import edu.stanford.nlp.ling.StringLabelFactory;
import edu.stanford.nlp.trees.*;
import edu.stanford.nlp.trees.Constituent;
import edu.stanford.nlp.util.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import tpe.*;

import java.io.*;
import java.util.*;

public class Data_augmentation_neo {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        JSONParser parser = new JSONParser();
        JSONObject obj2 = new JSONObject();
        ArrayList<List<String>> augmented_data = new ArrayList<>();
        ArrayList<List<String>> augmented_data2 = new ArrayList<>();
        ArrayList<List<String>> augmented_data3 = new ArrayList<>();
        re_test myTest = new re_test();


        try{
            Object obj = parser.parse(new FileReader("src/main/data/train_neg_edit_full.json"));
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
                    // list3�� TPE�� �̾Ƴ��� ����
//                    System.out.println(list3);

                    String pattern = "{S <EX .+> {VP <VB.* .+> * <JJ .+> * <(PR.*|NN.*) .+> * <IN .+> * <(PR.*|NN.*) .+> *}*}";
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
                        /////////////////////// �ܾ� ���� �ܰ� ////////////////////
                        String[] arr = list3.get(0).split(" ");
                        for(int a = 0; a < arr.length; a++){
                            ArrayList<String> melon = new ArrayList<>(Arrays.asList(arr));
                            int m_count = 0;
                            melon.remove(arr[a]);
                            String sum_melon = String.join(" ", melon);
//                            System.out.println(sum_melon);
                            for(String string:fruits){
//                                System.out.println(string);
                                if(sum_melon.contains(string) == true){
                                    m_count++;
                                }
                            }
                            if(m_count >= fruits.size()){
//                                System.out.println(sum_melon);
                                // sum_melon�� �ϳ��� ���ŷ� augment�� ����
//                                System.out.println(sum_melon);
                                List<String> newList = new ArrayList<>(list1);
                                newList.add(sum_melon);
                                newList.addAll(list2);
                                augmented_data.add(newList);
                                obj2.put("label", 0);
                                obj2.put("augmented_text", augmented_data);

                            }
                        }
                        ArrayList<String> first = new ArrayList<>();
                        ////////////////// �ܾ� ���� �ܰ� ////////////////

                        ///////////////SBAR ���� �ܰ� ////////////

                        Tree tree = (new PennTreeReader(new StringReader(myTest.replaced2(sentence)), new LabeledScoredTreeFactory(new StringLabelFactory()))).readTree();
                        Set<Constituent> treeConstituents = tree.constituents(new LabeledScoredConstituentFactory());
                        for (Constituent constituent : treeConstituents) {

                            if (constituent.label() != null && (constituent.label().toString().equals("SBAR"))) {
                                List<Tree> yoso = tree.getLeaves().subList(constituent.start(), constituent.end()+1);
                                String yoso_sum = StringUtils.join(yoso, " ");
//                                System.out.println("������ : "+yoso_sum);
                                first.add(yoso_sum);
//                                System.out.println("��� : " + first);
                            }
                        }
//                        System.out.println(first);
//                        System.out.println("Ʈ�� ���� : " + tree);
                        for(int n = 0; n < first.size(); n++){
//                            System.out.println("�̰� ���� ���� : "+list3.get(0));
//                            System.out.println("�̰� ������ ��� : "+first.get(n));
                            String output_sent = myTest.replaced3(list3.get(0), first.get(n));
//                            System.out.println("������1 : " + output_sent);
//                            System.out.println("���� ���� : " + list3.get(0));
//                            System.out.println("������ : " + output_sent);
                            int o_count = 0;
                            for(String string:fruits){
                                if(output_sent.contains(string) == true){
                                    o_count++;
                                }
                            }
//                            System.out.println("TPE : " + fruits);
                            if(o_count >= fruits.size()){
//                                System.out.println("������2 : " + output_sent);
                                List<String> newList = new ArrayList<>(list1);
                                newList.add(output_sent);
                                newList.addAll(list2);
                                augmented_data2.add(newList);
                                obj2.put("label2", 0);
                                obj2.put("augmented_text2", augmented_data2);
                            }
//                            System.out.println("------------");
                        }

                    ///////////// SBAR ���� �ܰ� ///////////////

                        for (Constituent constituent : treeConstituents) {

                            if (constituent.label() != null && (constituent.label().toString().equals("PP"))) {
                                List<Tree> yoso = tree.getLeaves().subList(constituent.start(), constituent.end()+1);
                                String yoso_sum = StringUtils.join(yoso, " ");
//                                System.out.println("������ : "+yoso_sum);
                                first.add(yoso_sum);
//                                System.out.println("��� : " + first);
                            }
                        }
//                        System.out.println(first);
//                        System.out.println("Ʈ�� ���� : " + tree);
                        for(int m = 0; m < first.size(); m++){
//                            System.out.println("�̰� ���� ���� : "+list3.get(0));
//                            System.out.println("�̰� ������ ��� : "+first.get(m));
                            String output_sent = myTest.replaced3(list3.get(0), first.get(m));
//                            System.out.println("������1 : " + output_sent);
//                            System.out.println("���� ���� : " + list3.get(0));
//                            System.out.println("������ : " + output_sent);
                            int o_count = 0;
                            for(String string:fruits){
                                if(output_sent.contains(string) == true){
                                    o_count++;
                                }
                            }
//                            System.out.println("TPE : " + fruits);
                            if(o_count >= fruits.size()){
//                                System.out.println("������2 : " + output_sent);
                                List<String> newList = new ArrayList<>(list1);
                                newList.add(output_sent);
                                newList.addAll(list2);
                                augmented_data3.add(newList);
                                obj2.put("label3", 0);
                                obj2.put("augmented_text3", augmented_data3);
                            }
//                            System.out.println("------------");
                        }


//                        String result_text = sum_text;
//                        List<String> newList = new ArrayList<String>(list1);
//                        newList.add(result_text);
//                        newList.addAll(list2);
//                        augmented_data.add(newList);
//                        obj2.put("label", 0);
//                        obj2.put("augmented_text", augmented_data);
//                        System.out.println(result_text);

                        try {

                            FileWriter file = new FileWriter("src/main/data/EX_neg.json");
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

        long end = System.currentTimeMillis(); //���α׷��� ������ ���� ���
        System.out.println( "���� �ð� : " + ( end - start )/1000.0 +"��");

    }
}
