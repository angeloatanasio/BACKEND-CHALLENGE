package com.wedoogift.backendchallenge.mapper;

import com.wedoogift.backendchallenge.domain.*;
import com.wedoogift.backendchallenge.dto.*;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LevelMapper {

    CompanyMapper companyMapper = Mappers.getMapper(CompanyMapper.class);
    DistributionMapper distributionMapper = Mappers.getMapper(DistributionMapper.class);
    UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    public LevelDTO toDto(List<User> users,
                          List<Company> companies,
                          List<Distribution> distributions,
                          String levelNumber) {

        LevelDTO levelDTO = new LevelDTO();

        levelDTO.setUsers(users
                .stream()
                .map(user -> userMapper.level(user, levelNumber, userMapper.toDto(user)))
                .collect(Collectors.toList()));

        levelDTO.setCompanies(companies
                .stream()
                .map(company -> companyMapper.toDto(company))
                .collect(Collectors.toList()));

        levelDTO.setDistributions(distributions
                .stream()
                .map(distribution ->distributionMapper.level(distribution,
                        levelNumber.toLowerCase(),
                        distributionMapper.toDto(distribution)) )
                .collect(Collectors.toList()));

        return levelDTO;
    }
}
