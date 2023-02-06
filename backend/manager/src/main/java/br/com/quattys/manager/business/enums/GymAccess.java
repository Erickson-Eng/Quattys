package br.com.quattys.manager.business.enums;

public enum GymAccess {
    PUBLIC_ACCESS("PUBLIC_ACCESS"),
    SUPERVISED_PUBLIC_ACCESS("SUPERVISED_PUBLIC_ACCESS"),
    PRIVATE_ACCESS("PRIVATE_ACCESS");

    private final String code;

    GymAccess(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public GymAccess getGymAccess(String code){
        for(GymAccess value: GymAccess.values()){
            if (value.getCode().equalsIgnoreCase(code))
                return value;
        }
        throw new IllegalArgumentException("Invalid code");
    }
}
