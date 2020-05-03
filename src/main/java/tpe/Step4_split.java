package tpe;

import java.util.ArrayList;
import java.util.List;

public class Step4_split {
    public static void main(String[] args)
    {
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("it");
        arrayList.add("is");
        arrayList.add("written");
        arrayList.add("He");
        arrayList.add("is");
        arrayList.add("given");

        int size = 3;
        for (int start = 0; start < arrayList.size(); start += size) {
            int end = Math.min(start + size, arrayList.size());
            List<String> sublist = arrayList.subList(start, end);
            String sum_text = String.join(" ",sublist);
            System.out.println(sum_text);
        }
    }
}
