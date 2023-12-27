package com.meli.socialmeli.dtos.response;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Min;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserUnfollowDTO {
    @NotNull(message = "El  id no puede estar vacío.")
    @Min(value= 1, message= "El id debe ser mayor a cero")
    private int userId;
    @NotNull(message = "El  id no puede estar vacío.")
    @Min(value= 1, message= "El id debe ser mayor a cero")
    private int userIdToUnfollow;
}
