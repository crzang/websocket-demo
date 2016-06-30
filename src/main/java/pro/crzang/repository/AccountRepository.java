package pro.crzang.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.crzang.model.Account;

/**
 * Created by crzang on 30.06.16.
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
  Account findOneByEmail(String email);
}
