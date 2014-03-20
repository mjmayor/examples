@Configuration
@Import(AppConfig.class)
@ImportResource("classpath:properties.xml") //Si es necesario
@EnableTransactionManagement
public class PersistenceConfig {

	@Value("${database.driver}")					private String driverClassName;
	@Value("${database.multisignal.url}")			private String databaseUrl;
	@Value("${database.multisignal.user}")			private String username;
	@Value("${database.multisignal.password}")		private String password;
	@Value("${database.multisignal.generateDDL}")	private String generateDDL;

	@Value("${database.hibernate.dialect}")			private String hibernateDialect;
	@Value("${database.format_sql}")				private String hibernateShowSql;
	@Value("${database.hbm2ddl.auto}")				private String databaseHbm2ddlAuto;


	@Bean
	public InternalResourceViewResolver irResolver() {
		InternalResourceViewResolver ir = new InternalResourceViewResolver();
		ir.setPrefix("/WEB-INF/jsp/");
		ir.setSuffix(".jsp");
		return ir;
	}

	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource resource = new ReloadableResourceBundleMessageSource();
		resource.setBasename("ruta de los properties de mensajes");
		resource.setCacheSeconds(86400);
		return resource;
	}

	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver cm = new CommonsMultipartResolver();
		cm.setMaxUploadSize(250000);
		cm.setResolveLazily(true);
		return cm;
	}

	private DataSource getDataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName(driverClassName);
		ds.setUrl(databaseUrl);
		ds.setUsername(username);
		ds.setPassword(password);
		return ds;
	}

	private HibernateJpaVendorAdapter hibernateJpaVendorAdapter() {
		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		hibernateJpaVendorAdapter.setShowSql(Boolean.parseBoolean(hibernateShowSql));
		hibernateJpaVendorAdapter.setGenerateDdl(Boolean.parseBoolean(generateDDL));
		hibernateJpaVendorAdapter.setDatabasePlatform(hibernateDialect);
		return hibernateJpaVendorAdapter;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		final LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(getDataSource());
		entityManagerFactoryBean.setPackagesToScan("org.code.app.package");
		entityManagerFactoryBean.setJpaVendorAdapter(hibernateJpaVendorAdapter());
		entityManagerFactoryBean.setPersistenceUnitName("transactionManager");
		return entityManagerFactoryBean;
	}

	@Bean
	public JpaTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		transactionManager.setDataSource(getDataSource());
		return transactionManager;

	}

	//Definicion de beans
}