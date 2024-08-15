package ru.borisov.datageneratorgrpcmicroservice.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.borisov.datageneratorgrpcmicroservice.model.Data;

@NoArgsConstructor
@Getter
@Setter
public class DateTestOptionsDto {

    private int delayInSeconds;
    private Data.MeasurementType[] measurementTypes;

}
