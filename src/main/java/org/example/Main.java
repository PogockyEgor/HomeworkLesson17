package org.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws RuntimeException {
        System.out.println(isValidate(new Scanner(System.in).nextLine()));
        try {
            FileInputStream fileInputStream = new FileInputStream("C:\\Users\\user\\IdeaProjects\\HomeworkLesson18\\src\\fileWithText.txt");
            StringBuilder text = new StringBuilder();
            int i = -1;
            while ((i = fileInputStream.read()) != -1) {
                text.append((char) i);
            }
            fileInputStream.close();
            Pattern documentNumber = Pattern.compile("\\d{1,4}-\\d{1,4}-\\d{1,2}");
            Pattern telephoneNumber = Pattern.compile("[+][(]\\d{2}[)]\\d{7}");
            Pattern email = Pattern.compile("\\S+@\\S+\\.\\S+\\b");
            LinkedHashMap<String, String> data = new LinkedHashMap<>();
            Matcher documentNumberMatcher = documentNumber.matcher(text);
            Matcher telephoneNumberMatcher = telephoneNumber.matcher(text);
            Matcher emailMatcher = email.matcher(text);
            while (documentNumberMatcher.find()) {
                data.put("documentNumber" + documentNumberMatcher.start(), documentNumberMatcher.group());
            }
            while (telephoneNumberMatcher.find()) {
                data.put("telephoneNumber" + telephoneNumberMatcher.start(), telephoneNumberMatcher.group());
            }
            while (emailMatcher.find()) {
                data.put("email" + emailMatcher.start(), emailMatcher.group());
            }
            System.out.println(data);
        } catch (IOException e) {
            System.out.println("Указанный файл не найден");
        }
    }

    public static boolean isValidate(String ip) {
        return ip.matches("\\d{1,3}.\\d{1,3}.\\d{1,3}.\\d{1,3}");
    }
}