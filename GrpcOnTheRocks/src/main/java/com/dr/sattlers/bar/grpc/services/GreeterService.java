package com.dr.sattlers.bar.grpc.services;

import com.dr.sattlers.bar.grpc.HelloReply;
import com.dr.sattlers.bar.grpc.HelloRequest;
import com.dr.sattlers.bar.grpc.GreeterGrpc.GreeterImplBase;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Service;

@Service
public class GreeterService extends GreeterImplBase {

    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
        String name = request.getName();
        String replyMessage = "Hello, " + name + "!";

        HelloReply reply = HelloReply.newBuilder()
                .setReply(replyMessage)
                .build();

        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
