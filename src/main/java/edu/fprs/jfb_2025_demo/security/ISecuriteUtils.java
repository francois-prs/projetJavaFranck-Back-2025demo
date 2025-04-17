package edu.fprs.jfb_2025_demo.security;

public interface ISecuriteUtils {
    String getRole(AppUserDetails userDetails);

    String generateToken(AppUserDetails userDetails);

    String getSubjectFromJwt(String jwt);
}
