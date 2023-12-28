package com.meli.socialmeli.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.meli.socialmeli.dtos.response.ProductDTO;

import com.meli.socialmeli.utilities.CustomLocalDateDeserializer;
import com.meli.socialmeli.utilities.CustomLocalDateValidation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
    @JsonProperty("user_id")
    @NotNull(message = "El  id no puede estar vacío.")
    @Min( value= 1, message= "El id debe ser mayor a cero")
    Integer userId;
    @JsonDeserialize(using = CustomLocalDateDeserializer.class)
    @NotNull(message = "La fecha no puede estar vacía.")
    @CustomLocalDateValidation(message = "Fecha inválida. El formato debe ser yyyy-MM-dd")
    protected LocalDate date;
    protected @Valid ProductDTO product;
    @NotNull(message = "El campo no puede estar vacío.")
    protected Integer category;
    @DecimalMin(value="0", message= "El precio mínimo por producto es de 10.000.000")
    @DecimalMax(value="10000000", message= "El precio máximo por producto es de 10.000.000")
    protected Double price;
}

