package com.tims.gateway.listener;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.tims.gateway.service.DynamicRouteService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;

@Component
@Slf4j
public class GatewayRouterNacosListener {
    private final NacosConfigManager nacosConfigManager;
    private final DynamicRouteService dynamicRouteService;

    public GatewayRouterNacosListener(NacosConfigManager nacosConfigManager,
                                      DynamicRouteService dynamicRouteService) {
        this.nacosConfigManager = nacosConfigManager;
        this.dynamicRouteService = dynamicRouteService;
    }

    @PostConstruct
    public void initListener() throws NacosException {
        ConfigService configService = nacosConfigManager.getConfigService();
        String dataId = "tims-gateway-router.json";
        String group = "DEFAULT_GROUP";

        // 启动时加载一次
        String content = configService.getConfig(dataId, group, 5000);
        dynamicRouteService.updateRoutes(content);

        // 注册监听器
        configService.addListener(
            dataId,
            group,
            new Listener() {
                @Override
                public Executor getExecutor() {
                    return null;
                }

                @Override
                public void receiveConfigInfo(String configInfo) {
                    log.info("Nacos 配置更新：{}", configInfo);
                    dynamicRouteService.updateRoutes(configInfo);
                }
            }
        );
    }
}
