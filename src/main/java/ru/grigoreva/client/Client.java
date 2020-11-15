package ru.grigoreva.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.http.*;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.grigoreva.models.Message;
import ru.grigoreva.models.RestRequest;
import ru.grigoreva.models.initialrequest.Admin;
import ru.grigoreva.models.initialrequest.InitialRequest;
import ru.grigoreva.models.request.Request;
import ru.grigoreva.models.request.User;
import javax.annotation.PostConstruct;

@Component  //Для создаия Bean
public class Client {

    Logger log = LoggerFactory.getLogger(Client.class);  //Подключено логирование
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

    @PostConstruct   //Анатоция используется, чтобы метод запускался после запуска приложения
    public void init() {
        log.info("Context is initialized");
        log.info("User name: {}", userName);
        if (password != null) {
            log.info("User password was successfully read");
        }
        connectToServer();
    }

//Подключение к серверу, передача username админа и пароля
    public void connectToServer() {
        Admin admin = new Admin(userName, password);
        InitialRequest initialRequest = new InitialRequest(admin);

        RestTemplate restTemplate = new RestTemplate();
        String serverAppUrl = url;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<InitialRequest> request = new HttpEntity<InitialRequest>(initialRequest, headers);
        restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());

        try {
            log.info("Try to set connection..");
            final String response = restTemplate.postForObject(serverAppUrl, request, String.class);
            log.info("Receive response: {}", response);
            log.info("Please, go to " + urlForPostForm + " to input your info");
        } catch (Exception e) {
            log.error("Failed to connect to server, will shout down the application..");
   //         initiateShutdown(1); ! Не работает
        }
    }

//Подключение к серверу, передача username админа и пароля
    public void sendJSon(User user){

        log.info("Sending request.....{}", user);

    //Для формирования message
        final String  PREFIX_MESSAGE = "Привет всем!";
        RestRequest restRequest = new RestRequest();
        Request request = new Request();
        Message message = new Message();
        message.setBody(PREFIX_MESSAGE);
        request.setUser(user);
        request.setMessage(message);
        restRequest.setRequest(request);

    //Для отправки запроса на эндпоинт, отличный от connectToServer()
       log.info("I send the: {}", restRequest);

        //Вспомогательные методы для отправки запроса
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(new MediaType("application","json"));
        HttpEntity<RestRequest> requestEntity = new HttpEntity<RestRequest>(restRequest, requestHeaders);
        restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());

    //Отправка запроса и обработка исключений, закрытие приложения в случае ошибки
        try {
            log.info("Try to send message");
            ResponseEntity<String> responseEntity = restTemplate
                    .exchange(url+dataEndpoint, HttpMethod.POST, requestEntity, String.class);
            String result = responseEntity.getBody();
            log.info("Receive response: {}", responseEntity);
        } catch (Exception e) {
            log.error("Failed to connect to server, will shout down the application..");
            //         initiateShutdown(1); ! Не работает
        }

    }

     // Возвращает 0, если нет ошибок
    private void initiateShutdown(int returnCode){
        SpringApplication.exit(applicationContext, () -> returnCode);
    }

    private String getBody() {   //Полезная нагрузка для post-зарпоса серверу
        return "{\n" +
                "    \"admin\": {\n" +
                "      \"login\": \"1\",\n" +
                "      \"password\": \"2\",\n" +
                "    }\n"+
                "}";
    }
}

//   "      \"login\": \" "    + userName   + "\",\n" +
//   "      \"password\": \" " + password + "\",\n" +


