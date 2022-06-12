package com.wedoogift.backendchallenge.facade;

import com.wedoogift.backendchallenge.domain.User;
import com.wedoogift.backendchallenge.dto.LevelDTO;
import com.wedoogift.backendchallenge.mapper.LevelMapper;
import com.wedoogift.backendchallenge.repository.*;
import com.wedoogift.backendchallenge.shared.LevelEnum;
import com.wedoogift.backendchallenge.shared.service.DistributionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class LevelFacade {

    private final CompanyRepository companyRepository;
    private final DistributionRepository distributionRepository;
    private final UserRepository userRepository;
    private final LevelMapper levelMapper;
    private final DistributionService distributionService;

    public LevelDTO findAll(String levelNumber) {
        boolean isLevel = levelNumber.replaceAll("\\s+", "").equalsIgnoreCase(String.valueOf(LevelEnum.LEVEL2));

        List<User> users = userRepository.findAll();

            users.stream().filter(user -> !user.getUserWallets().isEmpty()).forEach(user -> user.getUserWallets()
                    .forEach(userWallet ->
                            distributionService
                                    .calculateBalanceDistributionAndUserWalletWhenExpired(userWallet.getWallet(), user, user.getCompany())));


        return levelMapper.toDto( !isLevel
                       ? userRepository.findUsersByLevel1()
                       :  users,
                companyRepository.findAll(),
               !isLevel
                       ? distributionRepository.findDistributionByLevel1()
                       : distributionRepository.findAll(),
               levelNumber);
    }

    public void calculateBalance(int distributionAmount, long userId, String type) {
        distributionService.calculateBalance(distributionAmount, userId, type );
    }
}
