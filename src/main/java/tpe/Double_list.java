package tpe;

import java.util.ArrayList;

public class Double_list {
    public static void main(String[] args) {
        ArrayList<String> first = new ArrayList<>();
        ArrayList<ArrayList<String>> second = new ArrayList<>();

        first.add("first");
        first.add("second");
        first.add("third");

        second.add(first);

        System.out.println(second.get(0));
    }
}
