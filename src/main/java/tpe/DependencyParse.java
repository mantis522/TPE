package tpe;

import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.semgraph.SemanticGraph;

import java.util.Properties;

public class DependencyParse {
    public static void main(String[] args) {
        // set up pipeline properties
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,ssplit,pos,depparse");
        // set up pipeline
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        // the following has examples for the new Core Wrapper API and the older Annotation API
        // example using Core Wrappers (new API designed to make it easier to work with NLP data)
        String sent = "It is hard to like this movie.";
        CoreDocument exampleDocument = new CoreDocument(sent);
        // annotate document
        pipeline.annotate(exampleDocument);
        // access tokens from a CoreDocument
        // a token is represented by a CoreLabel
//        List<CoreLabel> firstSentenceTokens = exampleDocument.sentences().get(0).tokens();
        CoreSentence sentence = exampleDocument.sentences().get(0);

        // this for loop will print out all of the tokens and the character offset info
        SemanticGraph dependencyParse = sentence.dependencyParse();
        System.out.println(dependencyParse);
        System.out.println(dependencyParse.edgeListSorted());
    }
}
