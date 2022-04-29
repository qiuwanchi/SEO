package cn.qiuwanchi.cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@ServletComponentScan
@EnableScheduling //开启定时任务
@EnableTransactionManagement //开启注解事务管理
@MapperScan("cn.qiuwanchi.cms.dao")
public class SeoCmsApplication {

	public static ConfigurableApplicationContext ac;

	public static void main(String[] args) {
		ac = SpringApplication.run(SeoCmsApplication.class, args);
	}
	
}
