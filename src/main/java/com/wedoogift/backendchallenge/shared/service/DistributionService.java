package com.wedoogift.backendchallenge.shared.service;

import com.wedoogift.backendchallenge.domain.*;
import com.wedoogift.backendchallenge.repository.*;
import com.wedoogift.backendchallenge.shared.TypeWalletEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class DistributionService {

    private final CompanyRepository companyRepository;
    private final UserWalletRepository userWalletRepository;
    private final DistributionRepository distributionRepository;
    private final WalletRepository walletRepository;
    private final UserRepository userRepository;

    public void calculateBalance( int distributionAmount, long userId, String typeWallet) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("L'utilisateur n'existe pas en base de données"));

        Company company = companyRepository.findById(user.getCompany().getId())
                .orElseThrow(() -> new IllegalArgumentException("la compagnie n'existe pas avec cet utilisateur"));

        Wallet wallet = walletRepository.getWalletByType(typeWallet.replaceAll("\\s+","").toUpperCase())
                .orElseThrow(() -> new IllegalArgumentException("Il n'existe pas de portefeuille avec ce type"));

        Optional<UserWallet> userWalletExist = user.getUserWallets()
                .stream()
                .filter(u -> u.getUser().getId() == user.getId()
                            && u.getWallet().getId() == wallet.getId())
                .findFirst();

        UserWallet userWallet = userWalletExist.orElseGet(UserWallet::new);

        Optional<Distribution> distributionExist = user.getDistributions()
                .stream()
                .filter(d -> d.getUser().getId() == user.getId()
                        && d.getWallet() != null
                        && d.getWallet().getId() == wallet.getId())
                .findFirst();

        Distribution distribution = distributionExist.orElseGet(Distribution::new);

        saveDistributionAndAssociations(distributionAmount, user, company, userWallet, wallet, distribution);
    }

    private void saveDistributionAndAssociations( int distributionAmount,
                                                  User user,
                                                  Company company,
                                                  UserWallet userWallet,
                                                  Wallet wallet,
                                                  Distribution distribution) {

        if(company.getBalance() - distributionAmount < 0) {
            try {
                throw new Exception(" L'utilisateur ne peut recevoir davantage de carte vu que le solde de la compagnie est à zéro");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            LocalDate dateOfDistribution = LocalDate.now();

            try {
                String lifeSpanDistribution = wallet.getType().equalsIgnoreCase(String.valueOf(TypeWalletEnum.GIFT))
                        ? getNextDateForGiftCards(String.valueOf(dateOfDistribution))
                        : getNextDateForMealCards(String.valueOf(dateOfDistribution));

                if (dateOfDistribution.isBefore(LocalDate.parse(lifeSpanDistribution))
                        || dateOfDistribution.isEqual(LocalDate.parse(lifeSpanDistribution))) {

                    Distribution distributionToSave = distribution.getId() == 0
                            ? distribution.saveCalculateDistribution(distributionAmount, String.valueOf(dateOfDistribution), lifeSpanDistribution, company, user, wallet)
                            : distribution.updateCalculateDistribution(distributionAmount, distribution.getStartDate(),
                            distribution.getEndDate(), company, user, userWallet.getWallet()  );
                    distributionRepository.save(distributionToSave);

                    companyRepository.save(company.calculateBalanceCompanyByLevel(distributionAmount, company.getName()));

                    UserWallet userWalletToSave = userWallet.getId() == 0
                            ? userWallet.saveCalculateBalanceUserWalletByLevel(user, wallet, distributionAmount  )
                            : userWallet.updateCalculateBalanceUserWalletByLevel(user, userWallet.getWallet(), distributionAmount);
                    userWalletRepository.save(userWalletToSave);

                }

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public  void calculateBalanceDistributionAndUserWalletWhenExpired(Wallet wallet, User user, Company company) {
        distributionRepository.findAll()
                .forEach(distribution ->    {
                    if(LocalDate.parse(distribution.getEndDate()).isBefore(LocalDate.now())) {

                        userWalletRepository.findAll()
                                .forEach(userWallet -> {

                                    if(userWallet.getWallet().getId() == wallet.getId()
                                    && userWallet.getWallet().getId() == distribution.getWallet().getId()
                                    && userWallet.getUser().getId() == user.getId()
                                    && userWallet.getUser().getId() == distribution.getUser().getId()){

                                        distributionRepository.save(distribution.calculateDistributionWhenitExpire(distribution.getAmount(),
                                                distribution.getStartDate(),
                                                distribution.getEndDate(),
                                                company,
                                                user,
                                                wallet));
                                    }
                                });
                    }
                });
    }

    private  String getNextDateForGiftCards(String  curDate) throws ParseException {
        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        final Date date = format.parse(curDate);
        final Calendar calendar = getCalendar(date);
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        return format.format(calendar.getTime());
    }

    private String getNextDateForMealCards(String curDate) throws ParseException {
        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        final Date date = format.parse(curDate);
        final Calendar calendar = getCalendar(date);
        calendar.set(Calendar.MONTH, Calendar.FEBRUARY);
        calendar.set(Calendar.DAY_OF_MONTH, 28);
        return format.format(calendar.getTime());
    }

    private Calendar getCalendar(Date date) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, 1);
        return calendar;
    }
}
