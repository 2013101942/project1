package com.yq.android_recruit.handle;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;

import java.io.FileNotFoundException;
import java.io.InputStream;

public abstract class BaseHandle {

    SqlSessionFactory sqlSessionFactory = null;
    @Before
    public void init() throws FileNotFoundException {
        System.out.println("走了@Before");
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("mybatis.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
    }

    @After
    public void after(){
        System.out.println("走了@After");
        //mybatis中SQLSessionFactory 没有close（）方法，去掉它的引用，让gc()回收它
        sqlSessionFactory = null;
        System.gc();
    }
}
