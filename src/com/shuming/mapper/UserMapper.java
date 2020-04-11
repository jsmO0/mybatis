package com.shuming.mapper;

import com.shuming.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * mapper接口注意:
 * 1.接口的名字要和mapper.xml的namespace保持一致
 * 2.接口里面的方法名字要和mapper.xml中的id属性一直
 * 3.parameterType和resultType就是方法的返回类型和参数名
 * 4.mapper.xml和mapper接口一般应在一个包下面
 */
public interface UserMapper {
    /**
     * 查询单个对象
     * @param id
     * @return
     */
    User get(int id);

    /**
     * 查询多个对象
     * @return
     */
    List<User> listAll();

    /**
     * 更新用户信息
     * @param user
     */
    void update(User user);

    /**
     * 插入数据
     * @param user
     */
    void save( User user);

    /**
     * 查询薪资待遇大于minSalary小于maxSalary的人的信息
     * @param minSalary
     * @return
     */
    List<User> conditionQuery(@Param("minSalary") Integer minSalary ,
                              @Param("maxSalary") Integer maxSalary);

    /**
     * 根据名字来查询用户信息，如果名字为空，则返回所有人的信息
     * @param name
     * @return
     */
    List<User> nameConditionQuery(@Param("name") String name);

    /**
     * 使用foreach批量插入
     * @param userList
     */
    void batchSave(@Param("userList") List<User> userList);


}
