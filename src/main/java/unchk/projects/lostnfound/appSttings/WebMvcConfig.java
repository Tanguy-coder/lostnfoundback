package unchk.projects.lostnfound.appSttings;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Remplace ce chemin par le chemin où les fichiers sont stockés localement
        registry.addResourceHandler("uploads/**")
                .addResourceLocations("file:/C:/Users/HP-Miray/Documents/front_ang/lostnfoundback/uploads/");
    }
}