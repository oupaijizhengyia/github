package com.tangu.tanguStudy.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * FileName: MainTest
 * Author: yeyang
 * Date: 2019/8/29 15:37
 */

public class MainTest {
    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = new FileInputStream("E:\\Work\\nw\\tangu-study\\src\\main\\resources\\mybatis.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(stream);
        SqlSession session = factory.openSession();
        session.selectOne("sss");
    }

}
