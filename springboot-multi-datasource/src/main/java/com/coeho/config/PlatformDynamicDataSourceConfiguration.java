package com.coeho.config;

//import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;

import java.util.Properties;

/**
 * 动态数据源注入MyBatis、事务配置
 *
 * @author: Lomis Lu (http://blog.coeho.com)
 * @email: lomis.lu@gmail.com
 * @datetime: 2018-02-24 17:31
 */
public class PlatformDynamicDataSourceConfiguration {

    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(@Qualifier("dynamicDataSource") DynamicDataSource dataSource) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        Properties properties = new Properties();
        properties.setProperty("sqlType", "msyql");
        sqlSessionFactoryBean.setConfigurationProperties(properties);
//        sqlSessionFactoryBean.setPlugins(new Interceptor[]{new Object()});
        return sqlSessionFactoryBean;
    }

    @Bean
    public DynamicDataSourceTransactionManager transactionManager(@Qualifier("dynamicDataSource") DynamicDataSource dataSource) {
        DynamicDataSourceTransactionManager dynamicDataSourceTransactionManager = new DynamicDataSourceTransactionManager();
        dynamicDataSourceTransactionManager.setDataSource(dataSource);
        return dynamicDataSourceTransactionManager;
    }

}
