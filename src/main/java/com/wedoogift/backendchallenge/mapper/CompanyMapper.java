package com.wedoogift.backendchallenge.mapper;

import com.wedoogift.backendchallenge.domain.Company;
import com.wedoogift.backendchallenge.dto.CompanyDTO;
import com.wedoogift.backendchallenge.shared.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompanyMapper extends EntityMapper<CompanyDTO, Company> {

    @Override
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "balance", target = "balance")
    })
    CompanyDTO toDto(Company company);

    @Override
    List<CompanyDTO> toDto(List<Company> companies);
}
