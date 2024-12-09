package com.example.hotel.payload;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse {

    private String msg;
    private Boolean success;
    private HttpStatus httpStatus;
}
