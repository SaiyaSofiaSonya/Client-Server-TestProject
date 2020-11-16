package ru.grigoreva.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.http.*;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.grigoreva.models.Message;
import ru.grigoreva.models.RestRequest;
import ru.grigoreva.models.initialrequest.Admin;
import ru.grigoreva.models.initialrequest.InitialRequest;
import ru.grigoreva.models.request.Request;
import ru.grigoreva.models.request.User;
import javax.annotation.PostConstruct;

@Slf4j //Подключено логирование
@Service  //Для создаия Bean
public class Client {

    private ApplicationContext applicationContext;

    //Значения подтягиваются из файла application.yml
    @Value("${server-app.url}")
    private String url;
    @Value("${user.login}")
    private String userName;
    @Value("${user.password}")
    private String password;
    @Value("${server-app.dataEndpoint}")
    private String dataEndpoint;
    @Value("${client-app.url}")
    private String urlForPostForm;

    @PostConstruct   //Анотоция используется, чтобы метод запускался после запуска приложения
    public void init() {
        log.info("Происходит инициализация");
        log.info("User name: {}", userName);
        if (password != null) {
            log.info("User password успешно считан");
        }
        connectToServer();
    }

//Подключение к серверу, передача username админа и пароля
    public void connectToServer() {
    //формируется  объект для Json, преобразуется в Json
        Admin admin = new Admin(userName, password);
        InitialRequest initialRequest = new InitialRequest(admin);

        RestTemplate restTemplate = new RestTemplate();
        String serverAppUrl = url;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<InitialRequest> request = new HttpEntity<InitialRequest>(initialRequest, headers);
        restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());

        try {
        //Данные отправлены, ответ записывается в переменную
            final String response = restTemplate.postForObject(serverAppUrl, request, String.class);
            log.info("Название сервера: {}", response);
        //Выводится ссылка на экран для заполенния формы Фамилии и Имени пользователя
            log.info("Пожалуйста, перейдите по ссылке: " + urlForPostForm + " для заполения формы");
        } catch (Exception e) {
            log.error("Не получилось подключиться к серверу,приложение будет выключено.");
            initiateShutdown(1);
        }
    }

//Подключение к серверу, передача username админа и пароля
    public void sendJSon(User user){

    //Для формирования объекта Request
        final String  PREFIX_MESSAGE = "Привет всем!";
        RestRequest restRequest = new RestRequest();
        Request request = new Request();
        Message message = new Message();
        message.setBody(PREFIX_MESSAGE);
        request.setUser(user);
        request.setMessage(message);
        restRequest.setRequest(request);

    //Вспомогательные методы для отправки запроса
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(new MediaType("application","json"));
        HttpEntity<RestRequest> requestEntity = new HttpEntity<RestRequest>(restRequest, requestHeaders);
        restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());

    //Отправка запроса и обработка исключений, закрытие приложения в случае ошибки
        try {
            ResponseEntity<String> responseEntity = restTemplate
                    .exchange(url+dataEndpoint, HttpMethod.POST, requestEntity, String.class);
            String result = responseEntity.getBody();
        } catch (Exception e) {
            log.error("Не получилось подключиться к серверу, выключаю приложение..");
            initiateShutdown(1);
        }

    }

    // Выключает приложение, код 1 используется в случае ошибки
    private void initiateShutdown(int returnCode){
        SpringApplication.exit(applicationContext
                , () -> returnCode);
    }
}



