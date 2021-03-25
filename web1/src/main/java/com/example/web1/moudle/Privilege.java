package com.example.web1.moudle;

public class Privilege {
    private String privilege_id;
    private String privilege_name;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrivilege_name() {
        return privilege_name;
    }

    public void setPrivilege_name(String privilege_name) {
        this.privilege_name = privilege_name;
    }

    public String getPrivilege_id() {
        return privilege_id;
    }

    public void setPrivilege_id(String privilege_id) {
        this.privilege_id = privilege_id;
    }

    public static final class PrivilegeBuilder {
        private String privilege_id;
        private String privilege_name;
        private String description;

        private PrivilegeBuilder() {
        }

        public static PrivilegeBuilder aPrivilege() {
            return new PrivilegeBuilder();
        }

        public PrivilegeBuilder withPrivilege_id(String privilege_id) {
            this.privilege_id = privilege_id;
            return this;
        }

        public PrivilegeBuilder withPrivilege_name(String privilege_name) {
            this.privilege_name = privilege_name;
            return this;
        }

        public PrivilegeBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Privilege build() {
            Privilege privilege = new Privilege();
            privilege.setPrivilege_id(privilege_id);
            privilege.setPrivilege_name(privilege_name);
            privilege.setDescription(description);
            return privilege;
        }
    }

    @Override
    public String toString() {
        return "Privilege{" +
                "privilege_id='" + privilege_id + '\'' +
                ", privilege_name='" + privilege_name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
