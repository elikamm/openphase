import java.util.*;

public class DictionaryParser {
    public static String encode(String[] values) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < values.length; ++i) {
            if (i > 0) builder.append(',');

            String value = values[i];
            if (value.contains(",") || value.contains("\"")) {
                value = value.replace("\"", "\"\"");
                builder.append('"').append(value).append('"');
            } else {
                builder.append(value);
            }
        }

        return builder.toString();
    }

    public static String[] decode(String data) {
        List<String> result = new ArrayList<>();
        StringBuilder buffer = new StringBuilder();
        boolean escaped = false;
        
        for (int i = 0; i < data.length(); ++i) {
            char c = data.charAt(i);
            
            if (escaped) {
                if (c == '"') {
                    if (i + 1 < data.length() && data.charAt(i + 1) == '"') {
                        buffer.append('"');
                        ++i;
                    } else {
                        escaped = false;
                    }
                } else {
                    buffer.append(c);
                }
            } else {
                if (c == '"') {
                    escaped = true;
                } else if (c == ',') {
                    result.add(buffer.toString());
                    buffer.setLength(0);
                } else {
                    buffer.append(c);
                }
            }
        }
        
        result.add(buffer.toString());

        return result.toArray(new String[0]);
    }
}
