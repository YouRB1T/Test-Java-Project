package com.zhkh.office.mapper;

import com.zhkh.office.api.OfficeRequest;
import com.zhkh.office.api.OfficeResponse;
import com.zhkh.office.model.Office;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-16T18:08:53+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.2 (Amazon.com Inc.)"
)
@Component
public class OfficeMapperImpl implements OfficeMapper {

    @Override
    public OfficeResponse toResponse(Office office) {
        if ( office == null ) {
            return null;
        }

        OfficeResponse.OfficeResponseBuilder officeResponse = OfficeResponse.builder();

        officeResponse.officeId( office.getOfficeId() );
        officeResponse.buildingId( office.getBuildingId() );
        officeResponse.phone( office.getPhone() );
        officeResponse.email( office.getEmail() );

        return officeResponse.build();
    }

    @Override
    public List<OfficeResponse> toResponseList(List<Office> offices) {
        if ( offices == null ) {
            return null;
        }

        List<OfficeResponse> list = new ArrayList<OfficeResponse>( offices.size() );
        for ( Office office : offices ) {
            list.add( toResponse( office ) );
        }

        return list;
    }

    @Override
    public Office toEntity(OfficeRequest request) {
        if ( request == null ) {
            return null;
        }

        Office.OfficeBuilder office = Office.builder();

        office.buildingId( request.getBuildingId() );
        office.phone( request.getPhone() );
        office.email( request.getEmail() );

        return office.build();
    }
}
