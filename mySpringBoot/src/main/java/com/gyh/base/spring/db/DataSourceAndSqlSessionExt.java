package com.gyh.base.spring.db;

import com.alibaba.druid.pool.DruidDataSource;
import com.gyh.base.GyhConstant;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author guoyanhong
 * @date 2018/9/17 16:32
 */
@Component
public class DataSourceAndSqlSessionExt {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;
    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager;
    @Autowired
    private DataSource mainDataSource;

    /**
     * 重新定义一个 DataSource，并修改 dataSourceTransactionManager 和 SqlSession 里的dataSource对象，
     * 实验表明，保存数据时使用的DataSource为 SqlSession 里的dataSource，无论 dataSourceTransactionManager 的DataSource 改变与否，sql操作的数据库为 SqlSession 里的数据库连接;
     * 并且 dataSourceTransactionManager 和 SqlSession 的DataSource不一致时，dataSourceTransactionManager 管理的事务将不能使 SqlSession 里的 sql 回滚，
     * 所以现实代码中不要将这两处的DataSource设置成不一致，避免造成错误和混乱
     * 另：多数据源时，需要重写这两个地方的数据源，因为多数据源可能需要从主数据库中查询其他数据源连接信息，再定义成多数据源
     *
     * @throws Exception
     */
    @PostConstruct
    public void resetDataSource() throws Exception {
        DataSource newDataSource = getDataSource();
        // 重写 dataSourceTransactionManager 的 dataSource
        dataSourceTransactionManager.setDataSource(newDataSource);
        // 重写 sqlSession  的 dataSource
        Configuration configuration = sqlSessionTemplate.getConfiguration();
        Environment environment = configuration.getEnvironment();
        Environment newEnviroment = new Environment(environment.getId(), environment.getTransactionFactory(), newDataSource);
        configuration.setEnvironment(newEnviroment);
        // 为 sqlSession 添加拦截器
        // configuration.addInterceptor(interceptor);
    }

    /**
     * 获取动态数据源
     * @return
     */
    public DynamicDataSource getDataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(GyhConstant.MAIN_DB_ID,mainDataSource);
        // TODO 其他数据库连接信息，可以动态从主库某张表中获取，待完善数据库配置信息（连接池信息等）
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl("jdbc:mysql://localhost:3306/sys?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true&useAffectedRows=true&zeroDateTimeBehavior=convertToNull&useSSL=true");
        datasource.setDriverClassName("com.mysql.jdbc.Driver");
        datasource.setUsername("root");
        datasource.setPassword("root");
        targetDataSources.put("sys",datasource);
        dynamicDataSource.setTargetDataSources(targetDataSources);
        dynamicDataSource.setDefaultTargetDataSource(mainDataSource);
        dynamicDataSource.afterPropertiesSet();
        return dynamicDataSource;
    }

}
