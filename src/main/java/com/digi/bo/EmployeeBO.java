package com.digi.bo;

import lombok.Data;

@Data
public class EmployeeBO {
    //always take wrapper types because it holds null but simple type holds zero

    private Integer empNo;
    private String ename;
    private String job;
    private Double Sal;
    private Integer deptNo;
    private Integer mgrNo;
}
