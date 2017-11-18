package com.nagarro.amcart.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nagarro.amcart.facade.UserFacade;
import com.nagarro.amcart.facade.user.data.UserData;
import com.nagarro.amcart.models.user.User;

@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping(value = "users", method = RequestMethod.GET)
    public List<UserData> list() {
        return userFacade.list();
    }

    @RequestMapping(value = "users", method = RequestMethod.POST)
    public User create(@RequestBody User user) {
        return userFacade.saveOrUpdate(user);
    }

    @RequestMapping(value = "users/{id}", method = RequestMethod.GET)
    public User get(@PathVariable Long id) {
        return userFacade.get(id);
    }

    @RequestMapping(value = "users/{id}", method = RequestMethod.PUT)
    public User update(@PathVariable Long id, @RequestBody User user) {
        User existingUser = userFacade.get(id);
        BeanUtils.copyProperties(user, existingUser);
        return userFacade.saveOrUpdate(existingUser);
    }

    @RequestMapping(value = "users/{id}", method = RequestMethod.DELETE)
    public User delete(@PathVariable Long id) {
        User existingUser = userFacade.get(id);
        userFacade.delete(id);
        return existingUser;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void register(@RequestParam String username, @RequestParam String password, HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        userFacade.register(username, password);
        try {
            UserDetails userDetails = userFacade.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails, password, userDetails.getAuthorities());
            authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            if (usernamePasswordAuthenticationToken.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                        SecurityContextHolder.getContext());
                LOGGER.debug("Auto login for successfully!", username);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        response.sendRedirect("/");
    }

}
