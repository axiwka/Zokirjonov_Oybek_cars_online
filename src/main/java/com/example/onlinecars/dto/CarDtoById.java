package com.example.onlinecars.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CarDtoById {
    private Integer id;
    private String city;
    private Integer year;
    private BigDecimal price;
    private List<Integer> coloursIds;
    private Integer transmissionId;
    private Integer brandId;
    private String carName;
    private String carDescription;

}
