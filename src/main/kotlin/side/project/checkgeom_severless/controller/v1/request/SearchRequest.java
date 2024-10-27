package side.project.checkgeom_severless.controller.v1.request;

public record SearchRequest(
    String keyword,
    String searchType
) {




    // public static SearchRequest of(String keyword, String searchType, String listType, String sort) {
    //     return new SearchRequest(keyword, searchType, listType, sort);
    // }
    //
    // public SearchServiceRequest toServiceRequest() {
    //     return new SearchServiceRequest(
    //             this.keyword,
    //             this.searchType,
    //             this.listType,
    //             this.sort
    //     );
    // }

}
