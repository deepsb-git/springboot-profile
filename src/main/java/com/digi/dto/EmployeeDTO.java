package com.digi.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class EmployeeDTO implements Serializable {
    //always take wrapper types because it holds null but simple type holds zero

    private Integer srNo;
    private Integer empNo;
    private String ename;
    private String job;
    private Double Sal;
    private Integer deptNo;
    private Integer mgrNo;
}
