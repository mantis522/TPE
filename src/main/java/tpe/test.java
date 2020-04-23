package tpe;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class test {

    public static void main(String[] args) {

        // 대상 문자열
        String content = "Java is a general-purpose computer programming language. java is ...";
        System.out.println(content);

        // 정규식 문자열을 컴파일
        Pattern pattern = Pattern.compile("Java", Pattern.CASE_INSENSITIVE); // 대소문자 구분 안함
        // 검색 결과를 Matcher에 저장
        Matcher matcher = pattern.matcher(content);

        // 처음에 찾은 문자열만 치환
        System.out.println("\n==== 처음에 찾은 문자열만 치환");
        String result1 = matcher.replaceFirst("자바");
        System.out.println(result1);

        // 이동한 matcher의 시퀀스를 리셋
        matcher.reset();

        // 찾은 문자열 모두 치환
        System.out.println("\n==== 찾은 문자열 모두 치환");
        String result2 = matcher.replaceAll("자바");
        System.out.println(result2);

        // 이동한 matcher의 시퀀스를 리셋
        matcher.reset();

        // 찾을 때마다 처리를 실시
        System.out.println("\n==== 찾을 때마다 처리를 실시");
        StringBuffer replacedString = new StringBuffer();
        while (matcher.find()) {
            // 찾은 대상을 치환
            System.out.println(matcher.group() + " => 자바 로 치환 실행");
            matcher.appendReplacement(replacedString, "자바");
        }
        // 검색에 마지막으로 찾은 부분 이후의 검색 대상 문자열을 결합
        matcher.appendTail(replacedString);

        String result3 = replacedString.toString();
        System.out.println(result3);

    }
}
