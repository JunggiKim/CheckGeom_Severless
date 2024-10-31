package side.project.checkgeom_severless.service;


import side.project.checkgeom_severless.service.response.AllLibraryServiceResponse;
import side.project.checkgeom_severless.service.response.LibrarySearchServiceResponse;

public interface LibrarySearchService {
	LibrarySearchServiceResponse gyeonggiDoCyberLibrarySearch(String searchKeyword);

	LibrarySearchServiceResponse gyeonggiEducationalElectronicLibrarySearch(String searchKeyword);

	LibrarySearchServiceResponse smallBusinessLibrarySearch(String searchKeyword);

	AllLibraryServiceResponse allLibraryAsyncSearch(String searchKeyword);
}
