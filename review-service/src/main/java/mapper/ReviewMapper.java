package mapper;

import api.ReviewRequest;
import api.ReviewResponse;
import model.ReviewModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    ReviewResponse toResponse(ReviewModel model);

    List<ReviewResponse> toResponseList(List<ReviewModel> models);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "writtenDate", expression = "java(LocalDateTime.now())")
    @Mapping(target = "createdAt", expression = "java(LocalDateTime.now())")
    @Mapping(target = "isApproved", constant = "false")
    @Mapping(target = "isUpdated", constant = "false")
    ReviewModel toEntity(ReviewRequest request);
}

