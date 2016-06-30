package pro.crzang.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import pro.crzang.model.Greeting;
import pro.crzang.model.UserData;

//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by crzang on 29.06.16.
 */
@Controller
public class AccountController {

 // @Autowired
 // private AccountService accountService;

  @MessageMapping("/auth")
  @SendTo("/topic/autorize")
  public Greeting login(UserData userData) throws Exception {
    //accountService.signin(account);
    /*Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if(auth.isAuthenticated()){
      return new Greeting("Hello, " + auth.getName() + "!");
    }
    else {
      return new Greeting("Error autorize");
    }*/
    return new Greeting("Error autorize "+userData.getEmail());
  }
}
