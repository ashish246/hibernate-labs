package com.hibernate.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity  
@Table(name="TWO_WHEELER")  
//@DiscriminatorValue("Bike")  
public class TwoWheeler extends Vehicle   
{  
        @Column(name="STEERING_TYPE")  
        private String steeringTwoWheeler;  
  
 public String getSteeringTwoWheeler()  
 {  
  return steeringTwoWheeler;  
 }  
  
 public void setSteeringTwoWheeler(String steeringTwoWheeler)   
 {  
  this.steeringTwoWheeler = steeringTwoWheeler;  
 }  
}  