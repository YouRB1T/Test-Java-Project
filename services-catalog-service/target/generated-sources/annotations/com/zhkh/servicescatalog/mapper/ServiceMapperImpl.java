package com.zhkh.servicescatalog.mapper;

import com.zhkh.servicescatalog.api.ServiceRequest;
import com.zhkh.servicescatalog.api.ServiceResponse;
import com.zhkh.servicescatalog.model.ServiceEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-16T23:13:37+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.2 (Amazon.com Inc.)"
)
@Component
public class ServiceMapperImpl implements ServiceMapper {

    @Override
    public ServiceResponse toResponse(ServiceEntity serviceEntity) {
        if ( serviceEntity == null ) {
            return null;
        }

        ServiceResponse serviceResponse = new ServiceResponse();

        serviceResponse.setServiceId( serviceEntity.getServiceId() );
        serviceResponse.setServiceName( serviceEntity.getServiceName() );
        serviceResponse.setDescription( serviceEntity.getDescription() );
        serviceResponse.setIsActive( serviceEntity.getIsActive() );

        return serviceResponse;
    }

    @Override
    public List<ServiceResponse> toResponse(List<ServiceEntity> services) {
        if ( services == null ) {
            return null;
        }

        List<ServiceResponse> list = new ArrayList<ServiceResponse>( services.size() );
        for ( ServiceEntity serviceEntity : services ) {
            list.add( toResponse( serviceEntity ) );
        }

        return list;
    }

    @Override
    public ServiceEntity toEntity(ServiceRequest request) {
        if ( request == null ) {
            return null;
        }

        ServiceEntity serviceEntity = new ServiceEntity();

        serviceEntity.setServiceName( request.getServiceName() );
        serviceEntity.setDescription( request.getDescription() );

        return serviceEntity;
    }
}
