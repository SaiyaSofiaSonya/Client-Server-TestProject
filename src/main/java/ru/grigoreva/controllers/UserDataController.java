package ru.grigoreva.controllers;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.grigoreva.client.Client;
import ru.grigoreva.models.request.User;


@Slf4j //Подключено логирование
@Controller//Для принятия запросов с html страниц
public class UserDataController {

    private final Client client;

    @Autowired //Внедрение Client в контроллер
    public UserDataController(Client client) {
        this.client = client;
    }

    @GetMapping("/new")  //Возвращает страницу new.html , метод get
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

