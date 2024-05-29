package br.com.quattys.backend.domain.enums;

public enum RolesEnums {

    BASIC("BASIC"),
    ADMIN("ADMIN"),
    SUPER_USER("SUPER_USER");

    private final String roleName;

    RolesEnums(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public static RolesEnums getRoleName(String roleName) {
        for (RolesEnums role : RolesEnums.values()) {
            if (role.getRoleName().equalsIgnoreCase(roleName)) {
                return role;
            }

        }
        throw new IllegalArgumentException("Invalid Role");
    }

}
