package com.digi.service;

import com.digi.dto.EmployeeDTO;

import java.util.List;

public interface IEmployeeManagementService {
    public List<EmployeeDTO> fetchEmployeeByDesgns(String[] desgs)throws Exception;
}
