package com.tom.demo.mybatis

import org.apache.ibatis.io.Resources
import org.apache.ibatis.session.SqlSessionFactory
import org.apache.ibatis.session.SqlSessionFactoryBuilder

/**
 * Created by tom on 15/12/24.
 */
class MybatisDemo {
    public static void main(String[] args){
        def source="/mybatis-config.xml"
        InputStream inputStream = Resources.getResourceAsStream(source)
        SqlSessionFactory sessionFactory = SqlSessionFactoryBuilder.build(inputStream)


    }


}
