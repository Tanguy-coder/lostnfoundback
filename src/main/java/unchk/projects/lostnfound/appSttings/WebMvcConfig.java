package unchk.projects.lostnfound.appSttings;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Assurez-vous que le chemin local est correctement formaté
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:C:/Users/ndao8/Downloads/projet_final/lostnfoundback/uploads/");
    }
}
