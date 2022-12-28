package com.cg.controller.rest;


import com.cg.exception.DataInputException;
import com.cg.exception.EmailExistsException;
import com.cg.model.JwtResponse;
import com.cg.model.Role;
import com.cg.model.User;
import com.cg.model.dto.UserDTO;
import com.cg.service.jwt.JwtService;
import com.cg.service.role.IRoleService;
import com.cg.service.user.IUserService;
import com.cg.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthRestController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IUserService userService;

    @Autowired
    private AppUtil appUtil;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Validated @RequestBody UserDTO userDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return appUtil.mapErrorToResponse(bindingResult);
        }
        Optional<UserDTO> opUser = userService.findUserDTOByUsername(userDTO.getUsername());

        if (opUser.isPresent()) {
            throw new EmailExistsException("Email already exists");
        }

        Optional<Role> opRole = roleService.findById(userDTO.getRole().getId());

        if (!opRole.isPresent()) {
            throw new DataInputException("Invalid account role");
        }
        userDTO.setId(0L);

        try {
            userDTO.setStatus(false);
            User userDTO1 = userService.save(userDTO.toUser());

            return new ResponseEntity<>(userDTO1, HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            throw new DataInputException("Account information is not valid, please check the information again!!");

        }

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        Optional<User> userCheck = userService.findByUsername(user.getUsername());

        if (!userCheck.isPresent()) {
            throw new EmailExistsException("Account does not exist 2");
        }

        if (userCheck.get().getStatus().equals(true)) {
            throw new EmailExistsException("Account has been locked");
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtService.generateTokenLogin(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User currentUser = userService.getByUsername(user.getUsername());

        JwtResponse jwtResponse = new JwtResponse(
                jwt,
                currentUser.getId(),
                userDetails.getUsername(),
                currentUser.getUsername(),
                userDetails.getAuthorities()
        );
        ResponseCookie springCookie = ResponseCookie.from("JWT", jwt)
                .httpOnly(false)
                .secure(false)
                .path("/")
                .maxAge(60 * 1000)
                .domain("localhost")
                .build();

        System.out.println(jwtResponse);

        return ResponseEntity
                .ok()
                .header(HttpHeaders.SET_COOKIE, springCookie.toString())
                .body(jwtResponse);


    }


}
