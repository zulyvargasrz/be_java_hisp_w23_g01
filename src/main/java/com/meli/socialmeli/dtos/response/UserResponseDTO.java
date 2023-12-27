package com.meli.socialmeli.dtos.response;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserResponseDTO {
    @NotNull(message = "El  id no puede estar vacío.")
    @Min(value= 1, message= "El id debe ser mayor a cero")
    private Integer user_id;
    @Size(max=15, message = "La longitud no puede ser mayor a 15")
    private String user_name;
    private List<String> followers;
    private List<String> followed;
}
