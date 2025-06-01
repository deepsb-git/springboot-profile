package com.digi.dao;

import com.digi.bo.EmployeeBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository("empDAO")
@Profile({"dev","test"})
public class MySQLEmployeeDAOImpl implements IEmployeeDAO{

    private static final String GET_EMPS_BY_DEGS="SELECT EMPNO,ENAME,JOB,SAL,DEPTNO,MGR FROM EMP WHERE JOB IN";
    @Autowired
    private DataSource ds;

    public MySQLEmployeeDAOImpl() {
        System.out.println("MySQLEmployeeDAOImpl :: 0-param constructor");
    }

    @Override
    public List<EmployeeBO> getEmpsByDesg(String cond) throws Exception {
        List<EmployeeBO> listBO=null;
        System.out.println("DataSource obj class name::"+ds.getClass());
        //input is dynamic so used simple statement
        try(//get pooled Jdbc connection
                Connection con= ds.getConnection();
                //Create Statement Object
            Statement st=con.createStatement(); //PreparedStatement can not be used here because number of designation are dynamic value
            //send and execute sql query in db
            ResultSet rs=st.executeQuery(GET_EMPS_BY_DEGS+cond+" ORDER BY JOB");
        ){
            //convert rs object records to List  of BO Object
            listBO=new ArrayList<>();
            EmployeeBO bo=null;
            while(rs.next()){
                //copy each record of rs to a object of EmployeeBO class
                bo=new EmployeeBO();
                bo.setEmpNo(rs.getInt(1));
                bo.setEname(rs.getNString(2));
                bo.setJob(rs.getString(3));
                bo.setSal(rs.getDouble(4));
                bo.setDeptNo(rs.getInt(5));
                bo.setMgrNo(rs.getInt(6));
                //add each object of EmployeeBO to List
                listBO.add(bo);
            }//while

        }//try
        catch (SQLException se){
            se.printStackTrace();
            throw se;//propagate to next layer till client for exception propagation
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return listBO;
    }//method
}//class
