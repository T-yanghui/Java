package com.example.web1.moudle;

import java.util.HashSet;
import java.util.Set;

public class Role {
    private String role_id;
    private String role_name;
    private String description;

    private Set<Privilege> privileges = new HashSet<>();

    public Role(){}

    public Role(String role_name) {
        this.role_name = role_name;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public Set<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(Set<Privilege> privileges) {
        this.privileges = privileges;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public static final class RoleBuilder {
        private String role_id;
        private String role_name;
        private String description;
        private Set<Privilege> privileges = new HashSet<>();

        private RoleBuilder() {
        }

        public static RoleBuilder aRole() {
            return new RoleBuilder();
        }

        public RoleBuilder withRole_id(String role_id) {
            this.role_id = role_id;
            return this;
        }

        public RoleBuilder withRole_name(String role_name) {
            this.role_name = role_name;
            return this;
        }

        public RoleBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public RoleBuilder withPrivileges(Set<Privilege> privileges) {
            this.privileges = privileges;
            return this;
        }

        public Role build() {
            Role role = new Role();
            role.setRole_id(role_id);
            role.setRole_name(role_name);
            role.setDescription(description);
            role.setPrivileges(privileges);
            return role;
        }
    }

    @Override
    public String toString() {
        return "Role{" +
                "role_id='" + role_id + '\'' +
                ", role_name='" + role_name + '\'' +
                ", description='" + description + '\'' +
                ", privileges=" + privileges +
                '}';
    }
}
