package SentiWordNet;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class POSExample {

    public static void main(String[] args) throws IOException {

        StanfordCoreNLP stanfordCoreNLP = Pipeline.getPipeline();
        SentiScore sc = new SentiScore();

        String text = "In an interview, Johnny Duncan Robin said that in one scene he and Batman had to run from the car to the house and that Lowery was doubled over out of camera range because his girdle was too tight!";

        String[] words = text.split(" ");

        Double totScore = 0.0;

        for(String w:words){
            if(sc.extract(w) == null)
                continue;
            totScore += sc.extract(w);
        }

        String out = "";
        if(totScore>=0.75)
            out = "strong_positive";
        else
        if(totScore > 0.25 && totScore>=0.5)
            out = "positive";
        else
        if(totScore > 0 && totScore>=0.25)
            out = "weak_positive";
        else
        if(totScore < 0 && totScore>=-0.25)
            out = "weak_negative";
        else
        if(totScore < -0.25 && totScore>=-0.5)
            out = "negative";
        else
        if(totScore<=-0.75)
            out = "strong_negative";
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
        System.out.println(totScore);



    }
}