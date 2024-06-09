package khu.bigdata.infou.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


public class LectureResponseDTO {


    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CategoryRecommendLectureInfo {

        private float avgRating;

        private String thumbnail;
        private String title;
        private float price;

        private String topword1;
        private String topword2;
        private String topword3;
        private String topword4;
        private String topword5;
    }


    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CategoryRecommendLectureDto {

        List<CategoryRecommendLectureInfo> lectureList;
    }


    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class KeywordRecommendLectureInfo {

        // 이제 여기다가 각 리스트의 요소 별 데이터를 넣는거임!

        private String title;
    }


    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class KeywordRecommendLectureDto {

        // 하드코딩 된 4개의 키워드에 대한 리스트니까 걍 복잡하게 생각하지 말고 리스트 4개 담자
        // 리스트 내부의 정보들이 엔티티가 아니잖아, json, 즉 dto임
        // <> 안에 들어갈 놈이 dto임
        List<KeywordRecommendLectureInfo> list;
    }
}
