package ru.grigoreva.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.grigoreva.models.Message;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Request {

    @JsonProperty("User")
    User user;

    @JsonProperty("Message")
    Message message;

}

