package ru.grigoreva.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.grigoreva.models.response.Response;

//Модель данныx, аннотации ниже для вызова конструктора, геттеров и сеттеров без их явного создания (Lombok)
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class RestResponse {

  @JsonProperty("Response")
  private Response response;
}
