package easyStockUtilities;


import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {

	Properties pro;
	
	public ReadConfig()
	{
		File src = new File("./Configuration/config.properties");

		try {
			FileInputStream fis = new FileInputStream(src);
			pro = new Properties();
			pro.load(fis);
		} catch (Exception e) {
			System.out.println("Exception is " + e.getMessage());
		}
	}
	
	public String getIPAdress()
	{
		String IPAdress=pro.getProperty("IPAdress");
		return IPAdress;
	}
	public String getport()
	{
		String port=pro.getProperty("port");
		return port;
	}
	
	public String getUsername()
	{
	String username=pro.getProperty("username");
	return username;
	}
	
	public String getPassword()
	{
	String password=pro.getProperty("password");
	return password;
	}
	
	public String getBranchName()
	{
	String branchnm=pro.getProperty("branchnm");
	return branchnm;
	}
	
	public String getUser()
	{
	String user=pro.getProperty("user");
	return user;
	}
	
	public String getUsernumber()
	{
	String usernumber=pro.getProperty("usernumber");
	return usernumber;
	}
	
	public String getfilterDate()
	{
	String filterdate=pro.getProperty("filterdate");
	return filterdate;
	}
	
	
	
}