package com.kusob.config.JwtConfig;

import com.kusob.domain.member.Member;
import com.kusob.mapper.MemberMapper;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by seungki on 2017-07-12.
 */
@Slf4j
@Service
public class JwtService implements UserDetailsService{

    static final String CLAIM_KEY_EMAIL = "email";

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    @Autowired
    private MemberMapper memberMapper;

    public String createToken(UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken,String nickname){
        Map<String, Object> claims = new HashMap<>();
        Member m = memberMapper.selectByLoginEmail(usernamePasswordAuthenticationToken.getName());
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(new Date().getTime()+expiration*1000))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberMapper.selectByLoginEmail(username);

        if (member == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            JwtMember jwt1 = new JwtMember(member.getEmail(),member.getNickname(),member.getPassword(),authoritiesList("authority"));
            return jwt1;
        }
    }
    public static List<GrantedAuthority> authoritiesList(String name){
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority(name));
        return list;
    }

    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }
    public String emailFromToken(String token) {
        String email;
        try {
            final Claims claims = getClaimsFromToken(token);
            email = claims.get(CLAIM_KEY_EMAIL).toString();
        } catch (Exception e) {
            email = null;
        }
        return email;
    }
    public  Date expFromToken(String token){
        Date exp;
        try{
            final Claims claims = getClaimsFromToken(token);
            exp=claims.getExpiration();
        }catch (Exception e){
            exp=null;
        }
        return exp;
    }
    private Boolean isTokenExpired(String token) {
        final Date expiration = expFromToken(token);
        return expiration.before(new Date());
    }
    public Boolean validateToken(String token, UserDetails userDetails) {
        JwtMember member = (JwtMember) userDetails;
        final String email = emailFromToken(token);
        return (
                email.equals(member.getLoginId())
                        && !isTokenExpired(token));

    }
}
