package by.bsuir.spp.controller;

import by.bsuir.spp.exceptions.DbException;
import by.bsuir.spp.payload.JwtAuthenticationResponse;
import by.bsuir.spp.payload.SignUpRequest;
import by.bsuir.spp.repositories.UserRepository;
import by.bsuir.spp.security.JwtTokenProvider;
import by.bsuir.spp.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import by.bsuir.spp.entities.Role;
import by.bsuir.spp.entities.RoleName;
import by.bsuir.spp.entities.User;
import by.bsuir.spp.exceptions.AppException;
import by.bsuir.spp.payload.ApiResponse;
import by.bsuir.spp.payload.LoginRequest;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getLoginOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(new JwtAuthenticationResponse(jwt), headers, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) throws DbException {
        if(userRepository.existsByLogin(signUpRequest.getLogin())) {
            return new ResponseEntity<>(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<>(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        User user = new User(signUpRequest.getFirstName(), signUpRequest.getLastName(), signUpRequest.getLogin(),
                signUpRequest.getEmail(), signUpRequest.getPassword());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleService.getByName(RoleName.ROLE_USER);
        if (userRole == null) {
            throw new AppException("User Role not set.");
        }

        user.setRoles(Collections.singleton(userRole));

        User result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(result.getLogin()).toUri();
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.created(location).headers(headers).body(
                new ApiResponse(true, "User registered successfully"));
    }
}
