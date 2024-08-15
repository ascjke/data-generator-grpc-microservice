package ru.borisov.datageneratorgrpcmicroservice.service;

import ru.borisov.datageneratorgrpcmicroservice.model.test.DataTestOptions;

public interface TestDataService {

    void sendMessages(DataTestOptions testOptions);

}
