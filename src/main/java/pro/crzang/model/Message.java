package pro.crzang.model;

import java.util.Map;

/**
 * Created by crzang on 30.06.16.
 */
public class Message {

  private String type;

  private String sequence_id;

  private Map<String,String> data;

  /**
   * Getter for property 'type'.
   *
   * @return Value for property 'type'.
   */
  public String getType() {
    return type;
  }

  /**
   * Setter for property 'type'.
   *
   * @param type Value to set for property 'type'.
   */
  public void setType(String type) {
    this.type = type;
  }

  /**
   * Getter for property 'sequence_id'.
   *
   * @return Value for property 'sequence_id'.
   */
  public String getSequence_id() {
    return sequence_id;
  }

  /**
   * Setter for property 'sequence_id'.
   *
   * @param sequence_id Value to set for property 'sequence_id'.
   */
  public void setSequence_id(String sequence_id) {
    this.sequence_id = sequence_id;
  }

  /**
   * Getter for property 'data'.
   *
   * @return Value for property 'data'.
   */
  public Map<String, String> getData() {
    return data;
  }

  /**
   * Setter for property 'data'.
   *
   * @param data Value to set for property 'data'.
   */
  public void setData(Map<String, String> data) {
    this.data = data;
  }
  
  public interface Type{
    String ERROR_REQUEST   = "ERROR_REQUEST";
    String CUSTOMER_API_TOKEN = "CUSTOMER_API_TOKEN";
    String CUSTOMER_ERROR="CUSTOMER_ERROR";
  }
}
