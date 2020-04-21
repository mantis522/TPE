package tpe;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeCoreAnnotations;

import java.util.Properties;

public class CoreNLP_Parser{
    public static void main(String[] args) {
        // set up pipeline properties
        Properties props = new Properties();
        // set the list of annotators to run
        props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner,parse");
        // set a property for an annotator, in this case the coref annotator is being set to use the neural algorithm
        props.setProperty("coref.algorithm", "neural");
        // build pipeline
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        // build annotation for a review
        Annotation annotation =
                new Annotation("the wonderful scene with the physicists playing badmitton");
        // annotate
        pipeline.annotate(annotation);
        // get tree
        Tree tree =
                annotation.get(CoreAnnotations.SentencesAnnotation.class).get(0).get(TreeCoreAnnotations.TreeAnnotation.class);
//        Pattern pattern = Pattern.compile("(");
        System.out.println(tree);
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