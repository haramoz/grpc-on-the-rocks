package com.dr.sattlers.bar.grpc.client;

import com.dr.sattlers.bar.grpc.GreeterGrpc;
import com.dr.sattlers.bar.grpc.GreeterGrpc.GreeterBlockingStub;
import com.dr.sattlers.bar.grpc.HelloReply;
import com.dr.sattlers.bar.grpc.HelloRequest;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GreeterClient {

    private final GreeterBlockingStub blockingStub;

    public GreeterClient(String host, int port) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();

        blockingStub = GreeterGrpc.newBlockingStub(channel);
    }

    public String sayHello(String name) {
        HelloRequest request = HelloRequest.newBuilder()
                .setName(name)
                .build();

        HelloReply response = blockingStub.sayHello(request);
        return response.getReply();
    }

    public static void main(String[] args) {
        GreeterClient client = new GreeterClient("localhost", 9090);
        String name = "John Doe";
        String response = client.sayHello(name);
        System.out.println("Response from server: " + response);
    }
}
