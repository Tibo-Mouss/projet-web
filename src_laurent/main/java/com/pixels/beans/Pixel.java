package com.pixels.beans;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@NamedQuery(name = "Pixel.list", query = "SELECT p FROM Pixel p")
public class Pixel implements Serializable {

    @Id // clé primaire
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // prix du pixel à l'achat
    @NonNull
    @NotNull
    @Min(value = 0)
    private Integer price;

    // couleur du pixel sur la peinture
    @NonNull
    @NotBlank
    @Column(length = 6)
    @Size(min = 6, max = 6)
    private String color;

    // petit mot écrit par le user
    @NonNull
    private String description;

    // propriétaire actuel du pixel
    @ManyToOne(fetch = FetchType.EAGER)
    private User owner;

    // liste des transactions associées au pixel
    @OneToMany(mappedBy = "pixel")
    private List<Transaction> transactions;
}
