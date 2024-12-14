package com.reTheard.reThreard.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private UUID id;
    private String username;
    private String password;
    private String email;
    private String profilePicture;
    private String bio;
    private List<PostRequest> posts;

    public UserDTO(UUID Id, String username) {
        this.id = Id;
        this.username = username;
    }

}
