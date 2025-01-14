package com.stefanini.app.entity;
import com.stefanini.app.utils.AssetType;
import jakarta.persistence.*;
import com.stefanini.app.utils.Status;

import java.time.LocalDate;

@Entity
public class Asset {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String heritage;

    @Enumerated(EnumType.STRING)
    @Column
    private AssetType assetType;

    @Enumerated(EnumType.STRING)
    @Column
    private Status status = Status.PENDENTE;

    @Column
    private LocalDate scheduledDate = LocalDate.now();

    @Column
    private  LocalDate pickupDate;

    @Column
    private Integer pendingDays = 0;

    //contructors
    public Asset(String heritage) {
        this.heritage = heritage;
    }
    public Asset(){}

    //getters and setters
    public String getHeritage() {
        return heritage;
    }

    public void setHeritage(String heritage) {
        this.heritage = heritage;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(LocalDate scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public LocalDate getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(LocalDate pickupDate) {
        this.pickupDate = pickupDate;
    }

}
