package com.example.onlinecars.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Cars {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @NotBlank(message = "City is mandatory")
    @Column(nullable = false)
    private String city;

//    @NotBlank(message = "Year is mandatory")
    @Column(nullable = false)
    private Integer year;

//    @NotBlank(message = "transmission is mandatory")
    @ManyToOne(fetch = FetchType.LAZY)
    private Transmission transmission;

    @ManyToOne(fetch = FetchType.LAZY)
//    @NotBlank(message = "Brand is mandatory")
    private Brand brand;

//    @NotBlank(message = "Car name is mandatory")
    @Column(nullable = false)
    private String carName;

//    @NotBlank(message = "Colour is mandatory")
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Colour> colours;

//    @NotBlank(message = "Price is mandatory")
    @Column(nullable = false)
    private BigDecimal price;

//    @NotBlank(message = "Description is mandatory")
    @Column(nullable = false)
    private String carDescription;
    @ManyToOne(fetch = FetchType.LAZY)
    private Users user;

    @Column(updatable = false, nullable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private Timestamp updatedAt;

}
