package mapper;

import api.RequestRequestModel;
import api.ResponseRequestModel;
import model.RequestModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RequestMapper {

    RequestModel toEntity(RequestRequestModel dto);

    ResponseRequestModel toResponse(RequestModel entity);
}

