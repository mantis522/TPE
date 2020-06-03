package test;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.util.ArrayList;
import java.util.List;

public class POSExample {

    public static void main(String[] args) {

        StanfordCoreNLP stanfordCoreNLP = Pipeline.getPipeline();

        String text = "Titanic is 21st Century";

        CoreDocument coreDocument = new CoreDocument(text);

        stanfordCoreNLP.annotate(coreDocument);

        List<CoreLabel> coreLabelList = coreDocument.tokens();
        ArrayList<String> sent = new ArrayList<>();

        for(CoreLabel coreLabel : coreLabelList) {
            String pos = coreLabel.get(CoreAnnotations.PartOfSpeechAnnotation.class);
//            System.out.println(coreLabel.originalText() + " = "+ pos);
            if(pos.equals("JJ")){
                sent.add("");
            }
            else{
                sent.add(coreLabel.originalText());
            }
//            sent.add(coreLabel.originalText());
        }
        String sum_text = String.join(" ", sent);
        System.out.println(sum_text);



    }
}