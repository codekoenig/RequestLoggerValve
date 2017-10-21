package codekoenig.reqlogvalve.shared;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/demowebappapi")
public interface DemoWebAppApi {
    @Path("/testpost")
    @POST
    String testPost(String body);
}
