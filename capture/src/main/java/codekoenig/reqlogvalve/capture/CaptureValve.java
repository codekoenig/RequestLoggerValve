package codekoenig.reqlogvalve.capture;

import codekoenig.reqlogvalve.shared.DemoWebAppApi;
import codekoenig.reqlogvalve.shared.QueryString;
import codekoenig.reqlogvalve.shared.RequestDto;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.RequestWrapper;
import org.apache.catalina.connector.Response;
import org.apache.catalina.valves.ValveBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.*;

public class CaptureValve extends ValveBase {
    private static final Logger log = LoggerFactory.getLogger(CaptureValve.class);

    @Override
    public void invoke(Request request, Response response) throws IOException, ServletException {
        log.info("RequestLoggerValve invoked");
        // Wrap request so the body can be read multiple times
        RequestWrapper wrappedRequest = new RequestWrapper(request);

        try {
            // important - otherwise, requests aren't passed further down the chain...
            getNext().invoke(wrappedRequest, response);
        }
        catch (Exception e) {
            log.error("Error passing down request in pipeline", e);
        }

        // Read request including body again into Dto
        try {
            final RequestDto requestDto = getRequestDataFromRequest(wrappedRequest);
            log.info("Parsed body: " + requestDto.getBody());
            log.trace("Request:\n{}", requestDto);
        }
        catch (Exception e) {
            log.error("Error: ", e);
        }
    }

    private static RequestDto getRequestDataFromRequest(RequestWrapper request) throws IOException {
        final RequestDto requestDto = new RequestDto();

        requestDto.setBody(request.getBody());
        requestDto.setHeaders(getHeadersFromRequest(request));
        requestDto.setMethod(request.getMethod());
        requestDto.setPath(getPathFromRequest(request));
        requestDto.setQueryString(request.getQueryString() != null
                ? new QueryString(request.getQueryString())
                : new QueryString());
        requestDto.setContentType(request.getContentType());

        return requestDto;
    }

    private static Map<String, String> getHeadersFromRequest(Request request) {
        final Map<String, String> headers = new HashMap<>();

        final List<String> headerNames = Collections.list(request.getHeaderNames());

        for (String headerName : headerNames) {
            final StringJoiner joinedHeaderValues = new StringJoiner(",");
            final List<String> headerValues = Collections.list(request.getHeaders(headerName));
            headerValues.forEach(joinedHeaderValues::add);
            headers.put(headerName, joinedHeaderValues.toString());
        }

        for (Map.Entry<String, String> header : headers.entrySet()) {
            log.trace("{} {}", header.getKey(), header.getValue());
        }

        return headers;
    }

    private static String getPathFromRequest(Request request) {
        if (request.getServletPath() == null && request.getPathInfo() == null) {
            return null;
        }

        return n2e(request.getServletPath()) + n2e(request.getPathInfo());
    }

    private static String n2e(String nullableString) {
        return nullableString == null ? "" : nullableString;
    }
}
