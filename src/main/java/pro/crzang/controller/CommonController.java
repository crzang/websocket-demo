package pro.crzang.controller;

import pro.crzang.model.Message;

/**
 * Базовый контроллер для обработки сообщений.
 */
public interface CommonController {
  /**
   * Обработка сообщения.
   * @param message сообщение
   * @return ответ
   */
  Message execute(Message message);
}
