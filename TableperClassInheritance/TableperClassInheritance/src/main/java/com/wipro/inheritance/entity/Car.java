package com.wipro.inheritance.entity;


import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
@Entity
public class Car extends Vehicle{
	private int numberOfDoors;
	 public Car(String name, int numberOfDoors) {
	        super(name);
	        this.numberOfDoors = numberOfDoors;
	    }
}
