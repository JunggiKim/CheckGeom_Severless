package side.project.checkgeom_severless.domain;

public record MoreView (
       boolean moreView,
       int totalCount
){
    public static MoreView create (
            int totalCount
    ) {
        return new MoreView(
                isMoreView(totalCount),
                totalCount
        );
    }

    private static boolean isMoreView(int totalCount) {
        return totalCount >= 10;
    }

}
