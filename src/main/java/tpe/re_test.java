package tpe;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class re_test {

    public String replaced(String content){
        Pattern pattern = Pattern.compile("\\(");
        Pattern pattern2 = Pattern.compile("\\)");

        Matcher matcher = pattern.matcher(content);
        String result = matcher.replaceAll("\\<");
        Matcher matcher2 = pattern2.matcher(result);
        String result2 = matcher2.replaceAll("\\>");

        return result2;
    }

    public String replaced2(String content){
        Pattern pattern = Pattern.compile("<");
        Pattern pattern2 = Pattern.compile(">");

        Matcher matcher = pattern.matcher(content);
        String result = matcher.replaceAll("\\(");
        Matcher matcher2 = pattern2.matcher(result);
        String result2 = matcher2.replaceAll("\\)");

        return result2;
    }


    public static void main(String[] args) {
        String content = "<ROOT <S <ADVP <RB Unfortunately>> <NP <PRP it>> <VP <VBZ stays> <NP <JJ absurd> <DT the> <JJ WHOLE> <NN time>> <PP <IN with> <NP <DT no> <JJ general> <NN narrative>>> <S <ADVP <RB eventually>> <VP <VBG making> <NP <PRP it>> <ADVP <RB just>> <ADVP <RB too> <JJ off>> <S <VP <VBG putting>>>>>> <. .>>>";
        re_test myTest = new re_test();
        String test = myTest.replaced2(content);
        System.out.println(test);

    }
}
