@Configuration
public class RepositoryConfig {
	
	public static final String DATABASE = "database";
	
	@Bean
	public PropertyPlaceholderConfigurer getPropertyPlaceholderConfigurer() throws IOException	{
		
		Properties p = new Properties();
		FileInputStream in = new FileInputStream("fichero properties");
		p.load(in);
		in.close();
		 
		String database = p.getProperty(DATABASE);
		
		PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
		File f = new File(database);
		ppc.setLocation(new FileSystemResource(f));		
		ppc.setIgnoreUnresolvablePlaceholders(true);
		return ppc;
	}
}
