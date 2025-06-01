package com.digi.dao;

import com.digi.bo.EmployeeBO;

import java.util.List;

public interface IEmployeeDAO {

    public List<EmployeeBO> getEmpsByDesg(String cond)throws Exception;
}
