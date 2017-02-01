package hello.filters.pre;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.feign.support.SpringMvcContract;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import feign.jaxrs.JAXRSContract;

//@FeignClient(name="book1", url="http://localhost:8090", configuration=SpringMvcContract.class)
public interface Book1 {

  //@RequestMapping(value = "/available", method = RequestMethod.GET, produces = "text/html")
  String available();

}