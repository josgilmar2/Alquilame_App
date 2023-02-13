package com.salesianostriana.dam.alquilame.user.dto;

import com.salesianostriana.dam.alquilame.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
public class UserDwellingResponse {

    private String username, fullName, email, phoneNumber;
    private int numPublications;

    public static UserDwellingResponse of(User user) {
        return UserDwellingResponse.builder()
                .username(user.getUsername())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .numPublications(user.getDwellings().size())
                .build();
    }

}
