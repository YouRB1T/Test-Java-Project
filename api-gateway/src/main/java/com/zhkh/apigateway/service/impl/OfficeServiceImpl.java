package com.zhkh.apigateway.service.impl;

import com.zhkh.apigateway.api.building.BuildingResponse;
import com.zhkh.apigateway.api.office.OfficeRequest;
import com.zhkh.apigateway.api.office.OfficeResponse;
import com.zhkh.apigateway.api.office.OfficeResponseDTO;
import com.zhkh.apigateway.service.OfficeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;

// TODO: дописать url
@Service
@RequiredArgsConstructor
public class OfficeServiceImpl implements OfficeService {

    private final WebClient.Builder webClientBuilder;
    @Override
    public List<OfficeResponse> getAll() {
        List<OfficeResponseDTO> dtoList = webClientBuilder.build()
                .get()
                .uri("http://office-service/api/v1/offices")
                .retrieve()
                .bodyToFlux(OfficeResponseDTO.class)
                .collectList()
                .block();

        List<OfficeResponse> responseList = dtoList.stream()
                .map(dto -> {
                    BuildingResponse buildingResponse = webClientBuilder.build()
                            .get()
                            .uri("http://building-service/api/v1/buildings/" + dto.getBuildingId())
                            .retrieve()
                            .bodyToMono(BuildingResponse.class)
                            .block();
                    return OfficeResponse.builder()
                            .officeId(dto.getOfficeId())
                            .building(buildingResponse)
                            .phone(dto.getPhone())
                            .email(dto.getEmail())
                            .build();
                }).toList();

        return responseList;
    }

    @Override
    public OfficeResponse getById(UUID id) {
        OfficeResponseDTO responseDTO = webClientBuilder.build()
                .get()
                .uri("")
                .retrieve()
                .bodyToMono(OfficeResponseDTO.class)
                .block();
        return new OfficeResponse(
                responseDTO.getOfficeId(),
                webClientBuilder.build()
                        .get()
                        .uri("") // building GET URL
                        .retrieve()
                        .bodyToMono(BuildingResponse.class)
                        .block(),
                responseDTO.getPhone(),
                responseDTO.getEmail()
        );
    }

    @Override
    public OfficeResponse create(OfficeRequest request) {
        OfficeResponseDTO response = webClientBuilder.build()
                .post()
                .uri("")
                .retrieve()
                .bodyToMono(OfficeResponseDTO.class)
                .block();

        return new OfficeResponse(
                response.getOfficeId(),
                webClientBuilder.build()
                        .get()
                        .uri("")
                        .retrieve()
                        .bodyToMono(BuildingResponse.class)
                        .block(),
                response.getPhone(),
                response.getEmail()
        );
    }

    @Override
    public OfficeResponse update(UUID id, OfficeRequest request) {
        OfficeResponseDTO response = webClientBuilder.build()
                .put()
                .uri("")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(OfficeResponseDTO.class)
                .block();

        return new OfficeResponse(
                response.getOfficeId(),
                webClientBuilder.build()
                        .get()
                        .uri("")
                        .retrieve()
                        .bodyToMono(BuildingResponse.class)
                        .block(),
                response.getPhone(),
                response.getEmail()
        );
    }

    @Override
    public void delete(UUID id) {
        webClientBuilder.build()
                .delete()
                .uri("http://office-service/api/v1/offices/" + id)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }

    @Override
    public OfficeResponse getByBuildingId(UUID buildingId) {
        OfficeResponseDTO responseDTO = webClientBuilder.build()
                .get()
                .uri("")
                .retrieve()
                .bodyToMono(OfficeResponseDTO.class)
                .block();

        return new OfficeResponse(
                responseDTO.getOfficeId(),
                webClientBuilder.build()
                        .get()
                        .uri("")
                        .retrieve()
                        .bodyToMono(BuildingResponse.class)
                        .block(),
                responseDTO.getPhone(),
                responseDTO.getEmail()
        );
    }
}
