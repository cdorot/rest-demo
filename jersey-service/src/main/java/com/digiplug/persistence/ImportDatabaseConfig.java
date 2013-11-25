package com.digiplug.persistence;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

@Configuration
public class ImportDatabaseConfig {

	static final String DATASET_LOCATION = "classpath:com/digiplug/persistence/dataset.sql";

	@Resource
	DataSource dataSource;

	@Bean
	public DataSourceInitializer dataSourceInitializer() {
		DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();

		dataSourceInitializer.setDataSource(dataSource);
		dataSourceInitializer.setDatabasePopulator(databasePopulator());

		if (!this.isUserTableEmpty()) {
			dataSourceInitializer.setEnabled(false);
		}

		return dataSourceInitializer;
	}

	@Bean
	public DatabasePopulator databasePopulator() {
		ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
		databasePopulator.addScript(resourceLoader().getResource(DATASET_LOCATION));
		return databasePopulator;
	}

	@Bean
	public ResourceLoader resourceLoader() {
		return new DefaultResourceLoader();
	}

	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource);
	}

	private boolean isUserTableEmpty() {
		return this.jdbcTemplate().queryForObject("select count(*) from users", Integer.class) == 0;
	}

}
