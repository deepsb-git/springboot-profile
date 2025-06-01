package com.digi.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class EmployeeVO{
    //VO contains only string properties

    private String srNo;
    private String empNo;
    private String ename;
    private String job;
    private String sal;
    private String deptNo;
    private String mgrNo;
}
