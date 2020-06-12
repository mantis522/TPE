package tpe;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeCoreAnnotations;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CoreNLP_Parser{

    public String normal_pattern(String text){
        Properties props = new Properties();
        CoreNLP_Parser regex = new CoreNLP_Parser();
        props.setProperty("annotators", "tokenize,ssplit,pos,parse");
        // set a property for an annotator, in this case the coref annotator is being set to use the neural algorithm
        props.setProperty("coref.algorithm", "neural");

        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        // build annotation for a review
        Annotation annotation =
                new Annotation(text);
        // annotate
        pipeline.annotate(annotation);
        // get tree
        Tree tree =
                annotation.get(CoreAnnotations.SentencesAnnotation.class).get(0).get(TreeCoreAnnotations.TreeAnnotation.class);

        return tree.toString();
    }

    public String replaced(String content){
        Pattern pattern = Pattern.compile("\\(");
        Pattern pattern2 = Pattern.compile("\\)");

        Matcher matcher = pattern.matcher(content);
        String result = matcher.replaceAll("\\<");
        Matcher matcher2 = pattern2.matcher(result);
        String result2 = matcher2.replaceAll("\\>");

        return result2;
    }


    public String Core_parser(String text){
        Properties props = new Properties();
        CoreNLP_Parser regex = new CoreNLP_Parser();
        props.setProperty("annotators", "tokenize,ssplit,pos,parse");
        // set a property for an annotator, in this case the coref annotator is being set to use the neural algorithm
        props.setProperty("coref.algorithm", "neural");

        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        // build annotation for a review
        Annotation annotation =
                new Annotation(text);
        // annotate
        pipeline.annotate(annotation);
        // get tree
        Tree tree =
                annotation.get(CoreAnnotations.SentencesAnnotation.class).get(0).get(TreeCoreAnnotations.TreeAnnotation.class);

        String regex_tree = regex.replaced(tree.toString());

        return regex_tree;
    }

    // String이 공백일 경우는 true 리턴
    public static boolean isBlank(String str) {
        int strLen; if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((!Character.isWhitespace(str.charAt(i)))) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        CoreNLP_Parser parser = new CoreNLP_Parser();
//        System.out.println(parser.isBlank("  "));
        System.out.println(parser.Core_parser("It was boring, overdramatic, and the funny parts were too far in between to make up the slack."));
//        ArrayList<String> list = new ArrayList<>();
//        ArrayList<String> test = new ArrayList<>();
//        list.add("       ");
//        list.add("World");
//
//        for(int i = 0; i < list.size(); i++){
//            if(parser.isBlank(list.get(i)) == false){
//                test.add(parser.Core_parser(list.get(i)));
//            }
//            else{
//                test.add("space");
//            }
//        }
//
//        System.out.println(test);

//        try{
//            for(int i = 0; i < list.size(); i++) {
//                System.out.println(list.get(i));
//                System.out.println(parser.Core_parser(list.get(i)));
//            }
//        }
//        catch(IndexOutOfBoundsException e){
//            System.out.println(e);
//        }
//
//        System.out.println(parser.Core_parser("   ").getClass().getName());
//        System.out.println();
//        JSON_parser parse = new JSON_parser();
//        ArrayList<String> first = parse.json_parsing();
//        Split_Sentence split = new Split_Sentence();
//        ArrayList<String> test = split.split_sentence(first.get(1));
////        System.out.println(test);
////        System.out.println(test.get(5));
////        System.out.println(parser.normal_pattern(test.get(5)));
//        String parsed_text = parser.Core_parser(test.get(5));
//        System.out.println(parsed_text);
    }
}