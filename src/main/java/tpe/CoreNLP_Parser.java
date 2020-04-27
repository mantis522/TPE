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
        props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner,parse");
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


    public static void main(String[] args) {
        CoreNLP_Parser parser = new CoreNLP_Parser();
        String parsed_text = parser.Core_parser("she is so pretty all over the world.");

        System.out.println(parsed_text);
    }
}