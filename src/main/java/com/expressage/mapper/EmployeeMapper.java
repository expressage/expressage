package com.expressage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.expressage.pojo.Employee;
@Mapper
public interface EmployeeMapper {
    int deleteByPrimaryKey(Integer eid);

    int insert(Employee record);

    int insertSelective(Employee record);
    
    Employee selectByPrimaryKey(Integer eid);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);
    
    Employee zkSelByUsername(String username);
    
    List<Employee> zkSelAll(@Param("eid")Integer eid,@Param("name")String name,@Param("enable")String enable,@Param("tid")Integer tid,@Param("num")Integer num,@Param("size")Integer size);
    
    int zkCount(@Param("eid")Integer eid,@Param("name")String name,@Param("enable")String enable,@Param("tid")Integer tid);
    
    int zkInsert(Employee employee);
    
    Employee zkSelectByKey(Integer eid);
    
    int zkUpdByKey(Employee employee);
    
    int zkSelEmployeeByAccount(String account);
    
    int zkProhibitEmpl(Integer eid);
    
    int zkUpdPassword(@Param("password")String password,@Param("eid") Integer eid);
}