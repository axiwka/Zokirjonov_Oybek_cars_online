package com.example.onlinecars.service;

import com.example.onlinecars.dto.CarDto;
import com.example.onlinecars.dto.CarDtoById;
import com.example.onlinecars.model.*;
import com.example.onlinecars.projection.CarProjection;
import com.example.onlinecars.respository.BrandRepo;
import com.example.onlinecars.respository.CarRepo;
import com.example.onlinecars.respository.ColourRepo;
import com.example.onlinecars.respository.TransmissionRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepo repo;
    private final BrandRepo brandRepo;
    private final ColourRepo colourRepo;
    private final TransmissionRepo transmissionRepo;


    public List<CarProjection> getAll() {
        return repo.getAllCars();
    }

    public CarProjection getById(Integer id) {
        Optional<CarProjection> exist = repo.getCarById(id);
        if (exist.isPresent()) {
            return exist.get();
        } else throw new IllegalArgumentException("Car not found");
    }

    public List<CarProjection> getCarByYear(Integer year) {
        return repo.getCarByYear(year);
    }

    public void addCar(CarDto carDto, Users users) {
        List<Integer> coloursIds = carDto.getColoursIds();

        List<Colour> allColorsByIds = colourRepo.findAllById(coloursIds);


        Optional<Brand> brand = brandRepo.findById(carDto.getBrandId());
        if (brand.isEmpty()) {
            throw new IllegalArgumentException("Not found");
        }

        Optional<Transmission> transmission = transmissionRepo.findById(carDto.getTransmissionId());
        if (transmission.isEmpty()) {
            throw new IllegalArgumentException("Not found");
        }
        System.out.println(carDto);

        Cars cars = Cars.builder()
                .city(carDto.getCity())
                .year(carDto.getYear())
                .transmission(transmission.get())
                .brand(brand.get())
                .price(carDto.getPrice())
                .colours(allColorsByIds)
                .carName(carDto.getCarName())
                .carDescription(carDto.getCarDescription())
                .user(users)
                .build();
        repo.save(cars);
        System.out.println("Saved");
    }

    public void updateCar(CarDtoById carDtoById,Users users) {
        if(repo.findById(carDtoById.getId()).isPresent()){
            List<Colour> allColorsByIds = colourRepo.findAllById(carDtoById.getColoursIds());

            Optional<Brand> brand = brandRepo.findById(carDtoById.getBrandId());
            if (brand.isEmpty()) {
                throw new IllegalArgumentException("Not found");
            }

            Optional<Transmission> transmission = transmissionRepo.findById(carDtoById.getTransmissionId());
            if (transmission.isEmpty()) {
                throw new IllegalArgumentException("Not found");
            }

            Cars cars = Cars.builder()
                    .id(carDtoById.getId())
                    .city(carDtoById.getCity())
                    .year(carDtoById.getYear())
                    .transmission(transmission.get())
                    .brand(brand.get())
                    .price(carDtoById.getPrice())
                    .colours(allColorsByIds)
                    .carName(carDtoById.getCarName())
                    .carDescription(carDtoById.getCarDescription())
                    .user(users)
                    .build();
            repo.save(cars);
            System.out.println("Saved");
        }
        else throw new IllegalArgumentException("Car not found");
    }
    public void deleteCar(Integer id) {
        Optional<Cars> exist = repo.findById(id);
        if (exist.isPresent()) {
            repo.deleteById(id);
        } else throw new IllegalArgumentException("Car not found");
    }

    public List<CarProjection> getCarsBetweenYears(Integer year1,Integer year2){
        if(year2-year1<0){
            throw new IllegalArgumentException("Wrong year");
        }else return repo.getCarsByBetweenYears(year1,year2);
    }



}
