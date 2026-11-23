package com.wipro.inheritance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.wipro.inheritance.entity.Bike;
import com.wipro.inheritance.entity.Car;
import com.wipro.inheritance.repository.VehicleRepository;

@SpringBootApplication
public class JoinedTableInheritanceApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext=SpringApplication.run(JoinedTableInheritanceApplication.class, args);
		VehicleRepository vehicleRepository=applicationContext.getBean(VehicleRepository.class);
		
		 // Create and persist a Car
       Car car = new Car();
       car.setName("Tesla");
       car.setNumberOfDoors(4);
       vehicleRepository.save(car);
       
       // Create and persist a Bike
       Bike bike = new Bike();
       bike.setName("Honda");
       bike.setHasCarrier(true);
         
       vehicleRepository.save(bike);
	}

}
