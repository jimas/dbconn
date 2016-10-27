package com.shihuc.dbconn.sourceconfig.mysql;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
@MapperScan(basePackages = {"com.shihuc.dbconn.dao.mysql","com.shihuc.dbconn.repository.dao"})
public class MysqlMybatisConfig {
	
	@Autowired
	@Qualifier("mysqlds")
	private DataSource mysqlds;
	 @Autowired
	 private Environment env;
	@Bean
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(mysqlds);
        // 下边两句仅仅用于*.xml文件，如果整个持久层操作不需要使用到xml文件的话（只用注解就可以搞定），则不加
        sessionFactory.setTypeAliasesPackage(env.getProperty("mybatis.typeAliasesPackage"));// 指定基包
        sessionFactory.setMapperLocations(
        new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis.mapperLocations")));
        return sessionFactory.getObject();
    }
	
}
