package com.meli.socialmeli.dtos.response;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostsFromFollowsDTO {
    @NotNull(message = "El  id no puede estar vacío.")
    @Min(value= 1, message= "El id debe ser mayor a cero")
    private Integer user_id;
    private List<@Valid PostNoPromoDTO> posts;
}
