package com.patientmanagementsystem.billingservice.grpc;

import billing.BillingRequest;
import billing.BillingResponse;
import billing.BillingServiceGrpc.BillingServiceImplBase;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@GrpcService
public class BillingGrpcService  extends BillingServiceImplBase {


    private static final Logger log = LoggerFactory.getLogger(BillingGrpcService.class);

    public void createBillingAccount(BillingRequest billingRequest, StreamObserver<BillingResponse> responseStreamObserver){
        log.info("createBillingAccount request received {}", billingRequest.toString());

        BillingResponse billingResponse = BillingResponse.newBuilder()
                .setAccountId("1")
                .setStatus("ACTIVE")
                .build();

        responseStreamObserver.onNext(billingResponse);
        responseStreamObserver.onCompleted();

    }

}
