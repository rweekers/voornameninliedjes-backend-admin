package org.orangeflamingo.namesandsongs.springconfig;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.orangeflamingo.namesandsongs.domain.Song;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
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

	@Bean(name = "jedisConnFactory")
	public JedisConnectionFactory jedisConnFactory() {
		Properties props = dbProperties();
		JedisConnectionFactory jedisConnFactory = new JedisConnectionFactory();
		jedisConnFactory.setUsePool(true);
		LOGGER.info("Setting redis password "
				+ props.getProperty("redis_password"));
		jedisConnFactory.setPassword(props.getProperty("redis_password"));
		return jedisConnFactory;
	}

	@Bean(name = "stringRedisSerializer")
	public StringRedisSerializer redisSerializer() {
		return new StringRedisSerializer();
	}
	
	@Bean(name = "jdkSerializer")
	public JdkSerializationRedisSerializer jdkSerializer() {
		return new JdkSerializationRedisSerializer();
	}
	
	@Bean(name = "jacksonSerializer")
	public Jackson2JsonRedisSerializer<Song> jacksonSerializer() {
		// JacksonJsonRedisSerializer<Song> j = new JacksonJsonRedisSerializer<Song>(Song.class);
		// JacksonJsonRedisSerializer<Song> jsonSerializer = new JacksonJsonRedisSerializer<Song>(Song.class);
		Jackson2JsonRedisSerializer<Song> jackson2JsonSerializer = new Jackson2JsonRedisSerializer<Song>(Song.class);
		return jackson2JsonSerializer;
	}

	@Bean(name = "redisTemplate")
	public RedisTemplate<String, Object> redisTemplate() {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
		LOGGER.info("Creating RedisTemplate with connectionFactory on port "
				+ jedisConnFactory().getPort());
		redisTemplate.setConnectionFactory(jedisConnFactory());
		// redisTemplate.setDefaultSerializer(redisSerializer());
		redisTemplate.setKeySerializer(redisSerializer());
		redisTemplate.setValueSerializer(jacksonSerializer());
		return redisTemplate;
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