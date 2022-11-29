package com.cg.model;

import com.cg.model.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@Accessors(chain = true)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;


    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column
    private String status;


    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;



    public UserDTO toUserDTO() {
        return new UserDTO()
                .setId(id)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setUsername(username)
                .setPassword(password)
                .setRole(role.toRoleDTO())
                .setStatus(status);
    }

}
