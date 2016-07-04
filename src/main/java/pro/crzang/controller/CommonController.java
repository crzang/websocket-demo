package pro.crzang.controller;

import org.springframework.context.ApplicationContext;
import pro.crzang.model.Message;

/**
 * Created by crzang on 04.07.16.
 */
public interface CommonController {
  Message execute(ApplicationContext context, Message message);
}
