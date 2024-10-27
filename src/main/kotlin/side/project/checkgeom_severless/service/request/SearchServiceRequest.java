package side.project.checkgeom_severless.service.request;

public record SearchServiceRequest (
        String keyword,
        String searchType,
        String listType,
        String sort
){

}
