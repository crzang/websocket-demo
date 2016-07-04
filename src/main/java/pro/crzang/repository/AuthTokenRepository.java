package pro.crzang.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.crzang.model.Account;
import pro.crzang.model.AuthToken;

import java.time.Instant;
import java.util.List;

@Repository
public interface AuthTokenRepository extends JpaRepository<AuthToken, String> {
  List<AuthToken> findByAccountAndExpiredAfter(Account account, Instant now);
}
