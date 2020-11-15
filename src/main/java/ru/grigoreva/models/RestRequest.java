package ru.grigoreva.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.grigoreva.models.request.Request;

//Модель данныx, аннотации ниже для вызова конструктора, геттеров и сеттеров без их явного создания (Lombok)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestRequest {

  @JsonProperty("Request")
  private Request request;
}
