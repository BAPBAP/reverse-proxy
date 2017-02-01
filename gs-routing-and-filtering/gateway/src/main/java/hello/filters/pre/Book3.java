package hello.filters.pre;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.cloud.netflix.feign.FeignClient;

import feign.jaxrs.JAXRSContract;

@FeignClient(name="book1", url="http://localhost:8090", configuration=JAXRSContract.class)
public interface Book3 {

 // @RequestMapping(value = "/available", method = RequestMethod.GET, produces = "text/html")
  @GET
  @Path("/available")
  @Produces("text/html")
  String available();

}