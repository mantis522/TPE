package tpe;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Locale;

public class Split_Sentence {
    public ArrayList<String> split_sentence(String text){

        ArrayList<String> arrayList = new ArrayList<>();
        BreakIterator iterator = BreakIterator.getSentenceInstance(Locale.US);
        iterator.setText(text);
        int start = iterator.first();
        for (int end = iterator.next();
             end != BreakIterator.DONE;
             start = end, end = iterator.next()) {
//            System.out.println(text.substring(start,end));
            arrayList.add(text.substring(start,end));
        }

        return arrayList;
    }

    // 문장 들어오면 문장 나눠서 리스트에 넣는 메소드

    public static void main(String[] args) {
        Split_Sentence split = new Split_Sentence();
        String source = "How many miles are there to the moon? How many times do I have to tell you? How many times have any of us been involved in an event remotely exciting?";
        ArrayList<String> test = split.split_sentence(source);
        System.out.println(test);
    }
}
