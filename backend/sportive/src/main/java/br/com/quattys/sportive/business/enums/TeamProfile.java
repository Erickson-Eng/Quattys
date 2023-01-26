package br.com.quattys.sportive.business.enums;

public enum TeamProfile {
    MANAGER("MANAGER"),
    AFFILIATE("AFFILIATE"),
    WAITING_FOR_AFFILIATION("WAITING_FOR_AFFILIATION"),
    NOT_AFFILIATE("NOT_AFFILIATE");

    private final String code;

    TeamProfile(String code){
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public TeamProfile getProfile(String code){
        for (TeamProfile profile: TeamProfile.values()
             ) {
            if (profile.getCode().equalsIgnoreCase(code)){
                return profile;
            }
        } throw new IllegalArgumentException("Invalid profile");
    }
}
