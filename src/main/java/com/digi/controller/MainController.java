package com.digi.controller;

import com.digi.dto.EmployeeDTO;
import com.digi.service.IEmployeeManagementService;
import com.digi.vo.EmployeeVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller("controller")
public class MainController {

    @Autowired
    private IEmployeeManagementService service;

    public MainController() {
        System.out.println("MainController:: 0-param constructor");
    }

    public List<EmployeeVO> showEmpsByDesgs(String[] desgs) throws Exception {
        //use service
        List<EmployeeDTO> listDTO=service.fetchEmployeeByDesgns(desgs);
        //Convert listDTO to listVO
        List<EmployeeVO> listVO=new ArrayList<>();
        listDTO.forEach(dto->{
            EmployeeVO vo=new EmployeeVO();
            BeanUtils.copyProperties(dto,vo);
            vo.setSrNo(String.valueOf(dto.getSrNo()));
            vo.setEmpNo(String.valueOf(dto.getEmpNo()));
            vo.setEname(String.valueOf(dto.getEname()));
            vo.setSal(String.valueOf(dto.getSal()));
            vo.setDeptNo(String.valueOf(dto.getDeptNo()));
            vo.setMgrNo(String.valueOf(dto.getMgrNo()));
            listVO.add(vo);
        });

        return listVO;
    }
}
