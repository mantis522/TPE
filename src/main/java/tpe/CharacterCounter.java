package tpe;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CharacterCounter {
    public static void main(String[] args){
        ArrayList<String> arrayList = new ArrayList<String>();

        Pattern p = Pattern.compile("VP|NP|PP|ADJP|ADVP|WHADVP");
        Matcher m = p.matcher("<S <NP * <NN.*|PR.* .+>> <VP <VB.* .+> <VP <VB.* .+> *> *> *>");
        int count = 0;
        boolean a = false;
        while(a = m.find()){
            count++;
        }



        System.out.println(count);
    }
}
