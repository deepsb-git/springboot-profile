package com.digi.service;

import com.digi.bo.EmployeeBO;
import com.digi.dao.IEmployeeDAO;
import com.digi.dto.EmployeeDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("empService")
public class EmployeeMgmtServiceImpl implements IEmployeeManagementService{
    @Autowired
    private IEmployeeDAO dao;

    public EmployeeMgmtServiceImpl() {
        System.out.println("EmployeeMgmtServiceImpl::0-param constructor");
    }

    @Override
    public List<EmployeeDTO> fetchEmployeeByDesgns(String[] desgs) throws Exception {
        //String cond=null;
        //convert desgns[] into SQL IN clause string condition ('CLEARK','MANAGER','SALESMAN')
        StringBuffer buffer=new StringBuffer("(");//StringBuffer is mutable//For Web application StingBuilder because non-synchronized//Here we can choose both
        for (int i=0;i< desgs.length;++i){
            if(i==desgs.length-1)//Last element of the array
                buffer.append("'"+desgs[i]+"')");
                else
                    buffer.append("'"+desgs[i]+"',");//For other than last element


        }//for
        //Convert StringBuffer/StringBuilder obj into String
        String cond= buffer.toString();
        //Use DAO
        List<EmployeeBO> listBO = dao.getEmpsByDesg(cond);
        //Convert LISTBO to ListDTO
        List<EmployeeDTO> listDTO=new ArrayList<>();
        listBO.forEach(
                bo->{
                    EmployeeDTO dto=new EmployeeDTO();
                    //Copy each BO class object data to each DTO class obj
                    BeanUtils.copyProperties(bo,dto);//property names and types must match in both java beans
                    dto.setSrNo(listDTO.size()+1);
                    //add each DTO class to ListDTO
                    listDTO.add(dto);
                }
        );


        return listDTO;
    }//for
}
