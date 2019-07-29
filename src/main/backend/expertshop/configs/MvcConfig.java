package expertshop.configs;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.MultipartConfigElement;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    /*@Value("${upload.path}")
    private String uploadPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Раздача изображений
        registry.addResourceHandler("/img/**")
                .addResourceLocations("file://" + uploadPath + "/");
        // Раздача стилей
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/"); // Поиск стилей в classpath
    }*/
    /*@Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("20000KB");
        factory.setMaxRequestSize("20000KB");
        return factory.createMultipartConfig();
    }*/
}
