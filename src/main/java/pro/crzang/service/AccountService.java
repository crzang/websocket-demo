package pro.crzang.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.crzang.model.Account;
import pro.crzang.model.AuthToken;
import pro.crzang.repository.AccountRepository;
import pro.crzang.repository.AuthTokenRepository;

import javax.annotation.PostConstruct;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.List;

/**
 * Сервис авторизации и создания токенов авторизации.
 */

@Service
@Configurable
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AccountService {
  @Autowired
  private AccountRepository accountRepository;

  @Autowired
  private AuthTokenRepository authTokenRepository;
  private MessageDigest       md5;

  /**
   * Инициализация алгоритма шифрования пароля.
   * Начальное заполнение данные.
   */
  @PostConstruct
  protected void initialize() {
    try {
      md5 = MessageDigest.getInstance("MD5");
    }
    catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    save(newAccount("user@mail.ru", "demo"));
    save(newAccount("admin@host.com", "admin"));
  }

  /**
   * Создание экземпляра класса Account.
   * @param email email
   * @param pass password
   * @return экземпляр класса Account
   */
  private Account newAccount(String email, String pass) {
    Account account = new Account();
    account.setEmail(email);
    account.setPassword(pass);
    return account;
  }

  /**
   * Сохранение в БД.
   * @param account аккаунт
   * @return аккаунт
   */
  @Transactional
  public Account save(Account account) {
    account.setPassword(new String(md5.digest(account.getPassword().getBytes())));
    accountRepository.save(account);
    return account;
  }

  /**
   * Авторизация.
   * @param email идентификатор
   * @param password пароль
   * @return успешность авторизации
   */
  public boolean autorize(String email, String password) {
    Account account = accountRepository.findOneByEmail(email);
    byte[] md5Password = md5.digest(password.getBytes());
    return account != null && account.getPassword().equals(new String(md5Password));
  }

  /**
   * Получение токена авторизации.
   * @param email идентификатор.
   * @return токен авторизации
   */
  public AuthToken getAuthToken(String email) {
    Account account = accountRepository.findOneByEmail(email);
    if(account!=null) {
      checkExistAuthToken(account);
      AuthToken authToken = new AuthToken();
      authToken.setAccount(account);
      authTokenRepository.save(authToken);
      return authToken;
    }
    return null;
  }

  /**
   * Проверка наличия существующих токенов авторизации. Сброс даты истечения.
   * @param account account
   */
  private void checkExistAuthToken(Account account) {
    List<AuthToken> tokens = authTokenRepository
        .findByAccountAndExpiredAfter(account, Instant.now());
    if (tokens != null) {
      for (AuthToken token : tokens) {
        token.setExpired(Instant.now());
        authTokenRepository.save(token);
      }
    }
  }
}
