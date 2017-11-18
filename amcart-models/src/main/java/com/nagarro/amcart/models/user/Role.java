package com.nagarro.amcart.models.user;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.nagarro.amcart.models.AbstractEntity;

@Entity
@Table(name = "role")
public class Role extends AbstractEntity {

    private String roleName;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    /**
     * This is default constructor
     */
    public Role() {
    }

    /**
     * This is parameterized constructor.
     * 
     * @param userRoleId
     *            user's role ID
     * @param user
     *            the user
     * @param role
     *            user's role
     */
    public Role(Long userRoleId, String roleName) {
        this.id = userRoleId;
        this.roleName = roleName;
    }

    /**
     * This API get user's role.
     *
     * @return the role
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * This method set user's role
     * 
     * @param roleName
     *            user's role
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
