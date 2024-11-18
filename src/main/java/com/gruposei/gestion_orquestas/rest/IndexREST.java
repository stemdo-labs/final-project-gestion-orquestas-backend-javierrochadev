package com.gruposei.gestion_orquestas.rest;

import com.gruposei.gestion_orquestas.responses.ApiRequestException;
import com.gruposei.gestion_orquestas.model.AuthenticationRequest;
import com.gruposei.gestion_orquestas.model.AuthenticationResponse;
import com.gruposei.gestion_orquestas.responses.ResponseHandler;
import com.gruposei.gestion_orquestas.service.MyUserDetailsService;
import com.gruposei.gestion_orquestas.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class IndexREST {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService  userDetailsService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private ResponseHandler responseHandler;

    @RequestMapping("/")
    public String index(){

        return "index";
    }
    // Forwards all routes to FrontEnd except: '/', '/index.html', '/api', '/api/**'
    // Required because of 'mode: history' usage in frontend routing, see README for further details
    @RequestMapping(value = "/**/{[path:[^\\.]*}")
//    @RequestMapping(value = "{_:^(?!index\\.html|api).$}")
    public String redirectApi() {
        System.out.println("URL entered directly into the Browser, so we need to redirect...");
        return "forward:/";
//        return "forward:/index.html";
//        return "index";
    }
    @RequestMapping(value = "/api/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        }
        catch(BadCredentialsException e){

            throw new ApiRequestException("001");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return responseHandler.generateResponse("000",new AuthenticationResponse(jwt,authenticationRequest.getUsername()));
        //return ResponseEntity.ok(new AuthenticationResponse(jwt,authenticationRequest.getUsername()));
    }
}
