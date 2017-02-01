package hello.filters.pre;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.feign.support.SpringMvcContract;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//@FeignClient(name="book2", configuration=SpringMvcContract.class)
public interface Book2 {

  //@RequestMapping(value = "http://localhost:8090/checked-out", method = RequestMethod.GET)
  String checkedOut();

}