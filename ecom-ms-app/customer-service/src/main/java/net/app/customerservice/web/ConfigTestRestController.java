package net.app.customerservice.web;

import net.app.customerservice.config.CustomerParams;
import net.app.customerservice.config.GlobalParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/config")
@RefreshScope
public class ConfigTestRestController {

    private final GlobalParams globalParams;
    private final CustomerParams customerParams;

    @Value("${global.params.p1}")
    private int p1;

    @Value("${global.params.p2}")
    private int p2;

    public ConfigTestRestController(GlobalParams globalParams, CustomerParams customerParams) {
        this.globalParams = globalParams;
        this.customerParams = customerParams;
    }

    @GetMapping("/test1")
    public Map<String, Integer> testWithValueInjection() {
        return Map.of("p1", p1, "p2", p2);
    }

    @GetMapping("/test2")
    public Map<String, Object> testWithConfigurationProperties() {
        return Map.of(
                "globalParams", globalParams,
                "customerParams", customerParams
        );
    }
}
