package org.slam.slam_backend.config;

import lombok.extern.log4j.Log4j2;
import org.slam.slam_backend.controller.formatter.LocalDateFormatter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Log4j2
public class CustomServletConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        log.info("...................... addFormatters");
        registry.addFormatter(new LocalDateFormatter());
    }

    /*
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //addMapping("/**") > 어떤 경로에 CORS 설정할 것인가? (모든경로)
        //allowdOrigins("*") > 어디서부터 들어오는 경로를 허락할 것인가? (모든경로)
        registry.addMapping("/**").allowedOrigins("*").maxAge(500).allowedMethods("GET","POST","PUT","DELETE","HEAD","OPTIONS");
    }
     */
}
