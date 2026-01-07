package mapper;

import api.ResponseChange;
import model.ChangeModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChangeMapper {

    ResponseChange toResponse(ChangeModel model);
}

