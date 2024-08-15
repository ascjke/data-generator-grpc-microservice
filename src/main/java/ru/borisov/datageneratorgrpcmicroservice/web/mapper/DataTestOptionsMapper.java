package ru.borisov.datageneratorgrpcmicroservice.web.mapper;

import org.mapstruct.Mapper;
import ru.borisov.datageneratorgrpcmicroservice.model.test.DataTestOptions;
import ru.borisov.datageneratorgrpcmicroservice.web.dto.DateTestOptionsDto;

@Mapper(componentModel = "spring")
public interface DataTestOptionsMapper extends Mappable<DataTestOptions, DateTestOptionsDto> {

}
