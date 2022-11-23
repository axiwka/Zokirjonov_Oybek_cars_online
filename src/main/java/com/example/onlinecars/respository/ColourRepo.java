package com.example.onlinecars.respository;

import com.example.onlinecars.model.Colour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ColourRepo extends JpaRepository<Colour, Integer> {

    @Query(nativeQuery = true, value = "select c2.id,\n" +
            "       c2.name\n" +
            "from cars c join cars_colours cc on c.id = cc.cars_id\n" +
            "join colour c2 on c2.id = cc.colours_id\n" +
            "where c.id = :id")
    List<Colour> getAll(Integer id);
}
