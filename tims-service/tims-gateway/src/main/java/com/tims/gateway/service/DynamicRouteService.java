package com.tims.gateway.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


import java.util.List;

@Component
@Slf4j
public class DynamicRouteService {

    private final RouteDefinitionWriter routeDefinitionWriter;
    private final ApplicationEventPublisher publisher;
    private final ObjectMapper objectMapper;

    public DynamicRouteService(RouteDefinitionWriter routeDefinitionWriter,
                               ApplicationEventPublisher publisher, ObjectMapper objectMapper) {
        this.routeDefinitionWriter = routeDefinitionWriter;
        this.publisher = publisher;
        this.objectMapper = objectMapper;
    }

    public void updateRoutes(String json) {
        try {
            List<RouteDefinition> routes =
                    objectMapper.readValue(json, new TypeReference<>() {});
            log.info("更新 Gateway 路由: {}", routes);
            Flux.fromIterable(routes)
                    .flatMap(route -> routeDefinitionWriter.delete(Mono.just(route.getId()))
                            .onErrorResume(e -> {
                                if (e instanceof org.springframework.cloud.gateway.support.NotFoundException) {
                                    return Mono.empty();
                                }
                                return Mono.error(e);
                            })
                    )
                    .thenMany(Flux.fromIterable(routes)
                            .flatMap(route -> routeDefinitionWriter.save(Mono.just(route)))
                    )
                    .then(Mono.fromRunnable(() -> publisher.publishEvent(new RefreshRoutesEvent(this))))
                    .subscribe();

            log.info("✅ Gateway 路由已更新");
        } catch (Exception e) {
            log.error("❌ 更新 Gateway 路由失败: ", e);
        }
    }
}
