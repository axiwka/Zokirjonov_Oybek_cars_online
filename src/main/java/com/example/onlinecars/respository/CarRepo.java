package com.example.onlinecars.respository;
import com.example.onlinecars.model.Cars;
import com.example.onlinecars.projection.CarProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface CarRepo extends JpaRepository<Cars,Integer> {
    @Query(nativeQuery = true,value = "select c.id,\n" +
            "       c.city,\n" +
            "       c.year,\n" +
            "       c.price,\n" +
            "       t.id transmissionId,\n" +
            "       t.transmission transmissionName,\n" +
            "       b.id brandId,\n" +
            "       b.name brandName,\n" +
            "       c.car_name carName,\n" +
            "       c.car_description carDescription\n" +
            "from cars as c\n" +
            "join brand b on b.id = c.brand_id\n" +
            "join transmission t on c.transmission_id = t.id")
    List<CarProjection> getAllCars();

    @Query(nativeQuery = true,value = "select c.id,\n" +
            "       c.city,\n" +
            "       c.year,\n" +
            "       c.price,\n" +
            "       c2.id colourId,\n" +
            "       c2.name colourName,\n" +
            "       t.id transmissionId,\n" +
            "       t.transmission transmissionName,\n" +
            "       b.id brandId,\n" +
            "       b.name brandName,\n" +
            "       c.car_name carName,\n" +
            "       c.car_description carDescription\n" +
            "from cars as c join cars_colour cc on c.id = cc.cars_id\n" +
            "    join colour c2 on c2.id = cc.colour_id\n" +
            "join brand b on b.id = c.brand_id\n" +
            "join transmission t on c.transmission_id = t.id where c.id=:id")
    Optional<CarProjection> getCarById(Integer id);

    @Query(nativeQuery = true,value = "select c.id,\n" +
            "                   c.city,\n" +
            "                   c.year,\n" +
            "                   c.price,\n" +
            "                   t.id transmissionId,\n" +
            "                   t.transmission transmissionName,\n" +
            "                   b.id brandId,\n" +
            "                   b.name brandName,\n" +
            "                   c.car_name carName,\n" +
            "                   c.car_description carDescription\n" +
            "                   from cars c\n" +
            "            join brand b on b.id = c.brand_id\n" +
            "            join transmission t on c.transmission_id = t.id where c.year=:yil")
    List<CarProjection> getCarByYear(Integer yil);

    @Query(nativeQuery = true,value = "select c.id,\n" +
            "       c.city,\n" +
            "       c.year,\n" +
            "       c.price,\n" +
            "       t.id              transmissionId,\n" +
            "       t.transmission    transmissionName,\n" +
            "       b.id              brandId,\n" +
            "       b.name            brandName,\n" +
            "       c.car_name        carName,\n" +
            "       c.car_description carDescription\n" +
            "from cars as c\n" +
            "         join brand b on b.id = c.brand_id\n" +
            "         join transmission t on c.transmission_id = t.id\n" +
            "where c.year between :from and :to")
    List<CarProjection> getCarsByBetweenYears(Integer from,Integer to);






}
