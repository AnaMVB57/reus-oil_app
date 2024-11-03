package com.reusoil.app.filter;

import com.reusoil.app.services.persona.PersonaService;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {


//    @Bean
    public FilterRegistrationBean<PersonaFilter> configurarFiltros(PersonaService personaService){
        FilterRegistrationBean<PersonaFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new PersonaFilter(personaService));
        registrationBean.addUrlPatterns("*"); // El patrón que quieres filtrar
        return registrationBean;
    }

}
