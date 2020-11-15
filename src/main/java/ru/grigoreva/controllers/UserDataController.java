package ru.grigoreva.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.grigoreva.client.Client;
import ru.grigoreva.models.request.User;


//@Component //анологичен @Component, @ComponentScan используется для создания Bean
@Controller
//@RequestMapping("/userinfo") //Все методы в контроллере имели префикс userinfo
public class UserDataController {

    Logger log = LoggerFactory.getLogger(Client.class);  //Подключено логирование
    private final Client client;

    @Autowired //Внедрение Client в контроллер
    public UserDataController(Client client) {
        this.client = client;
    }

    @GetMapping("/new")  //Возвращает страницу new.html  пользователю при переходе /userinfo/new, метод get
    public String newUserInfo(@ModelAttribute("user") User user) { //В контроллер внедряется модель данных User
        return "new";
    }

    @PostMapping("/new")
    public String addUserInfo(@ModelAttribute("user") User user){ //В контроллер внедряется модель данных User
        System.out.println("The next stage "+ user.getName());
        log.info("Object received: {}", user.getName());
        client.sendJSon(user);
     return "success";
    }

    @GetMapping("/success") //Возвращает страницу success.html пользователю при переходе /userinfo/success, метод get
    public String redirect(@ModelAttribute("user") User user) { //В контроллер внедряется модель данных User
        return "success";
    }
}

