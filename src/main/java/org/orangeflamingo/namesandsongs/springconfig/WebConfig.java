package org.orangeflamingo.namesandsongs.springconfig;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "org.orangeflamingo.namesandsongs")
@EnableTransactionManagement
public class WebConfig extends WebMvcConfigurerAdapter {

    private static final Logger LOGGER = Logger.getLogger(WebConfig.class);

    private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "org.hibernate.dialect.PostgreSQLDialect";
    private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private static final String PROPERTY_NAME_HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
    private static final String PROPERTY_NAME_HIBERNATE_USE_SQL_COMMENTS = "hibernate.use_sql_comments";
    private static final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN = "org.orangeflamingo.namesandsongs.domain";
    private static final String PROPERTY_NAME_HIBERNATE_HBM2DDL = "hibernate.hbm2ddl.validate";

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        Properties props = dbProperties();

        dataSource.setDriverClassName(props.getProperty("driver"));
        dataSource.setUrl(props.getProperty("url"));

        dataSource.setUsername(props.getProperty("username"));
        dataSource.setPassword(props.getProperty("password"));

        return dataSource;
    }

    @Bean(name = "sessionFactory")
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean
                .setPackagesToScan(PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN);
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setHibernateProperties(hibProperties());
        return sessionFactoryBean;
    }

    private Properties hibProperties() {
        Properties properties = new Properties();
        properties.put(PROPERTY_NAME_HIBERNATE_DIALECT,
                PROPERTY_NAME_HIBERNATE_DIALECT);
        properties.put(PROPERTY_NAME_HIBERNATE_FORMAT_SQL, "true");
        properties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL, "false");
        properties.put(PROPERTY_NAME_HIBERNATE_HBM2DDL,
                PROPERTY_NAME_HIBERNATE_HBM2DDL);
        properties.put(PROPERTY_NAME_HIBERNATE_USE_SQL_COMMENTS, "true");
        return properties;
    }

    private Properties dbProperties() {
        LOGGER.info("Reading database properties...");
        // create and load default properties
        Properties props = new Properties();
        FileInputStream in;
        try {
            in = new FileInputStream("database.properties");
            props.load(in);
            in.close();
        } catch (FileNotFoundException e) {
            LOGGER.fatal("Failed to read database.properties: "
                    + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            LOGGER.fatal("Fatal IO error: " + e.getMessage());
        }
        LOGGER.info("Database properties loaded...");
        return props;
    }

    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }
}