package com.meli.socialmeli.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    private int post_id;
    private LocalDate date;
    private String category;
    private double price;
    private boolean has_promo;
    private double discount;
}
