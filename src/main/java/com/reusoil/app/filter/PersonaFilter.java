package com.reusoil.app.filter;

import com.reusoil.app.models.persona.PersonaEntity;
import com.reusoil.app.services.persona.PersonaService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class PersonaFilter implements Filter {

    private final PersonaService personaService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        //Acá casteamos el request
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        if (requestURI.equals("/empresa/guardar") || mostrarEstilos(requestURI)) {
            filterChain.doFilter(request, response);
            return;
        }

        HttpServletResponse httpResponse = (HttpServletResponse) response;
        //Acá recuperamos la sesión sin crear una nueva
        HttpSession session = httpRequest.getSession(false);

        Long usuarioId = (Long) session.getAttribute("usuarioId");
        Optional<PersonaEntity> persona = personaService.obtenerPersonaPorUsuarioId(usuarioId);

        if (persona.isPresent() && !httpRequest.getRequestURI().equals("/empresa/guardar-empresa")){
            if (persona.get().getEmpresa() == null){
                httpResponse.sendRedirect("/empresa/guardar-empresa");
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    public boolean mostrarEstilos(String requestURI) {
        List<String> lista = List.of("/css/", "/js/", "/img/", "/static/", "/empresa/guardar");
        return lista.stream().anyMatch(requestURI::startsWith);
    }
}

