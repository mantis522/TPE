package tpe;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class test {

    public static void main(String[] args) {

        // ��� ���ڿ�
        String content = "Java is a general-purpose computer programming language. java is ...";
        System.out.println(content);

        // ���Խ� ���ڿ��� ������
        Pattern pattern = Pattern.compile("Java", Pattern.CASE_INSENSITIVE); // ��ҹ��� ���� ����
        // �˻� ����� Matcher�� ����
        Matcher matcher = pattern.matcher(content);

        // ó���� ã�� ���ڿ��� ġȯ
        System.out.println("\n==== ó���� ã�� ���ڿ��� ġȯ");
        String result1 = matcher.replaceFirst("�ڹ�");
        System.out.println(result1);

        // �̵��� matcher�� �������� ����
        matcher.reset();

        // ã�� ���ڿ� ��� ġȯ
        System.out.println("\n==== ã�� ���ڿ� ��� ġȯ");
        String result2 = matcher.replaceAll("�ڹ�");
        System.out.println(result2);

        // �̵��� matcher�� �������� ����
        matcher.reset();

        // ã�� ������ ó���� �ǽ�
        System.out.println("\n==== ã�� ������ ó���� �ǽ�");
        StringBuffer replacedString = new StringBuffer();
        while (matcher.find()) {
            // ã�� ����� ġȯ
            System.out.println(matcher.group() + " => �ڹ� �� ġȯ ����");
            matcher.appendReplacement(replacedString, "�ڹ�");
        }
        // �˻��� ���������� ã�� �κ� ������ �˻� ��� ���ڿ��� ����
        matcher.appendTail(replacedString);

        String result3 = replacedString.toString();
        System.out.println(result3);

    }
}
