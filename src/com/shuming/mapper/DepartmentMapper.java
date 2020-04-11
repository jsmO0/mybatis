package com.shuming.mapper;

import com.shuming.domain.Department;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DepartmentMapper {

    //批量增加部门信息到数据表
    void saveMany(@Param("departmentList") List<Department> departmentList);
}
