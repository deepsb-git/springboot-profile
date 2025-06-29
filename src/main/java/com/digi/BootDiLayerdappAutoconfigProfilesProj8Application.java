package com.digi;

import com.digi.controller.MainController;
import com.digi.vo.EmployeeVO;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

import java.beans.PropertyVetoException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class BootDiLayerdappAutoconfigProfilesProj8Application {

	@Autowired
	private Environment env;

	@Bean(name="cds")
	@Profile("uat")
	public ComboPooledDataSource createC3PODs() throws Exception {
		System.out.println("Creating C3P0 DataSource");
		ComboPooledDataSource cds = new ComboPooledDataSource();
		cds.setDriverClass(env.getRequiredProperty("spring.datasource.driver-class-name"));
		cds.setJdbcUrl(env.getRequiredProperty("spring.datasource.url"));
		cds.setUser(env.getRequiredProperty("spring.datasource.username"));
		cds.setPassword(env.getRequiredProperty("spring.datasource.password"));
		return cds;

	}

	public static void main(String[] args) {

		System.out.println("Main method init");
		//read inputs from enduser
		Scanner sc=new Scanner(System.in);
		System.out.println("Desgs count::");
		int count=sc.nextInt();
		String[] desgs=null;
		if(count>=1)
			desgs=new String[count];

		else {
			System.out.println("Invalid desg count");
			return;
		}

		for (int i=0;i<count;++i){
			System.out.println("enter desg"+ (i+1));
			String desg=sc.next();
			desgs[i]=desg;
		}

		//get IOC container
		ApplicationContext ctx=SpringApplication.run(BootDiLayerdappAutoconfigProfilesProj8Application.class, args);

		//get Controller class object
		MainController controller = ctx.getBean("controller", MainController.class);

		//invoke b.method

		try{
			List<EmployeeVO> listVO =controller.showEmpsByDesgs(desgs);
			System.out.println("Emp details having " + Arrays.toString(desgs));
			listVO.forEach(vo->{
				System.out.println(vo);
			});
		}//try
		catch (Exception e){
			e.printStackTrace();
			System.out.println("Some Internal problem::" + e.getMessage());
		}
		//close IOC container
		((org.springframework.context.ConfigurableApplicationContext) ctx).close();

	}

}
