package com.nagarro.amcart.models.enums;

public enum RoleType {
    ROLE_ADMIN("ROLE_ADMIN"), ROLE_CUSTOMER("ROLE_CUSTOMER"), ROLE_ANONYMOUS("ROLE_ANONYMOUS");

    private String name;

    RoleType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
