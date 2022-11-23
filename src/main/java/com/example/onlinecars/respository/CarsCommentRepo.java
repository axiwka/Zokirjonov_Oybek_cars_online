package com.example.onlinecars.respository;

import com.example.onlinecars.model.CarsComment;
import com.example.onlinecars.projection.CarsCommandProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarsCommentRepo extends JpaRepository<CarsComment,Integer> {

    @Query(nativeQuery = true, value = "select\n" +
            "    c.id,\n" +
            "    c.comment,\n" +
            "    c.users_id userId,\n" +
            "    u.full_name fullName\n" +
            "    from cars_comment c join users u on u.id = c.users_id where c.cars_Id=:carId ",
    countQuery = "select count(*) from cars_comment"
    )
    Page<CarsCommandProjection> getCarsCommentByCarId(Pageable page, Integer carId);

    @Query(nativeQuery = true, value = "select\n" +
            "    c.id,\n" +
            "    c.comment,\n" +
            "    c.users_id userId,\n" +
            "    u.full_name fullName\n" +
            "    from cars_comment c join users u on u.id = c.users_id where c.users_id=:userId")
    List<CarsCommandProjection> getCarsCommentByUserId(Integer userId);
}
