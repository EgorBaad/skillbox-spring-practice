package com.example.m3practice.data;

import lombok.Data;
import lombok.experimental.FieldNameConstants;

import java.util.UUID;

@Data
@FieldNameConstants
public class Contact {
    String id;
    String firstName;
    String lastName;
    String email;
    String phone;
}
