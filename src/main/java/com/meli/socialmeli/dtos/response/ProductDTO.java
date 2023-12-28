package com.meli.socialmeli.dtos.response;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ProductDTO {
    @NotNull(message = "El  id no puede estar vacío.")
    @Min(value= 1, message= "El id debe ser mayor a cero")
    private Integer product_id;
    @NotBlank(message = "El campo no puede estar vacío.")
    @Size(max=40, message = "La longitud no puede superar los 40 caracteres.")
    @Pattern(regexp = "^[A-Za-z ]*$", message = "El campo no puede poseer caracteres especiales.")
    private String product_name;
    @NotBlank(message = "El campo no puede estar vacío.")
    @Size(max=15, message = "La longitud no puede superar los 15 caracteres.")
    @Pattern(regexp = "^[A-Za-z ]*$", message = "El campo no puede poseer caracteres especiales.")
    private String type;
    @NotBlank(message = "El campo no puede estar vacío.")
    @Size(max=25, message = "La longitud no puede superar los 25 caracteres.")
    @Pattern(regexp = "^[A-Za-z ]*$", message = "El campo no puede poseer caracteres especiales.")
    private String brand;
    @NotBlank(message = "El campo no puede estar vacío.")
    @Size(max=15, message = "La longitud no puede superar los 15 caracteres.")
    @Pattern(regexp = "^[A-Za-z ]*$", message = "El campo no puede poseer caracteres especiales.")
    private String color;
    @Size(max=80, message = "La longitud no puede superar los 80 caracteres.")
    @Pattern(regexp = "^[A-Za-z ]*$", message = "El campo no puede poseer caracteres especiales.")
    private String notes;
}
