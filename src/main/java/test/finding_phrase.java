package test;

import edu.stanford.nlp.ling.StringLabelFactory;
import edu.stanford.nlp.trees.*;
import edu.stanford.nlp.util.StringUtils;
import tpe.re_test;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class finding_phrase {
    public static void main(String[] args) throws IOException {
        re_test myTest = new re_test();
        ArrayList<String> first = new ArrayList<>();
//        Properties props = new Properties();
//        props.setProperty("annotators", "tokenize,ssplit,pos,parse");
//        // set a property for an annotator, in this case the coref annotator is being set to use the neural algorithm
//        props.setProperty("coref.algorithm", "neural");
//
//        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        // build annotation for a review
        String TPE = "Story of  man";
        String sent = "Story of a man who has unnatural feelings for a pig.";
        String text = "<ROOT <NP <NP <NP <NNP Story>> <PP <IN of> <NP <NP <DT a> <NN man>> <SBAR <WHNP <WP who>> <S <VP <VBZ has> <NP <NP <JJ unnatural> <NNS feelings>> <PP <IN for> <NP <DT a> <NN pig>>>>>>>>>> <. .>>>";
//        Annotation annotation =
//                new Annotation(text);
        // annotate
//        pipeline.annotate(annotation);
        // get tree

        String[] arr = TPE.split(" ");
        String[] arr2 = sent.split(" ");
//        final ArrayList<String> fruits = new ArrayList<>(Arrays.asList(arr2));
//        System.out.println(fruits.size());
        for(int i = 0; i < arr2.length; i++){
            ArrayList<String> fruits = new ArrayList<>(Arrays.asList(arr2));
//            System.out.println(fruits);
            int count = 0;
            fruits.remove(arr2[i]);
            String sum_text = String.join(" ", fruits);
//            System.out.println(sum_text);
            for(String string:arr){
                if(sum_text.contains(string) == true){
                    count++;
                }
            }
            if(count >= arr.length){
                System.out.println("하나씩 제거로 augment : " + sum_text);
            }
//            System.out.println(fruits);
        }

//        fruits.remove()
//        for(int i =0 ; i < fruits.size(); i++){
//            fruits.remove(fruits.get(i));
//            System.out.println(fruits);
//        }


        Tree tree = (new PennTreeReader(new StringReader(myTest.replaced2(text)), new LabeledScoredTreeFactory(new StringLabelFactory()))).readTree();
//        System.out.println(tree.toString());
//          Tree tree =      annotation.get(CoreAnnotations.SentencesAnnotation.class).get(0).get(TreeCoreAnnotations.TreeAnnotation.class);
//        System.out.println(tree.getClass().getName());
        Set<Constituent> treeConstituents = tree.constituents(new LabeledScoredConstituentFactory());
//        System.out.println(treeConstituents);
        // 잘라낼 부분 저장하는 배열
        for (Constituent constituent : treeConstituents) {
            if (constituent.label() != null &&
                    (constituent.label().toString().equals("SBAR"))) {
//                System.out.println("found constituent: "+constituent.toString());
                List<Tree> yoso = tree.getLeaves().subList(constituent.start(), constituent.end()+1);
                String yoso_sum = StringUtils.join(yoso, " ");
                first.add(yoso_sum);
//                System.out.println(tree.getLeaves().subList(constituent.start(), constituent.end()+1));
            }
        }

        for(int i = 0; i < first.size(); i++){
            String output_sent = myTest.replaced3(sent, first.get(i));
            int count = 0;
//            System.out.println(output_sent.contains("Story"));
            for(String string: arr){
//                System.out.println(string);
                if(output_sent.contains(string) == true){
                    count++;
                }
            }
            if(count >= arr.length){
                System.out.println("phrase 제거로 augment : " + output_sent);
            }
        }



    }
}
