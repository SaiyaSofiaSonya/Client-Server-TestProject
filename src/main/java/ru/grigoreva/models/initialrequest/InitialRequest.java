package ru.grigoreva.models.initialrequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//Модель данныx, аннотации ниже для вызова конструктора, геттеров и сеттеров без их явного создания (Lombok)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

//Создание обертки для запроса от клиента при подключении к серверу
public class InitialRequest {
    @JsonProperty("Admin")
    private Admin admin;
}
