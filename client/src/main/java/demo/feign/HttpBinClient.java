package demo.feign;

import demo.configuration.DisableHystrixConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@FeignClient(name = "HttpBin", url = "https://httpbin.org", configuration = DisableHystrixConfiguration.class)
public interface HttpBinClient {

    @RequestMapping(value = "/get", method = GET)
    String get();
}
