package spring.core.basic.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MyLogger {

    private String uuid;
    private String requestURL;

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.printf("[%s] [%s] %s", uuid, requestURL, message);
        System.out.println();
    }

    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
        System.out.printf("[%s] A request scope bean created: %s", uuid, this);
        System.out.println();
    }

    @PreDestroy
    public void close() {
        System.out.printf("[%s] A request scope bean closed: %s", uuid, this);
        System.out.println();
    }
}
