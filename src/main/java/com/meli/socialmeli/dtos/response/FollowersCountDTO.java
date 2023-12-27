package com.meli.socialmeli.dtos.response;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FollowersCountDTO {
    @NotNull(message = "El  id no puede estar vacío.")
    @Min(value= 1, message= "El id debe ser mayor a cero")
    private Integer user_id;
    @Size(max=15, message = "La longitud no puede ser mayor a 15")
    private String user_name;
    private int followers_count;
}
