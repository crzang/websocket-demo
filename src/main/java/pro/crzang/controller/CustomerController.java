package pro.crzang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
import pro.crzang.model.AuthToken;
import pro.crzang.model.Message;
import pro.crzang.model.MessageType;
import pro.crzang.service.AccountService;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Контроллер сообщения с типом LOGIN_CUSTOMER.
 * Производит авторизацию пользователя и выдачу токена авторизации.
 */
@Component("LOGIN_CUSTOMER")
@Configurable
public class CustomerController implements CommonController {

  @Autowired
  private AccountService accountService;

  @Override
  public Message execute(Message message) {
    Message result = new Message();
    result.setData(new HashMap<>());
    Map<String, String> data = message.getData();
    if (checkParam(result, data, "email", "Email not defined", "email.notFound") ||
        checkParam(result, data, "password", "Password not defined", "password.notFound"))
      return result;
    String email = data.get("email");
    if(accountService.autorize(email,data.get("password"))){
      result.setType(MessageType.CUSTOMER_API_TOKEN);
      result.setSequence_id(UUID.randomUUID().toString());
      AuthToken authToken=accountService.getAuthToken(email);
      result.getData().put("api_token",authToken.getToken());
      result.getData().put("api_token_expiration_date", authToken.getExpired().toString());
    }
    else{
      result.setType(MessageType.CUSTOMER_ERROR);
      result.setSequence_id(message.getSequence_id());
      result.getData().put("error_description","Customer not found");
      result.getData().put("error_code","customer.notFound");
    }
    return result;
  }

  /**
   * Проверка входных парметров сообщения.
   * @param message результат
   * @param data данные
   * @param key ключ
   * @param errorDescription описание ошибки, если данных по ключу нет
   * @param errorCode код ошибки, если данных по ключу нет
   * @return отсутвие данных по ключу
   */
  private boolean checkParam(Message message, Map<String, String> data, String key, String errorDescription,
      String errorCode) {
    if (!data.containsKey(key)) {
      message.setType(MessageType.ERROR_REQUEST);
      message.setSequence_id(UUID.randomUUID().toString());
      message.getData().put("error_description", errorDescription);
      message.getData().put("error_code", errorCode);
      return true;
    }
    return false;
  }

}
