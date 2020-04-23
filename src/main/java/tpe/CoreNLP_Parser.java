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



    public static void main(String[] args) {
        // set up pipeline properties
        Properties props = new Properties();
        CoreNLP_Parser regex = new CoreNLP_Parser();
        // set the list of annotators to run
        props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner,parse");
        // set a property for an annotator, in this case the coref annotator is being set to use the neural algorithm
        props.setProperty("coref.algorithm", "neural");
        // build pipeline
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        // build annotation for a review
        Annotation annotation =
                new Annotation("she is so pretty all over the world.");
        // annotate
        pipeline.annotate(annotation);
        // get tree
        Tree tree =
                annotation.get(CoreAnnotations.SentencesAnnotation.class).get(0).get(TreeCoreAnnotations.TreeAnnotation.class);

        String regex_tree = regex.replaced(tree.toString());
        System.out.println(regex_tree);

//        Set<edu.stanford.nlp.trees.Constituent> treeConstituents = tree.constituents(new LabeledScoredConstituentFactory());
//        for (edu.stanford.nlp.trees.Constituent constituent : treeConstituents) {
//            if (constituent.label() != null &&
//                    (constituent.label().toString().equals("VP") || constituent.label().toString().equals("NP"))) {
//                System.err.println("found constituent: " + constituent.toString());
//                System.err.println(tree.getLeaves().subList(constituent.start(), constituent.end() + 1));
//
//            }
//        }
    }
}