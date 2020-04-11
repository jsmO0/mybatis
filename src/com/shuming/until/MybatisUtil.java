package com.shuming.until;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.imageio.IIOException;
import java.io.IOException;
import java.io.InputStream;

public class MybatisUtil {
    //整个应用只需要一个sqlSessionFactory对象
    private static SqlSessionFactory sqlSessionFactory = null;
    static {
        try {

            sqlSessionFactory = new SqlSessionFactoryBuilder().
                    build(Resources.getResourceAsStream("mybatis-config.xml"));
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
    //获取SqlSession对象，SqlSession是线程不安全的，所以应该每次事务都需要一个SqlSession对象
     public static SqlSession getSqlSession(){
            return sqlSessionFactory.openSession();
        }

}
