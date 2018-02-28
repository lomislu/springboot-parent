package com.coeho.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 多数据源配置，此方式省去配置文件，但灵活性不够
 *
 * @author: Lomis Lu (http://blog.coeho.com)
 * @email: lomis.lu@gmail.com
 * @datetime: 2018-02-24 15:22
 */
@Configuration
public class DataSourceConfiguration {

    @Bean(name = "masterDataSource", destroyMethod = "close", initMethod = "init")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource masterDataSource() {
        return new DruidDataSource();
    }

    @Bean(name = "slave1DataSource", destroyMethod = "close", initMethod = "init")
    @ConfigurationProperties(prefix = "spring.datasource.slave1")
    public DataSource slave1DataSource() {
        return new DruidDataSource();
    }

    @Bean(name = "slave2DataSource", destroyMethod = "close", initMethod = "init")
    @ConfigurationProperties(prefix = "spring.datasource.slave2")
    public DataSource slave2DataSource() {
        return new DruidDataSource();
    }

    public DynamicDataSource dynamicDataSource(@Qualifier("masterDataSource") DataSource masterDataSource,
                                               @Qualifier("slave1DataSource") DataSource slave1DataSource,
                                               @Qualifier("slave2DataSource") DataSource slave2DataSource) {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        Map<Object, Object> targetDataSource = new HashMap<Object, Object>();
        targetDataSource.put("master", masterDataSource);
        targetDataSource.put("slave1", slave1DataSource);
        targetDataSource.put("slave2", slave2DataSource);
        dynamicDataSource.setTargetDataSources(targetDataSource);

        dynamicDataSource.setDefaultTargetDataSource(masterDataSource);

        List<Object> slaveDataSource = new ArrayList<Object>();
        slaveDataSource.add(slave1DataSource);
        slaveDataSource.add(slave2DataSource);
        dynamicDataSource.setSlaveDataSource(slaveDataSource);


        return dynamicDataSource;
    }

}
