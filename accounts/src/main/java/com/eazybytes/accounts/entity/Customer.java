package com.eazybytes.accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;

@Entity
@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
@FieldDefaults(level = PRIVATE)
public class Customer extends BaseEntity{
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "customer_id")
    Long customerId;
    String name;
    String email;

    @Column(name = "mobile_number")
    String mobileNumber;
}
