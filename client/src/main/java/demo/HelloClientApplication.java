package demo;

import demo.configuration.DisableHystrixConfiguration;
import demo.feign.HttpBinClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author Spencer Gibb
 */
@SpringBootApplication
@EnableDiscoveryClient
@RestController
@EnableFeignClients
public class HelloClientApplication {
	@Autowired
	HelloClient helloClient;
	@Autowired
	HttpBinClient httpBinClient;

	@RequestMapping("/")
	public String hello() {
		// Hystrix支持
		return helloClient.hello();
	}

	@RequestMapping("/get")
	public String get() {
		// Hystrix不支持
		return httpBinClient.get();
	}

	public static void main(String[] args) {
		SpringApplication.run(HelloClientApplication.class, args);
	}

	@FeignClient(name = "HelloServer", fallback = HelloClientFallback.class)
	interface HelloClient {
		@RequestMapping(value = "/", method = GET)
		String hello();
	}

	@Component
	static class HelloClientFallback implements HelloClient {

		@Override
		public String hello() {
			return "HELLO, FALLBACK";
		}
	}
}
