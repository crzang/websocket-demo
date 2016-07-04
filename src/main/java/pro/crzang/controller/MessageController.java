package pro.crzang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import pro.crzang.model.Message;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by crzang on 29.06.16.
 */
@Controller
public class MessageController {

  @Autowired
  private ApplicationContext context;

  /*@PostConstruct
  public void init(){
    context = new ClassPathXmlApplicationContext(new String[] { "WEB-INF/beans.xml" });
  }*/

  @MessageMapping("/message")
  @SendTo("/topic/message")
  public Message login(Message message) throws Exception {
    Object bean = context.getBean(message.getType());
    if(bean instanceof CommonController){
      return ((CommonController) bean).execute(context,message);
    }
    else {
      Message responseMessage = new Message();
      responseMessage.setSequence_id(UUID.randomUUID().toString());
      responseMessage.setType(Message.Type.ERROR_REQUEST);
      responseMessage.setData(new HashMap<>());
      responseMessage.getData().put("error.message","Type of message not support.");
      return responseMessage;
    }
  }
}
