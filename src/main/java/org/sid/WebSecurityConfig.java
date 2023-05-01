/*
 * package org.sid;
 * 
 * 
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.security.config.annotation.web.builders.HttpSecurity;
 * import org.springframework.security.config.annotation.web.configuration.
 * EnableWebSecurity; import
 * org.springframework.security.config.annotation.web.configuration.
 * WebSecurityConfigurerAdapter; import
 * org.springframework.security.oauth2.client.registration.ClientRegistration;
 * import org.springframework.security.oauth2.client.registration.
 * ClientRegistrationRepository; import
 * org.springframework.security.oauth2.client.registration.
 * InMemoryClientRegistrationRepository; import
 * org.springframework.security.oauth2.client.userinfo.OAuth2UserService; import
 * org.springframework.security.oauth2.core.AuthorizationGrantType; import
 * org.springframework.security.oauth2.core.ClientAuthenticationMethod; import
 * org.springframework.security.oauth2.core.oidc.IdTokenClaimNames;
 * 
 * @Configuration
 * 
 * @EnableWebSecurity public class WebSecurityConfig extends
 * WebSecurityConfigurerAdapter {
 * 
 * private static String CLIENT_ID =
 * "228081781783-np1ph6qspborldpcmimkvd7bnfe3tr9p.apps.googleusercontent.com";
 * private static String CLIENT_SECRET = "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";
 * private static String REDIRECT_URI =
 * "http://localhost:8082/login/oauth2/code/google";
 * 
 * @Autowired private OAuth2UserService oauth2UserService;
 * 
 * @Override protected void configure(HttpSecurity http) throws Exception { http
 * .authorizeRequests() .antMatchers("/", "/home", "/login**", "/error**")
 * .permitAll() .anyRequest() .authenticated() .and() .oauth2Login()
 * .loginPage("/login") .redirectionEndpoint() .baseUri("/login/oauth2/code/*")
 * .and() .userInfoEndpoint() .userService(oauth2UserService) .and()
 * .successHandler((request, response, authentication) -> {
 * response.sendRedirect("/user"); }); // .and() // .logout() //
 * .logoutUrl("/logout") // .logoutSuccessUrl("/loggin") // .permitAll(); }
 * 
 * @Bean public ClientRegistrationRepository clientRegistrationRepository() {
 * return new InMemoryClientRegistrationRepository(googleClientRegistration());
 * }
 * 
 * private ClientRegistration googleClientRegistration() { return
 * ClientRegistration.withRegistrationId("google") .clientId(CLIENT_ID)
 * .clientSecret(CLIENT_SECRET)
 * .clientAuthenticationMethod(ClientAuthenticationMethod.BASIC)
 * .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
 * .redirectUri(REDIRECT_URI) .scope("openid", "profile", "email")
 * .authorizationUri("https://accounts.google.com/o/oauth2/v2/auth")
 * .tokenUri("https://www.googleapis.com/oauth2/v4/token")
 * .userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo")
 * .userNameAttributeName(IdTokenClaimNames.SUB)
 * .jwkSetUri("https://www.googleapis.com/oauth2/v3/certs")
 * .clientName("Google") .build(); } }
 * 
 * 
 * 
 * // package com.chiffrageTest;
 * 
 * 
 * 
 * import java.io.IOException;
 * 
 * import javax.servlet.ServletException; import
 * javax.servlet.http.HttpServletRequest; import
 * javax.servlet.http.HttpServletResponse;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.security.authentication.dao.DaoAuthenticationProvider;
 * import
 * org.springframework.security.config.annotation.authentication.builders.
 * AuthenticationManagerBuilder; import
 * org.springframework.security.config.annotation.web.builders.HttpSecurity;
 * import
 * org.springframework.security.config.annotation.web.builders.WebSecurity;
 * import org.springframework.security.config.annotation.web.configuration.
 * WebSecurityConfigurerAdapter; import
 * org.springframework.security.core.Authentication; import
 * org.springframework.security.core.userdetails.UserDetailsService; import
 * org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
 * 
 * import
 * org.springframework.security.web.authentication.AuthenticationSuccessHandler;
 * 
 * 
 * 
 * //import com.chiffrageTest.boot.CustomOAuth2User;
 * 
 * 
 * 
 * 
 * 
 * @Configuration public class WebSecurityConfig extends
 * WebSecurityConfigurerAdapter {
 * 
 * 
 * @Bean public UserDetailsService userDetailsService() { return new
 * org.sid.userInfo.UserDetailsServiceImpl(); }
 * 
 * @Bean public BCryptPasswordEncoder passwordEncoder() { return new
 * BCryptPasswordEncoder(); }
 * 
 * @Bean public DaoAuthenticationProvider authenticationProvider() {
 * DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
 * 
 * authProvider.setPasswordEncoder(passwordEncoder());
 * authProvider.setUserDetailsService(userDetailsService());
 * 
 * return authProvider; }
 * 
 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
 * Exception { auth.authenticationProvider(authenticationProvider()); }
 * 
 * 
 * 
 * @Override public void configure(WebSecurity web) throws Exception { web
 * .ignoring() .antMatchers("/resources/**", "/static/**","/assets/**",
 * "/vendor/**","/css/**", "/js/**", "/images/**", "/fonts/**", "/scss/**"); }
 * 
 * @Override protected void configure(HttpSecurity http) throws Exception {
 * 
 * 
 * 
 * 
 * http.authorizeRequests() .antMatchers("/", "/login", "/oauth/**").permitAll()
 * .anyRequest().authenticated() .and() // .formLogin().permitAll() // .and()
 * .oauth2Login() .loginPage("/login") ;
 * 
 * 
 * 
 * 
 * http.csrf().disable().authorizeRequests(a -> a .antMatchers("/", "/signup",
 * "/oauth/**", "/loggin").permitAll() .anyRequest().authenticated()
 * ).oauth2Login().loginPage("/homePage");
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * http.authorizeRequests()
 * .antMatchers("/deleteObjectif/**").hasAnyAuthority("DG", "COMMERCIAL")
 * .antMatchers("/deleteUser/**").hasAnyAuthority("DG", "COMMERCIAL")
 * .antMatchers("/deleteRole/**").hasAnyAuthority("DG", "COMMERCIAL")
 * .antMatchers("/newObjectifAnnuel/**").hasAnyAuthority("DG", "COMMERCIAL")
 * .antMatchers("/newFP/**").hasAuthority("DG") .antMatchers("/","/loggin",
 * "/oauth/**").permitAll() .anyRequest().authenticated() .and()
 * 
 * 
 * //.formLogin().permitAll() //.loginPage("/loggin")
 * //.usernameParameter("email") //.passwordParameter("pass")
 * //.successHandler(loginSuccessHandler) //.defaultSuccessUrl("/homePage") //
 * .oauth2Login() .loginPage("/loggin") .defaultSuccessUrl("/homePage")
 * .userInfoEndpoint() .userService(oauthUserService) .and() .successHandler(new
 * AuthenticationSuccessHandler() {
 * 
 * @Override public void onAuthenticationSuccess(HttpServletRequest request,
 * HttpServletResponse response, Authentication authentication) throws
 * IOException, ServletException {
 * System.out.println("AuthenticationSuccessHandler invoked");
 * System.out.println("Authentication name: " + authentication.getName());
 * org.sid.userInfo.CustomOAuth2User oauthUser =
 * (org.sid.userInfo.CustomOAuth2User) authentication.getPrincipal();
 * 
 * userService.processOAuthPostLogin(oauthUser.getEmail());
 * 
 * response.sendRedirect("/homePage"); } }) //.defaultSuccessUrl("/list") .and()
 * .logout().logoutSuccessUrl("/loggin").permitAll() .and()
 * .exceptionHandling().accessDeniedPage("/403") ;
 * 
 * 
 * 
 * }
 * 
 * 
 * 
 * @Autowired private org.sid.userInfo.LoginSuccessHandler loginSuccessHandler;
 * 
 * @Autowired private org.sid.userInfo.CustomOAuth2UserService oauthUserService;
 * 
 * // @Autowired private
 * OAuth2UserService<OAuth2UserRequest,OAuth2User>oauthUserService;
 * 
 * @Autowired private org.sid.userInfo.UserService userService;
 * 
 * // @Autowired private UserDetailsService userService;
 * 
 * 
 * 
 * 
 * }
 * 
 */