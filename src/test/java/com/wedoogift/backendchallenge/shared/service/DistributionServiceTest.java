package com.wedoogift.backendchallenge.shared.service;

import com.wedoogift.backendchallenge.builder.*;
import com.wedoogift.backendchallenge.domain.*;
import com.wedoogift.backendchallenge.repository.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class DistributionServiceTest {

    @Mock
    private CompanyRepository companyRepository;
    @Mock
    private UserWalletRepository userWalletRepository;
    @Mock
    private DistributionRepository distributionRepository;
    @Mock
    private WalletRepository walletRepository;
    @Mock
    private UserRepository userRepository;

    @Test
    public void whenUserDontExist() {
        long userId=1;

        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
                () -> userRepository.findById(userId).orElseThrow(() ->new IllegalArgumentException("user inexistant")));
        assertEquals("user inexistant", illegalArgumentException.getMessage());
    }

    @Test
    public void whenCompanyDontExist() {
        long companyId=1;
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
                () -> companyRepository.findById(companyId)
                .orElseThrow(() -> new IllegalArgumentException("company inexistante")));

        assertEquals("company inexistante", illegalArgumentException.getMessage());
    }

    @Test
    public void whenWalletDontExistWithType() {
        String typeWallet = "gof t";

        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> walletRepository.getWalletByType(typeWallet.replaceAll("\\s+","").toUpperCase())
                .orElseThrow(() -> new IllegalArgumentException("wallet inexistant")));

        assertEquals("wallet inexistant", illegalArgumentException.getMessage());
        assertEquals("GOFT", typeWallet.replaceAll("\\s+","").toUpperCase());
    }

    @Test
    public void whenUserExist() {
        long userId=1;
        User user = getUser();
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        assertNotNull(user);
        assertEquals("wedoogift", user.getCompany().getName());
    }

    @Test
    public void whenCompanyExistAndUserAttached() {
        long userId=1;
        User user = getUser();


        Company company = getCompany();
        Distribution distribution = getDistribution();

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(companyRepository.findById(user.getCompany().getId())).thenReturn(Optional.of(company));

        assertNotNull(company);
        assertEquals("wedoogift", company.getName());
        assertEquals(1000, company.getBalance());
        assertEquals(company.getDistributions().get(0).getId(), distribution.getId());
    }

    private User getUser() {
        UserBuilder userBuilder = new UserBuilder();
        return userBuilder.withId(1)
                .withCompany(new Company(1,
                        "wedoogift",
                        100,
                        Collections.singletonList(new Distribution()),
                        Collections.singletonList(new User()) ))
                .withDistributions(Collections.singletonList(new Distribution()))
                .withUserWallets(Collections.singletonList(getUserWallet()))
                .buuild();
    }

    private Company getCompany() {
        CompanyBuilder companyBuilder = new CompanyBuilder();
        return companyBuilder.withId(1)
                .withBalance(1000)
                .withName("wedoogift")
                .withUsers(Collections.singletonList(getUser()))
                .withDistributions(Collections.singletonList(new Distribution(1,100,
                        "2022-06-13", "2023-06-12",
                        new Company(1, "wedoogift", 1000,
                                Collections.singletonList(new Distribution(1, 100, "2022-06-13", "2023-06-12", new Company(), getUser(), getWallet())),
                                Collections.singletonList(new User())),
                        getUser(),
                        getWallet())))
                .build();
    }

    private Wallet getWallet() {
        WalletBuilder walletBuilder = new WalletBuilder();
       return walletBuilder.withId(1)
                .withName("gift cards")
                .withType("GIFT")
                .withDistributions(Collections.singletonList(new Distribution(1, 100,
                        "2022-06-13",
                        "2023-06-12",
                                new Company(),
                                new User(),
                                new Wallet())))
                .withUserWallets(Collections.singletonList(new UserWallet()))
                .build();
    }

    private UserWallet getUserWallet() {
        UserWalletBuilder userWalletBuilder = new UserWalletBuilder();
       return userWalletBuilder.withId(1)
                .withBalance(100)
                .withUser(new User())
                .withWallet(new Wallet())
                .build();
    }

    private Distribution getDistribution() {
        DistributionBuilder distributionBuilder = new DistributionBuilder();
        return distributionBuilder.withId(1)
                .withCompany(getCompany())
                .withWallet(getWallet())
                .withUser(getUser())
                .withAmount(100)
                .withStartDate("2022-06-13")
                .withEndDate("2023-06-12")
                .build();
    }
}
