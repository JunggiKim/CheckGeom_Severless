package side.project.checkgeom_severless.repository.library.gyeonggidocyberlibrary;

public enum GyeonggiDoCyberLibraryBookType {

    COLLECTION("소장형", "EB"),
    SUBSCRIPTION("구독형", "SUBS");

    private final String name;
    private final String urlType;

    GyeonggiDoCyberLibraryBookType(String name, String urlType) {
        this.name = name;
        this.urlType = urlType;
    }

    public static GyeonggiDoCyberLibraryBookType of(String bookType) {
        return switch (bookType.trim()) {
            case "소장형" -> COLLECTION;
            case "구독형" -> SUBSCRIPTION;
            default -> throw  new RuntimeException(bookType + " : 지원 하는 타입이 아닙니다.");
        };
    }

    public String getUrlType() {
        return urlType;
    }

    public String getName() {
        return name;
    }

}
