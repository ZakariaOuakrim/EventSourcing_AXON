package ma.zakaria.analyticsservice.repo;

import ma.zakaria.analyticsservice.entities.AccountAnalytics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountAnalyticsRepo extends JpaRepository<AccountAnalytics,Long> {
    AccountAnalytics findByAccountId(String accountId);
}