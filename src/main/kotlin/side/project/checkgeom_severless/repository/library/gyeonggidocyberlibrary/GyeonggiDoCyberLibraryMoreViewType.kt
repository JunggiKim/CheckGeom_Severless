package side.project.checkgeom_severless.repository.library.gyeonggidocyberlibrary;

public record GyeonggiDoCyberLibraryMoreViewType(
        GyeonggiDoCyberLibraryBookType bookType ,
        int totalCount
) {


    public static GyeonggiDoCyberLibraryMoreViewType of (GyeonggiDoCyberLibraryBookType bookType , int totalCount) {
        return new GyeonggiDoCyberLibraryMoreViewType(
                bookType,
                totalCount
        );
    }


    public boolean isMoreView () {
        return this.totalCount > 6 ;
    }

    public boolean isNotMoreView () {
        return this.totalCount <= 6 ;
    }


}
