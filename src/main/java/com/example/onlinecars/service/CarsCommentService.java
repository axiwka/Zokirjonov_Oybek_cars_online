package com.example.onlinecars.service;

import com.example.onlinecars.dto.CarsCommentDto;
import com.example.onlinecars.exception.BadRequestException;
import com.example.onlinecars.model.Cars;
import com.example.onlinecars.model.CarsComment;
import com.example.onlinecars.model.Users;
import com.example.onlinecars.projection.CarsCommandProjection;
import com.example.onlinecars.respository.CarRepo;
import com.example.onlinecars.respository.CarsCommentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CarsCommentService {
    private final CarsCommentRepo carsCommentRepo;
    private final CarRepo carRepo;

//    public List<CarsCommandProjection> getAllByCarId(Integer carId) {
//        List<CarsCommandProjection> exist = carsCommentRepo.getCarsCommentByCarId(carId);
//        if (!exist.isEmpty()) {
//            return carsCommentRepo.getCarsCommentByCarId(carId);
//        } else throw new IllegalArgumentException("Not found!!!");
//    }

    public List<CarsCommandProjection> getAllByUserId(Integer userId) {
        List<CarsCommandProjection> exist = carsCommentRepo.getCarsCommentByUserId(userId);
        if (!exist.isEmpty()) {
            return carsCommentRepo.getCarsCommentByUserId(userId);
        } else throw new IllegalArgumentException("Not found!!");
    }

    public void saveComment(CarsCommentDto carsCommentDto, Users users) {
        Optional<Cars> isCarExist = carRepo.findById(carsCommentDto.getCarId());
        if (isCarExist.isEmpty()) {
            throw new IllegalArgumentException("Car not found");
        }
        carsCommentRepo.save(CarsComment.builder()
                .car(isCarExist.get())
                .comment(carsCommentDto.getComment())
                .user(users)
                .build());
        System.out.println("Saved");
    }

    public void deleteComment(Integer id, Users currentUser) {
        Optional<CarsComment> carsComment = carsCommentRepo.findById(id);
        if (carsComment.isPresent()) {
            if (carsComment.get().getUser().getId().equals(currentUser.getId()))
                carsCommentRepo.deleteById(id);
            else throw new BadRequestException("You can't delete others comments");
        } else throw new IllegalArgumentException("Not found ");
    }

    public Page<CarsCommandProjection> pagination(int page, int size,int carId ){

        return carsCommentRepo.getCarsCommentByCarId(PageRequest.of(page-1,size),carId);
    }
}
