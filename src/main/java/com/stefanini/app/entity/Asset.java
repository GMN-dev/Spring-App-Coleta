package com.stefanini.app.entity;
import com.stefanini.app.utils.AssetType;
import jakarta.persistence.*;
import com.stefanini.app.utils.Status;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Asset {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @Column
    private String heritage;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AssetType assetType;

    @Enumerated(EnumType.STRING)
    @Column
    private Status status = Status.PENDENTE;

    @Column
    private  LocalDate pickupDate;

    @Column
    private LocalDate schedulatedDate = LocalDate.now();

    @Column
    private Integer pendingDays = 0;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    public Asset(String heritage, AssetType type, String name, String email) {
        this.heritage = heritage;
        this.assetType = type;
        this.employee = new Employee(name, email);
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

    public LocalDate getPickupDate() {
        return pickupDate;
    }
    public void setPickupDate(LocalDate pickupDate) {
        this.pickupDate = pickupDate;
    }

    public LocalDate getSchedulatedDate() {return schedulatedDate;}
    public void setSchedulatedDate(LocalDate schedulatedDate) {this.schedulatedDate = schedulatedDate;}

    public Integer getPendingDays(){
        return this.pendingDays;
    }
    public void setPendingDays(Integer pendingDays){
        this.pendingDays = pendingDays;
    }

    public AssetType getAssetType() { return assetType;}
    public void setAssetType(AssetType assetType) { this.assetType = assetType; }

    public Employee getEmployee() {return employee; }

    public UUID getId() { return id; }

}
