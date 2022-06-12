package com.wedoogift.backendchallenge.mapper;

import com.wedoogift.backendchallenge.domain.User;
import com.wedoogift.backendchallenge.dto.UserDTO;
import com.wedoogift.backendchallenge.dto.UserLevel1DTO;
import com.wedoogift.backendchallenge.dto.UserLevel2DTO;
import com.wedoogift.backendchallenge.dto.WalletBalanceDTO;
import com.wedoogift.backendchallenge.shared.LevelEnum;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class UserMapper{

            @Mapping(source = "user.id", target = "id")
            public abstract  UserDTO toDto(User user);

            @AfterMapping
            public UserDTO level(User user, String levelNumber, @MappingTarget UserDTO userDTO) {

                if(levelNumber.replaceAll("\\s+","").equalsIgnoreCase(String.valueOf(LevelEnum.LEVEL2))) {
                    UserLevel2DTO userLevel2DTO = new UserLevel2DTO(user.getId());
                    user.getUserWallets()
                            .forEach(userWallet ->userLevel2DTO.getBalance()
                                    .add(new WalletBalanceDTO( userWallet.getWallet().getId(), userWallet.getBalance())));

                    return userLevel2DTO;
                }
                long walletId = Long.parseLong(LevelEnum.LEVEL1.toString()
                        .substring(LevelEnum.LEVEL1.toString().length() - 1));

                return user.getUserWallets()
                        .stream()
                        .filter(userWallet -> userWallet.getWallet() != null && userWallet.getWallet().getId() == walletId)
                        .map(userWallet -> new UserLevel1DTO(userDTO.getId(), userWallet.getBalance()))
                        .findFirst().orElseThrow(() -> new IllegalArgumentException("Il n'existe pas d'user wallet avec cet id"));
            }
}
