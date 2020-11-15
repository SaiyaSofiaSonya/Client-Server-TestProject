package ru.grigoreva.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//Модель данныx, аннотации ниже для вызова конструктора, геттеров и сеттеров без их явного создания (Lombok)
@Builder
@Data //Для автоматического формирования геттеров, сеттеров и конструкторов
@NoArgsConstructor //Конструктор без аргументов
@AllArgsConstructor
public class User {

  @JsonProperty("Name")  //Отображение Json
  private String name;
  @JsonProperty("SecondName")
  private String secondName;
 }
