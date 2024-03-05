package com.example.encore_spring_pjt.config;
import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
/*
 @Componenet() : 객체 생성과 라이프 사이클 관리를 spring container에게 위임해서 하겠다라는 의미
 Inversion of Control(IOC) : 제어의 역행
 -- IOC(Dependency Injection(DI) , Dependency Lookup(DL))
 -- @Controller
 -- @Service
 -- @Repository
 -- @
 환경설정에 관련된 객체는
 -- @Configuration
 Dependency Injection 관련 어노테이션으로
 spring container 관리하는 객체를 가져다 쓸 수 있다.
 -- AutoWired
 -- Inject
 -- Resource
 -- Qulified
 */
@Configuration
@PropertySource("classpath:/application.properties")
public class MariaDBConfig {
    //DI 구현
    @Autowired
    private ApplicationContext context ; 
    // 객체를 container에게 맡겨서 라이프사이클을 관리하라고 시키는 것
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public HikariConfig hikariConfig(){//어플리케이션 프로퍼티파일로 부터 DB정보를 읽어들임
        HikariConfig config = new HikariConfig();
        return config ;
    }
    @Bean
    public DataSource dataSource(){//히카리의 CP
        return new HikariDataSource(hikariConfig());
    }
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception{//CP를 만들어서 컨테이너에서 관리
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(dataSource());
        factory.setMapperLocations(context.getResources("classpath:/mapper/**/*Mapper.xml"));
        factory.setConfiguration(myConfiguration());
        return factory.getObject();
    }
    @Bean("encore")
    public SqlSessionTemplate sqlSession() throws Exception{//
        return new SqlSessionTemplate(sqlSessionFactory());
    }
    @Bean
    @ConfigurationProperties(prefix = "mybatis.configuration")
    public org.apache.ibatis.session.Configuration myConfiguration(){
        return new org.apache.ibatis.session.Configuration();
    }
}