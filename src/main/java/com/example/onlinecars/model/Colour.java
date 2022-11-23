package com.example.onlinecars.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Colour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "Colour name is mandatory")
    @Column(unique = true,nullable = false)
    private String colourName;
    @Column(updatable = false, nullable = false)
    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    @Column(nullable = false)
    private Timestamp updatedAt;

}
