package com.wrfxx.demo10.domain.value.enumurator;

public enum ApplicationUserPermissions {

PROJECT_READ("project:read"),
PROJECT_WRITE("project:write");

private final String permissions;

    ApplicationUserPermissions(String permissions) {
        this.permissions = permissions;
    }
}
