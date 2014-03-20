@Configuration
@Import({ RepositoryConfig.class, PersistenceConfig.class })
@ComponentScan({ "org.code.app.package" })
@EnableWebMvc
public class AppConfig {

}
