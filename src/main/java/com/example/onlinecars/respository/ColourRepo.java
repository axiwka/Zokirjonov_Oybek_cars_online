package com.example.onlinecars.respository;

import com.example.onlinecars.model.Colour;
import com.example.onlinecars.projection.ColourProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ColourRepo extends JpaRepository<Colour, Integer> {

    @Query(nativeQuery = true, value = "select color.id ,\n" +
            "             color.colour_name as colourName \n" +
            "            from cars  join cars_colours cc on cars.id = cc.cars_id\n" +
            "                join colour color on color.id = cc.colours_id\n" +
            "            where cars.id = :id")
    List<ColourProjection> getAll(Integer id);
}
