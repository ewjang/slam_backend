package org.slam.slam_backend.config;

import lombok.extern.log4j.Log4j2;
import org.slam.slam_backend.controller.formatter.LocalDateFormatter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Log4j2
public class CustomServletConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        log.info("...................... addFormatters");
        registry.addFormatter(new LocalDateFormatter());

    }
}
