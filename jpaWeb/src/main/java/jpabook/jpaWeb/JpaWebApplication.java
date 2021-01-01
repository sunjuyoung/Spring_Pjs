package jpabook.jpaWeb;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JpaWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaWebApplication.class, args);
	}


	@Bean
	Hibernate5Module hibernate5Module(){
	//	Hibernate5Module hibernate5Module = new Hibernate5Module();
	//	hibernate5Module.configure(Hibernate5Module.Feature.FORCE_LAZY_LOADING,true);

		//return hibernate5Module;
		return new Hibernate5Module();
	}
}
