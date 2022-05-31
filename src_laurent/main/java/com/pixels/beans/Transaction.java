package com.pixels.beans;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@NamedQuery(name = "Transaction.list", query = "SELECT t FROM Transaction t order by t.time desc")
public class Transaction implements Serializable {

    @Id // clé primaire
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // prix de la transaction
    @NonNull
    @NotNull
    private Integer price;

    // couleur du pixel de la transaction
    @NonNull
    @NotNull
    private String color;

    // date de la transaction
    @NonNull
    @NotNull
    private Date time;

    // wallet acheteur
    @ManyToOne
    private User buyer;

    // pixel acheté
    @ManyToOne
    private Pixel pixel;

    public Transaction(Date time, Pixel pixel) {
        this.time = time;
        this.pixel = pixel;
        this.price = pixel.getPrice();
        this.color = pixel.getColor();
        this.buyer = pixel.getOwner();
    }
}
