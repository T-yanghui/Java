package com.example.web1.moudle;

import javax.management.relation.Role;
import java.util.HashSet;
import java.util.Set;

public class User {
    private String user_id;
    private String user_name;
    private String password;

    private Set<Role> roles = new HashSet<>();

    public String getUser_id() {
        return user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getPassword() {
        return password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public static final class UserBuilder {
        private String user_id;
        private String user_name;
        private String password;
        private Set<Role> roles = new HashSet<>();

        private UserBuilder() {
        }

        public static UserBuilder anUser() {
            return new UserBuilder();
        }

        public UserBuilder withUser_id(String user_id) {
            this.user_id = user_id;
            return this;
        }

        public UserBuilder withUser_name(String user_name) {
            this.user_name = user_name;
            return this;
        }

        public UserBuilder withPassword(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder withRoles(Set<Role> roles) {
            this.roles = roles;
            return this;
        }

        public User build() {
            User user = new User();
            user.user_name = this.user_name;
            user.roles = this.roles;
            user.user_id = this.user_id;
            user.password = this.password;
            return user;
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id='" + user_id + '\'' +
                ", user_name='" + user_name + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }
}

