package ru.borisov.datageneratorgrpcmicroservice.service;

import com.google.protobuf.Empty;
import com.google.protobuf.Timestamp;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import ru.borisov.datageneratorgrpcmicroservice.model.Data;
import ru.borisov.grpccommon.DataServerGrpc;
import ru.borisov.grpccommon.GRPCData;
import ru.borisov.grpccommon.MeasurementType;

import java.time.ZoneOffset;
import java.util.List;


@Service
@RequiredArgsConstructor
public class GRPCDataServiceImpl implements GRPCDataService {

    @GrpcClient(value = "data-generator-blocking")
    private DataServerGrpc.DataServerBlockingStub blockingStub;

    @GrpcClient(value = "data-generator-async")
    private DataServerGrpc.DataServerStub asyncStub;

    @Override
    public void send(Data data) {

        GRPCData request = getGrpcData(data);
        StreamObserver<Empty> responseObserver = getResponseObserver();
        asyncStub.addData(request, responseObserver);
    }

    @Override
    public void send(List<Data> data) {

        StreamObserver<Empty> responseObserver = getResponseObserver();
        StreamObserver<GRPCData> requestObserver = asyncStub.addStreamData(responseObserver);
        for (Data d : data) {
            GRPCData request = getGrpcData(d);
            requestObserver.onNext(request);
        }
        requestObserver.onCompleted();
    }

    private static StreamObserver<Empty> getResponseObserver() {

        return new StreamObserver<>() {
            @Override
            public void onNext(Empty empty) {
            }

            @Override
            public void onError(Throwable throwable) {
            }

            @Override
            public void onCompleted() {
            }
        };
    }

    private static GRPCData getGrpcData(Data data) {

        return GRPCData.newBuilder()
                .setSensorId(data.getSensorId())
                .setTimestamp(
                        Timestamp.newBuilder()
                                .setSeconds(
                                        data.getTimestamp().toEpochSecond(ZoneOffset.UTC)
                                )
                                .build()
                )
                .setMeasurementType(MeasurementType.valueOf(data.getMeasurementType().name()))
                .setMeasurement(data.getMeasurement())
                .build();
    }

}
