package codekoenig.reqlogvalve.shared;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestDto {
    private String body;
    private Map<String, String> headers;
    private String method;
    private QueryString queryString;
    private String path;
    private long timestamp;
    private String contentType;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }


    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }


    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public QueryString getQueryString() {
        return queryString;
    }

    public void setQueryString(QueryString queryString) {
        this.queryString = queryString;
    }

    public String getFullPath() {
        if (queryString == null || queryString.toString().length() == 0) {
            return path;
        }

        return String.join("?", path, queryString.toString());
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getContentType() { return contentType; }

    public void setContentType(String contentType) { this.contentType = contentType; }

    @Override
    public String toString() {
        return "RequestDto{" +
                "body='" + body + '\'' +
                ", headers=" + headers +
                ", method='" + method + '\'' +
                ", queryString='" + queryString + '\'' +
                ", path='" + path + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
