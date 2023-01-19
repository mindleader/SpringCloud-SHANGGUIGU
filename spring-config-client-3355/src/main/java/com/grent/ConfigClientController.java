package com.grent;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.endpoint.web.annotation.RestControllerEndpoint;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

@RestController
@RefreshScope
public class ConfigClientController {

    @Value("${config.info}")
    String configInfo;

    @GetMapping("/getConfigInfo")
    public String getConfigInfo(){
        return configInfo;
    }
}
