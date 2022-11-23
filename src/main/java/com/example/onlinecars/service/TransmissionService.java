package com.example.onlinecars.service;

import com.example.onlinecars.model.Transmission;
import com.example.onlinecars.respository.TransmissionRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransmissionService  {
    private final TransmissionRepo transmissionRepo;

    public List<Transmission> getAll(){
        return transmissionRepo.findAll();
    }

    public void saveTransmission(Transmission transmission){
        transmissionRepo.save(transmission);
    }

    public void editTransmission(Transmission transmission){
        transmissionRepo.save(transmission);
    }
    public Transmission getById(Integer id){
        Optional<Transmission> exist = transmissionRepo.findById(id);
        if (exist.isPresent()) {
            return exist.get();
        }
        else throw new IllegalArgumentException("The transmission with this " + id + " does not exist");
    }
    public void deleteTransmission(Integer id ){
        Optional<Transmission> delete = transmissionRepo.findById(id);
        if (delete.isPresent()) {
            transmissionRepo.deleteById(id);
        }
        else throw new IllegalArgumentException("The transmission with this " + id + " does not exist");
    }
}
