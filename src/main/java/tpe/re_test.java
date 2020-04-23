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


    public static void main(String[] args) {
        String content = "(S (V (VV) ) )";
        re_test myTest = new re_test();
        String test = myTest.replaced(content);
        System.out.println(test);

    }
}
