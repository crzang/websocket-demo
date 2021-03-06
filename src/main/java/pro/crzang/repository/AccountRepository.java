package pro.crzang.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.crzang.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
  Account findOneByEmail(String email);
}
