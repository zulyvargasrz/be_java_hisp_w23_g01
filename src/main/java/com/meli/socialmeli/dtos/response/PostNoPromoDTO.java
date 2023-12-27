package com.meli.socialmeli.dtos.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostNoPromoDTO {
    @NotNull(message = "El  id no puede estar vacío.")
    @Min(value= 1, message= "El id debe ser mayor a cero")
    private Integer user_id;
    private Integer post_id;
    @NotNull(message = "La fecha no puede estar vacía.")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private String date;
    @Valid
    private ProductDTO product;
    @NotNull(message = "El campo no puede estar vacío.")
    private Integer category;
    @NotNull(message = "El  id no puede estar vacío.")
    @Max(value=10000000, message= "El precio máximo por producto es de 10.000.000")
    private Double price;
}
