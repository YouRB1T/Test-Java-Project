package com.zhkh.counterparty.mapper;

import com.zhkh.counterparty.api.CounterpartyRequest;
import com.zhkh.counterparty.api.CounterpartyResponse;
import com.zhkh.counterparty.model.Counterparty;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CounterpartyMapper {

    CounterpartyMapper INSTANCE = Mappers.getMapper(CounterpartyMapper.class);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "taxId", target = "taxId")
    @Mapping(source = "RRC", target = "RRC")
    @Mapping(source = "PSRN", target = "PSRN")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "phone", target = "phone")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "contactPerson", target = "contactPerson")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "isActive", constant = "true")
    @Mapping(target = "createAt", expression = "java(new java.util.Date())")
    @Mapping(target = "updateAt", ignore = true)
    Counterparty toEntity(CounterpartyRequest request);

    CounterpartyResponse toResponse(Counterparty counterparty);
}
