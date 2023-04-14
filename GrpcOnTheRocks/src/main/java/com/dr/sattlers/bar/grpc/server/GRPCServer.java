package com.dr.sattlers.bar.grpc.server;

import com.dr.sattlers.bar.grpc.services.GreeterService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.protobuf.services.ProtoReflectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;

@Component
public class GRPCServer {

    @Autowired
    private GreeterService greeterService;

    private Server server;

    private final int port = 9090;

    @PostConstruct
    public void start() throws IOException {
        server = ServerBuilder.forPort(port)
                .addService(greeterService)
                .addService(ProtoReflectionService.newInstance()) // Add reflection service
                .build()
                .start();

        System.out.println("Server started on port " + port);
    }

    @PreDestroy
    public void stop() {
        if (server != null) {
            server.shutdown();
        }
    }
}
