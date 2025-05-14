package com.eazybytes.accounts.dto;

import java.util.List;
import java.util.Map;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "accounts")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountsContactInfoDto {
     String message;
     Map<String, String> contactDetails;
     List<String> onCallSupport;
}
