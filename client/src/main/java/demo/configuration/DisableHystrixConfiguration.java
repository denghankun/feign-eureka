package demo.configuration;

import feign.Feign;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * 此处可以忽略@Configuration, feign框架支持直接使用配置类
 */
public class DisableHystrixConfiguration {
    /**
     * 重新定义Feign.Builder可以屏蔽支持Hystrix
     */
    @Bean
    @Scope("prototype")
    public Feign.Builder feignBuilder() {
        return Feign.builder();
    }
}
