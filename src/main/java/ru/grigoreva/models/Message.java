package ru.grigoreva.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//Модель данныx, аннотации ниже для вызова конструктора, геттеров и сеттеров без их явного создания (Lombok)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message {
  final String PATTERN = "yyyy-MM-dd HH:mm:ss";

  @JsonProperty("Body")
  private String body;

  @JsonProperty("Timestamp")
  private String timestamp = setTimestamp();

  private String setTimestamp() {
    LocalDateTime dateTime = LocalDateTime.now();
    DateTimeFormatter format = DateTimeFormatter.ofPattern(PATTERN);
    return dateTime.format(format);
  }
}
