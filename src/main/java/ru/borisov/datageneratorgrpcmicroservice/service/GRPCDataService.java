package ru.borisov.datageneratorgrpcmicroservice.service;

import ru.borisov.datageneratorgrpcmicroservice.model.Data;

import java.util.List;

public interface GRPCDataService {

    void send(Data data);

    void send(List<Data> data);
}
