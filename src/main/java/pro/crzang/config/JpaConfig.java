package pro.crzang.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import org.springframework.util.ClassUtils;
import pro.crzang.Application;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackageClasses = Application.class)
class JpaConfig implements TransactionManagementConfigurer {

  @Value("${dataSource.driverClassName}")
  private String driver;
  @Value("${dataSource.url}")
  private String url;
  @Value("${dataSource.username}")
  private String username;
  @Value("${dataSource.password}")
  private String password;
  @Value("${hibernate.dialect}")
  private String dialect;
  @Value("${hibernate.hbm2ddl.auto}")
  private String hbm2ddlAuto;
  @Value("${hibernate.enable_lazy_load_no_trans}")
  private String enableLazyLoad;

  @Bean
  public DataSource dataSource() {
    HikariConfig config = new HikariConfig();
    config.setDriverClassName(driver);
    config.setJdbcUrl(url);
    config.setUsername(username);
    config.setPassword(password);
    config.addDataSourceProperty("cachePrepStmts", "true");
    config.addDataSourceProperty("prepStmtCacheSize", "250");
    config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
    config.addDataSourceProperty("useServerPrepStmts", "true");

    return new HikariDataSource(config);
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
    LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
    entityManagerFactoryBean.setDataSource(dataSource);

    String entities = ClassUtils.getPackageName(Application.class);
    String converters = ClassUtils.getPackageName(Jsr310JpaConverters.class);
    entityManagerFactoryBean.setPackagesToScan(entities, converters);

    entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

    Properties jpaProperties = new Properties();
    jpaProperties.put(org.hibernate.cfg.Environment.DIALECT, dialect);
    jpaProperties.put(org.hibernate.cfg.Environment.HBM2DDL_AUTO, hbm2ddlAuto);
    jpaProperties.put(Environment.ENABLE_LAZY_LOAD_NO_TRANS,enableLazyLoad);
    entityManagerFactoryBean.setJpaProperties(jpaProperties);

    return entityManagerFactoryBean;
  }

  @Bean
  public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
    return new JpaTransactionManager(entityManagerFactory);
  }

  @Override
  public PlatformTransactionManager annotationDrivenTransactionManager() {
    return new JpaTransactionManager();
  }
}
