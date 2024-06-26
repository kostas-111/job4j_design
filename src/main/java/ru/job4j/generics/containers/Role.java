package ru.job4j.generics.containers;

public class Role extends Base {

    private final String roleName;

    public Role(String id, String roleName) {
        super(id);
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}