package com.example.skillboxpractice2.dto;

import lombok.*;

import java.util.UUID;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private UUID id;
    private String firstName;
    private String lastName;
    private int age;
}
