package com.shuming;

import com.shuming.domain.Department;
import com.shuming.mapper.DepartmentMapper;
import com.shuming.mapper.UserMapper;
import com.shuming.until.MybatisUtil;
import jdk.internal.util.xml.impl.Input;
import org.apache.ibatis.binding.MapperProxy;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

class HandlerDatabasesViaMybatis{

    /**
     * 查询一个对象的方法
     * @return User
     * @throws Exception
     */
    public User queryOne() throws Exception{
        //1.加载全局配置文件
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        //2.获取SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        /**
         * 3.获取SqlSession对象
         * 可以跟一个参数设置是否要自动提交事务
         */
        SqlSession sqlSession = sqlSessionFactory.openSession();
        /**
         * 4.进行查询操作
         * 查询一个对象用selectOne(),第一个参数为namespace+id找到唯一的执行SQL，第二个参数为SQL所需要传入的参数
         */
        User user = sqlSession.selectOne("com.shuming.UserMapper.get",1);
        //5关闭资源
        sqlSession.close();
        return user;
    }

    /**
     * 查询多个对象
     * @returnList<User>
     * @throws Exception
     */
    public List<User> queryList(){
        List<User> userList = null;
        SqlSession sqlSession = null;

        try {
             sqlSession = MybatisUtil.getSqlSession();
             userList = sqlSession.selectList("com.shuming.UserMapper.listAll");
        } catch (Exception e1){
            e1.printStackTrace();
        } finally {
            sqlSession.close();
        }

        return userList;

    }

    /**
     * 更新用户信息
     * @param user
     */
    public void update(User user){
        SqlSession sqlSession = null;
        int result = 0 ;

        try {
            sqlSession = MybatisUtil.getSqlSession();
             result = sqlSession.update("com.shuming.UserMapper.update",user);
             if ( result != 0) {
                 sqlSession.commit();
                 System.out.println("更新数据成功....");
             }

        } catch (Exception e1){
            e1.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

    /**
     * 插入新的用户信息
     * @param user
     */
    public void save(User user){
        SqlSession sqlSession = null;
        int result = 0 ;

        try {
            sqlSession = MybatisUtil.getSqlSession();
            result = sqlSession.insert("com.shuming.UserMapper.save",user);
            if ( result != 0) {
                sqlSession.commit();
                System.out.println("插入数据成功....");
            }

        } catch (Exception e1){
            e1.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

}

public class App {

    public static void main(String[] args) throws Exception{
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        DepartmentMapper departmentMapper =
                       sqlSession.getMapper(DepartmentMapper.class);
        UserMapper userMapper =
                         sqlSession.getMapper(UserMapper.class);
        List<Department> departmentList =
                         new ArrayList<>();
        Department department = new Department("5G测试",115);
        departmentList.add(department);
        departmentMapper.saveMany(departmentList);
        User user = new User();
        user.setName("嫦娥");
        user.setSalary(8000);
        user.setDepartments(department);
        userMapper.save(user);
        sqlSession.commit();
        sqlSession.close();
    }
}
