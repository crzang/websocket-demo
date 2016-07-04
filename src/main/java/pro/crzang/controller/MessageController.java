package pro.crzang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import pro.crzang.model.Message;
import pro.crzang.model.MessageType;

import java.util.HashMap;
import java.util.UUID;

/**
 * Корневой контроллер обработки сообщений.
 */
@Controller
public class MessageController {

  @Autowired
  private ApplicationContext context;

  /**
   * Обработка сообщения. Принимает сообщения по адресу message. Ответ отправляет на /topic/message.
   * @param message сообщение-запрос
   * @return сообщение-ответ.
   */
  @MessageMapping("/message")
  @SendTo("/topic/message")
  public Message processMessage(Message message) throws Exception {
    // Ищем именовый контроллер, тип которого указан в сообщении
    Object bean = context.getBean(message.getType());
    if(bean instanceof CommonController){
      return ((CommonController) bean).execute(message);
    }
    else {
      Message responseMessage = new Message();
      responseMessage.setSequence_id(UUID.randomUUID().toString());
      responseMessage.setType(MessageType.ERROR_REQUEST);
      responseMessage.setData(new HashMap<>());
      responseMessage.getData().put("error.message","Type of message not support.");
      return responseMessage;
    }
  }
}
