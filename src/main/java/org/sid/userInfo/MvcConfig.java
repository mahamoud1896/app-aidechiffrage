package org.sid.userInfo;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/403").setViewName("403");
		registry.addViewController("/loggin").setViewName("auth-login");
		registry.addViewController("/").setViewName("index");
        registry.addViewController("/homePage").setViewName("/pages/landing_page");
        //registry.addViewController("/editor_home").setViewName("editor_home");
	}

}
