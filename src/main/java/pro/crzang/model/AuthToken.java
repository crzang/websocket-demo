package pro.crzang.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.Instant;
import java.time.Period;
import java.util.UUID;

/**
 * Токен авторизации.
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "authtoken")
public class AuthToken {

  @Id
  private String token;

  @ManyToOne
  private Account account;

  private Instant expired;

  private Instant created;
  /**
   * Конструктор.
   * Срок истечения токена = текущее время + 1 день.
   * Токен генерируется случайно.
   */
  public AuthToken() {
    expired =Instant.now().plus(Period.ofDays(1));
    created=Instant.now();
    token= UUID.randomUUID().toString();
  }

  /**
   * Getter for property 'token'.
   *
   * @return Value for property 'token'.
   */
  public String getToken() {
    return token;
  }

  /**
   * Setter for property 'token'.
   *
   * @param token Value to set for property 'token'.
   */
  public void setToken(String token) {
    this.token = token;
  }

  /**
   * Getter for property 'account'.
   *
   * @return Value for property 'account'.
   */
  public Account getAccount() {
    return account;
  }

  /**
   * Setter for property 'account'.
   *
   * @param account Value to set for property 'account'.
   */
  public void setAccount(Account account) {
    this.account = account;
  }


  /**
   * Getter for property 'expired'.
   *
   * @return Value for property 'expired'.
   */
  public Instant getExpired() {
    return expired;
  }

  /**
   * Setter for property 'expired'.
   *
   * @param expired Value to set for property 'expired'.
   */
  public void setExpired(Instant expired) {
    this.expired = expired;
  }

  /**
   * Getter for property 'created'.
   *
   * @return Value for property 'created'.
   */
  public Instant getCreated() {
    return created;
  }

  /**
   * Setter for property 'created'.
   *
   * @param created Value to set for property 'created'.
   */
  public void setCreated(Instant created) {
    this.created = created;
  }
}
