package codekoenig.reqlogvalve.shared;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.StringJoiner;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryString extends LinkedHashMap<String, String> {

    public static final String UTF_8 = "UTF-8";

    public QueryString() {}

    public QueryString(String encodedQueryString) throws UnsupportedEncodingException {
        if (encodedQueryString == null || encodedQueryString.trim().length() == 0) {
            throw new IllegalArgumentException("encodedQueryString cannot be NULL or empty");
        }

        putFromQueryString(encodedQueryString);
    }

    private void putFromQueryString(String encodedQueryString) throws UnsupportedEncodingException {
        final String normalizedQueryString;

        if (encodedQueryString.startsWith("?")) {
            normalizedQueryString = encodedQueryString.substring(1);
        }
        else {
            normalizedQueryString = encodedQueryString;
        }

        final String[] params = normalizedQueryString.split("&");

        for (String param : params) {

            final String[] keyValuePair = param.split("=");

            // TODO: Handling when no value is present
            final String key = URLDecoder.decode(keyValuePair[0], UTF_8);
            final String value;

            if (keyValuePair.length == 2) {
                value = URLDecoder.decode(keyValuePair[1], UTF_8);
            }
            else {
                value = "";
            }

            this.put(key, value);
        }
    }

    @Override
    public String toString() {
        final StringJoiner queryStringJoiner = new StringJoiner("&");

        super.forEach((key, value) -> {
            String encodedKey;
            String encodedValue;

            try {
                encodedKey = URLEncoder.encode(key, UTF_8);
                encodedValue = URLEncoder.encode(value, UTF_8);
            } catch (UnsupportedEncodingException e) {
                // Fallback
                encodedKey = key;
                encodedValue = value;
            }

            queryStringJoiner.add(String.format("%s=%s", encodedKey, encodedValue));
        });

        return queryStringJoiner.toString();
    }
}
