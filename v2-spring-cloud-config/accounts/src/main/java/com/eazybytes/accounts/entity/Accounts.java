package com.eazybytes.accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class Accounts extends BaseEntity {
    @Column(name = "customer_id")
    Long customerId;

    @Id
    @Column(name = "account_number")
    Long accountNumber;

    @Column(name = "account_type")
    String accountType;

    @Column(name = "branch_address")
    String branchAddress;
}
