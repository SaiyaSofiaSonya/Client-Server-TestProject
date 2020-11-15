package ru.grigoreva.models.initialrequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


//Модель данныx, аннотации ниже для вызова конструктора, геттеров и сеттеров без их явного создания (Lombok)
@Data
@Builder
@AllArgsConstructor
public class Admin {

    @JsonProperty("Login")//Для отражения в Json
    private String login;


    @JsonProperty("Password")
    private String password;
}
