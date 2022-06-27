package com.wrfxx.demo10.domain.value.enumurator;

import com.google.common.collect.Sets;

import java.util.Set;

import static com.wrfxx.demo10.domain.value.enumurator.ApplicationUserPermissions.*;

public enum ApplicationRoleUser {

    CUSTOMER(Sets.newHashSet()), CLIENT(Sets.newHashSet(PROJECT_READ,PROJECT_WRITE)),ADMIN(Sets.newHashSet(PROJECT_READ,PROJECT_WRITE));
    private final Set<ApplicationUserPermissions> permissions;

    ApplicationRoleUser(Set<ApplicationUserPermissions> permissions) {
        this.permissions = permissions;
    }
}
