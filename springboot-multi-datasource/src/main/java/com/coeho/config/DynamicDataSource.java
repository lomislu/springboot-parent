package com.coeho.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 动态数据源路由，读写使用主库，只读使用从库
 *
 * @author: Lomis Lu (http://blog.coeho.com)
 * @email: lomis.lu@gmail.com
 * @datetime: 2018-02-24 15:41
 */
@Slf4j
public class DynamicDataSource extends AbstractRoutingDataSource {

    // 从库列表
    private List<Object> slaveDataSource = new ArrayList<Object>();

    // 轮询计数
    private AtomicInteger squence = new AtomicInteger(0);

    @Override
    public void afterPropertiesSet() {
        super.afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        Object key = "";
        if (DynamicDataSourceHolder.isMaster()) {
            key = DynamicDataSourceHolder.MASTER;
        } else {
            key = getSlaveKey();
        }
        return key;
    }

    public List<Object> getSlaveDataSource() {
        return slaveDataSource;
    }

    public void setSlaveDataSource(List<Object> slaveDataSource) {
        this.slaveDataSource = slaveDataSource;
    }

    public Object getSlaveKey() {
        if (squence.intValue() == Integer.MAX_VALUE) {
            synchronized (squence) {
                if (squence.intValue() == Integer.MAX_VALUE) {
                    squence = new AtomicInteger(0);
                }
            }
        }
        int index = squence.getAndIncrement() % slaveDataSource.size();
        return slaveDataSource.get(index);
    }
}
