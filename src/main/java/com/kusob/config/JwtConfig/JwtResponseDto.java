package com.kusob.config.JwtConfig;


import lombok.*;

/**
 * Created by seungki on 2017-07-13.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtResponseDto {
    String jwtToken;
    String message;
}
