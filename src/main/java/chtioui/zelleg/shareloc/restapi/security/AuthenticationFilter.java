package chtioui.zelleg.shareloc.restapi.security;

import java.io.IOException;
import java.security.Principal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import jakarta.ws.rs.ext.Provider;


@Secured
@Provider
@Priority(Priorities.AUTHORIZATION)
public class AuthenticationFilter implements ContainerRequestFilter {

    Connection con;
    private static final String REALM = "example";
    private static final String AUTHENTICATION_SCHEME = "Bearer";
  
    public AuthenticationFilter() {
        
        try {
            String url =String.format("jdbc:mysql://localhost:3306/Shareloc?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
            String uname ="root";
            String pwd = "";
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url,uname,pwd);
            
        } catch(Exception e) {
            System.out.println(e +"data insert unsuccess.");
        }
    }

    public void filter(ContainerRequestContext requestContext) throws IOException {
        
        
        // Get the Authorization header from the request
        String authorizationHeader =
                requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        // Validate the Authorization header
        if (!isTokenBasedAuthentication(authorizationHeader)) {
            abortWithUnauthorized(requestContext);
            return;
        }

        // Extract the token from the Authorization header
        final String token = authorizationHeader
                            .substring(AUTHENTICATION_SCHEME.length()).trim();

        try {

            // Validate the token
            System.out.println("token"+token);
            validateToken(token);

        } catch (Exception e) {
            abortWithUnauthorized(requestContext);
        }
        final SecurityContext currentSecurityContext = requestContext.getSecurityContext();
        requestContext.setSecurityContext(new SecurityContext() {

                public Principal getUserPrincipal() {
                    String select = "select * from user where token='"+token+"'";
                    PreparedStatement ps=null;
                    try {
                        ps = con.prepareStatement(select);
                    } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    ResultSet rs=null;
                    try {
                        rs = ps.executeQuery();
                    } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    try {
                        if(rs.next()) {
                            System.out.println(rs);
                            final String name= rs.getString("iduser");
                                return ()->name;
                            }
                    } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    return null;
                }

            public boolean isUserInRole(String role) {
                return true;
            }

            public boolean isSecure() {
                return currentSecurityContext.isSecure();
            }

            public String getAuthenticationScheme() {
                return AUTHENTICATION_SCHEME;
            }
        });
        
    }

    private boolean isTokenBasedAuthentication(String authorizationHeader) {

        // Check if the Authorization header is valid
        // It must not be null and must be prefixed with "Bearer" plus a whitespace
        // The authentication scheme comparison must be case-insensitive
        System.out.println("je suis ici");
        return authorizationHeader != null && authorizationHeader.toLowerCase()
                    .startsWith(AUTHENTICATION_SCHEME.toLowerCase() + " ");
    }

    private void abortWithUnauthorized(ContainerRequestContext requestContext) {

        // Abort the filter chain with a 401 status code response
        // The WWW-Authenticate header is sent along with the response
        requestContext.abortWith(
                Response.status(Response.Status.UNAUTHORIZED)
                        .header(HttpHeaders.WWW_AUTHENTICATE, 
                                AUTHENTICATION_SCHEME + " realm=\"" + REALM + "\"")
                        .build());
    }
    public void validateToken(String token) throws Exception {
        // Check if the token was issued by the server and if it's not expired
        // Throw an Exception if the token is invalid
        
        String select = "select * from user where token='"+token+"'";
        PreparedStatement ps = con.prepareStatement(select);
        ResultSet rs = ps.executeQuery();
        if(rs.next()) {
            System.out.println("token validate"+token);
                
            }
            else {
                System.out.println("je suis ici");
                throw new TokenInvalidateException("token not validated");
            
        }
            
        }

    public class TokenInvalidateException extends Exception{
        public TokenInvalidateException(String s){
            super(s);
        }
        
    }

   
}
