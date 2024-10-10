package br.com.stapassoli.spring_batch.dbTwo_entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

//@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Visitors {

    //@Id
    private String id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String phoneNumber;
    private String address;
    //private Date visitDate;

    //@Transient
    private String strVisitDate;

}
