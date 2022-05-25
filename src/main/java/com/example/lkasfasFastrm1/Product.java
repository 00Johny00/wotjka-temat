package com.example.lkasfasFastrm1;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Objects;

@Data
public class Product {

    @Id
    private final Long id;
    private final String undertitle;
    private final String category;
    private final boolean hero;
}
