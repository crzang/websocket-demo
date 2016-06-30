package pro.crzang.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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

  @JsonIgnore
  private String password;

  private String role = "ROLE_USER";

  @JsonCreator
  public Account(String email, String password) {
    this.email = email;
    this.password = password;
  }

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

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }
}
