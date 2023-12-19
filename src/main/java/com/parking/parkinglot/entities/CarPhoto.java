package com.parking.parkinglot.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class CarPhoto {
    private Long id;
    String fileName;
    String fileType;
    byte[] fileContent;
    Car car;
    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }
    @OneToOne
    public Car getCar() {
        return car;
    }
    public void setCar(Car car) {
        this.car = car;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public byte[] getFileContent() {
        return fileContent;
    }

    public void setFileContent(byte[] fileContent) {
        this.fileContent = fileContent;
    }
}
