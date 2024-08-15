package ru.borisov.datageneratorgrpcmicroservice.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.borisov.datageneratorgrpcmicroservice.model.Data;
import ru.borisov.datageneratorgrpcmicroservice.model.test.DataTestOptions;
import ru.borisov.datageneratorgrpcmicroservice.service.GRPCDataService;
import ru.borisov.datageneratorgrpcmicroservice.service.TestDataService;
import ru.borisov.datageneratorgrpcmicroservice.web.dto.DataDto;
import ru.borisov.datageneratorgrpcmicroservice.web.dto.DateTestOptionsDto;
import ru.borisov.datageneratorgrpcmicroservice.web.mapper.DataMapper;
import ru.borisov.datageneratorgrpcmicroservice.web.mapper.DataTestOptionsMapper;

@RestController
@RequestMapping("/api/v1/data")
@RequiredArgsConstructor
public class DataController {

    private final GRPCDataService grpcDataService;
    private final TestDataService testDataService;
    private final DataMapper dataMapper;
    private final DataTestOptionsMapper dataTestOptionsMapper;

    @PostMapping("/send")
    public void send(@RequestBody DataDto dataDto) {

        Data data = dataMapper.toEntity(dataDto);
        grpcDataService.send(data);
    }

    @PostMapping("/test/send")
    public void testSend(@RequestBody DateTestOptionsDto testOptionsDto) {

        DataTestOptions testOptions = dataTestOptionsMapper.toEntity(testOptionsDto);
        testDataService.sendMessages(testOptions);
    }

}
