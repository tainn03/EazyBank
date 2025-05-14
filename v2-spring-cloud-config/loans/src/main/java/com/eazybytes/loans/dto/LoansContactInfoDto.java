package com.eazybytes.loans.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "loans")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoansContactInfoDto{
    String message;
    Map<String, String> contactDetails;
    List<String> onCallSupport;
}