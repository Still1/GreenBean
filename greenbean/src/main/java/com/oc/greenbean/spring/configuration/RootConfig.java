package com.oc.greenbean.spring.configuration;

import com.oc.greenbean.mybatis.mapper.UserMapper;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
@PropertySource("classpath:properties/database.properties")
@EnableTransactionManagement
@EnableAspectJAutoProxy
@EnableCaching
@ComponentScan(basePackages = "com.oc",
    includeFilters = {
        @ComponentScan.Filter(type = FilterType.ASPECTJ, pattern = "com.oc..service..*"),
        @ComponentScan.Filter(type = FilterType.ASPECTJ, pattern = "com.oc..component..*"),
        @ComponentScan.Filter(type = FilterType.ASPECTJ, pattern = "com.oc..repository..*")
    })
public class RootConfig {

    @Value("#{${greenbean.db}}")
    private Map<String, String> databaseProperties;

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(databaseProperties.get("driverClassName"));
        dataSource.setUrl(databaseProperties.get("url"));
        dataSource.setUsername(databaseProperties.get("username"));
        dataSource.setPassword(databaseProperties.get("password"));
        return dataSource;
    }

    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager() {
        DataSourceTransactionManager manager = new DataSourceTransactionManager();
        manager.setDataSource(dataSource());
        return manager;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        org.apache.ibatis.session.Configuration myBatisConfig = new org.apache.ibatis.session.Configuration();
        myBatisConfig.setLazyLoadingEnabled(true);
        myBatisConfig.setAggressiveLazyLoading(false);
        myBatisConfig.setCacheEnabled(true);
        myBatisConfig.addMappers("com.oc.greenbean.mybatis.mapper");
        sqlSessionFactoryBean.setConfiguration(myBatisConfig);
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public UserMapper userMapper() throws Exception {
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
        return sqlSessionTemplate.getMapper(UserMapper.class);
    }

    @Bean
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
        EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        ehCacheManagerFactoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
        ehCacheManagerFactoryBean.setShared(true);
        return ehCacheManagerFactoryBean;
    }

    @Bean
    public EhCacheCacheManager ehCacheCacheManager() {
        EhCacheCacheManager ehCacheCacheManager = new EhCacheCacheManager(ehCacheManagerFactoryBean().getObject());
        return ehCacheCacheManager;
    }
}
