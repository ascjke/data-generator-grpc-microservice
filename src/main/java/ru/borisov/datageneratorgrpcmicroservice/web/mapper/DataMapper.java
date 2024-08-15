package ru.borisov.datageneratorgrpcmicroservice.web.mapper;

import org.mapstruct.Mapper;
import ru.borisov.datageneratorgrpcmicroservice.model.Data;
import ru.borisov.datageneratorgrpcmicroservice.web.dto.DataDto;

@Mapper(componentModel = "spring")
public interface DataMapper extends Mappable<Data, DataDto> {
}
