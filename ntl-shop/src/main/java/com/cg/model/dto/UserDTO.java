package com.cg.model.dto;

import com.cg.model.Role;
import com.cg.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.Valid;
import javax.validation.constraints.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UserDTO {

    private Long id;


    @NotBlank(message = "The first name is required")
    @Pattern(regexp = "\\b([A-ZÀ-ÿ][-,a-z. ']+[ ]*)+", message = "First name contains only letters")
    private String firstName;

    @NotBlank(message = "The last name is required")
    @Pattern(regexp = "\\b([A-ZÀ-ÿ][-,a-z. ']+[ ]*)+", message = "Last name contains only letters")
    private String lastName;


    @NotBlank(message = "The email is required")
    @Email(message = "The email address is invalid")
    private String username;

    @NotBlank(message = "The password is required")
//    @Size(max = 30, message = "Maximum password length 30 characters")
    private String password;


    @Valid
    private RoleDTO role;

    private Boolean status;

    public UserDTO(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public UserDTO(Long id, String firstName,String password,String lastName, String username, Role role,Boolean status) {
        this.id = id;
        this.firstName = firstName;
        this.password = password;
        this.lastName = lastName;
        this.username = username;
        this.role = role.toRoleDTO();
        this.status = status;
    }

    public User toUser() {
        return new User()
                .setId(id)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setUsername(username)
                .setPassword(password)
                .setRole(role.toRole())
                .setStatus(status);
    }

}
