package codekoenig.reqlogvalve.demowebapp;

import codekoenig.reqlogvalve.shared.DemoWebAppApi;
import codekoenig.reqlogvalve.shared.RequestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;

public class DemoWebAppRestService implements DemoWebAppApi {
    private static final Logger log = LoggerFactory.getLogger(DemoWebAppRestService.class);

    DemoWebAppRestService() {
    }

    @Override
    public String testPost(String body) {
        return body;
    }
}
