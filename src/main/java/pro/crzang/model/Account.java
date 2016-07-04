package pro.crzang.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by crzang on 30.06.16.
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "account")
public class Account {
  @Id
  @GeneratedValue
  private Long id;

  @Column(unique = true)
  private String email;

  private String password;

  @OneToMany(mappedBy="account")
  private List<AuthToken> authTokens;
  /**
   * Getter for property 'id'.
   *
   * @return Value for property 'id'.
   */
  public Long getId() {
    return id;
  }

  /**
   * Getter for property 'email'.
   *
   * @return Value for property 'email'.
   */
  public String getEmail() {
    return email;
  }

  /**
   * Setter for property 'email'.
   *
   * @param email Value to set for property 'email'.
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Getter for property 'password'.
   *
   * @return Value for property 'password'.
   */
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

}
