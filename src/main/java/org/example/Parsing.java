//2) Дана json-строка (можно сохранить в файл и читать из файла)
//[{"фамилия":"Иванов","оценка":"5","предмет":"Математика"},{"фамилия":"Петрова","оценка":"4","предмет":"Информатика"},{"фамилия":"Краснов","оценка":"5","предмет":"Физика"}]
//Написать метод(ы), который распарсит json и, используя StringBuilder, создаст строки вида: Студент [фамилия] получил [оценка] по предмету [предмет].
//Пример вывода:
//Студент Иванов получил 5 по предмету Математика.
//Студент Петрова получил 4 по предмету Информатика.
//Студент Краснов получил 5 по предмету Физика.

package org.example;

import java.util.Arrays;
import java.util.List;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Parsing {

    public static void main(String[] args) {
        try {
            String inputFilePath = "src/main/resources/data.json";
            File file = new File(inputFilePath);

            if (!file.exists() || !file.canRead()) {
                System.err.println("Файл не найден!");
                return;
            }

            FileReader reader = new FileReader(file);
            char[] buffer = new char[(int) file.length()];
            reader.read(buffer);
            String json = new String(buffer);
            parsePrint(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void parsePrint(String json) {
        if (json.isEmpty()) {
            System.err.println("JSON строка пуста.");
            return;
        }

        StringBuilder stringBuilder = new StringBuilder();

        List<String> jsonObjects = Arrays.asList(json.substring(1, json.length() - 1).split("\\},"));

        for (String jsonObjectString : jsonObjects) {
            String[] properties = jsonObjectString.replaceAll("\\{", "").replaceAll("}", "").split(",");

            String surname = "";
            String mark = "";
            String subject = "";

            for (String property : properties) {
                String[] keyValue = property.split(":");
                String key = keyValue[0].trim();
                String value = keyValue[1].trim();

                if (key.equals("\"фамилия\"")) {
                    surname = value.replaceAll("\"", "");
                } else if (key.equals("\"оценка\"")) {
                    mark = value.replaceAll("\"", "");
                } else if (key.equals("\"предмет\"")) {
                    subject = value.replaceAll("\"", "");
                }
            }

            stringBuilder.append("Студент ")
                    .append(surname)
                    .append(" получил ")
                    .append(mark)
                    .append(" по предмету ")
                    .append(subject)
                    .append(".")
                    .append("\n");
        }

        System.out.println(stringBuilder.toString());
    }
}