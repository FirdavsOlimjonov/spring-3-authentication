package com.alibou.security.payload;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderAddDTO {
    @NotBlank
    String firstName;
    @NotBlank
    String lastName;
    @NotNull
    @Min(1)
    Long numberOfOrders;
    @NotBlank
    String phoneNumber;
    @NotBlank
    String address;
}
