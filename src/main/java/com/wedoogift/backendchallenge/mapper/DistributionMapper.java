package com.wedoogift.backendchallenge.mapper;

import com.wedoogift.backendchallenge.domain.Distribution;
import com.wedoogift.backendchallenge.dto.DistributionDTO;
import com.wedoogift.backendchallenge.dto.DistributionLevelDTO;
import com.wedoogift.backendchallenge.shared.LevelEnum;
import lombok.RequiredArgsConstructor;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
@RequiredArgsConstructor
public abstract class DistributionMapper {


            @BeforeMapping
            public DistributionDTO level(Distribution distribution, String levelNumber, @MappingTarget DistributionDTO distributionDTO) {

                if(levelNumber.replaceAll("\\s+","").equalsIgnoreCase(String.valueOf(LevelEnum.LEVEL2))) {
                    DistributionLevelDTO distributionLevelDTO = new DistributionLevelDTO(distributionDTO.getId(),
                            distributionDTO.getAmount(),
                            distributionDTO.getStart_date(),
                            distributionDTO.getEnd_date(),
                            distributionDTO.getCompany_id(),
                            distributionDTO.getUser_id());
                    if(distribution.getWallet() != null){
                        distributionLevelDTO.setWallet_id(distribution.getWallet().getId());
                    }
                    return distributionLevelDTO;
                }
                return distributionDTO;
            }

            @Mappings({
                    @Mapping(source = "id", target = "id"),
                    @Mapping(source = "amount", target = "amount"),
                    @Mapping(source = "startDate", target="start_date"),
                    @Mapping(source = "endDate", target = "end_date"),
                    @Mapping(source = "company.id", target = "company_id"),
                    @Mapping(source = "user.id", target = "user_id"),
            })
            public abstract DistributionDTO toDto(Distribution distribution);

}
