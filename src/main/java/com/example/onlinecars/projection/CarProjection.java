package com.example.onlinecars.projection;

import com.example.onlinecars.model.Colour;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;
import java.util.List;

public interface CarProjection {
    Integer getId();

    String getCity();

    Integer getYear();

    @Value("#{@colourRepo.getAll(target.id)}")
    List<ColourProjection> getColors();

    Integer getTransmissionId();

    String getTransmissionName();

    Integer getBrandId();

    String getBrandName();

    String getCarName();

    BigDecimal getPrice();

    String getCarDescription();
}
