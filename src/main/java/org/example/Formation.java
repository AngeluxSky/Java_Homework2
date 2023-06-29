//1) Дана строка sql-запроса "select * from students where ". Сформируйте часть WHERE этого запроса, используя StringBuilder или String. Данные для фильтрации приведены ниже в виде json-строки.
//Если значение null, то параметр не должен попадать в запрос.
//Параметры для фильтрации: {"name":"Ivanov", "country":"Russia", "city":"Moscow", "age":"null"}

package org.example;
import java.util.HashMap;
import java.util.Map;

public class Formation {
    public static void main(String[] args) {
        Map<String, String> parameter = new HashMap<String, String>();
        parameter.put("name", "Ivanov");
        parameter.put("country", "Russia");
        parameter.put("city", "Moscow");
        parameter.put("age", null);
        System.out.println(getQuery(parameter));
    }

    public static String getQuery(Map<String, String> params) {
        StringBuilder queryBuilder = new StringBuilder("select * from students where ");
        boolean FirstParameter = true;

        for (Map.Entry<String, String> entry : params.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            if (value != null) {
                if (!FirstParameter) {
                    queryBuilder.append(" and ");
                }
                queryBuilder.append(key).append(" = '").append(value).append("'");

                FirstParameter = false;
            }
        }

        return queryBuilder.toString();
    }
}