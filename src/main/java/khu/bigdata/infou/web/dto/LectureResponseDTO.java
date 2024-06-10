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

        private int lectureId;
        private float avgRating;
        private String thumbnail;
        private String title;
        private float price;

        private String topword1;
        private String topword2;
        private String topword3;

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

        private int lectureId;
        private float avgRating;
        private String thumbnail;
        private String title;
        private float price;

        private String topword1;
        private String topword2;
        private String topword3;
    }


    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class KeywordRecommendLectureDto {

        List<KeywordRecommendLectureInfo> lectureList;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LectureDetailDto {

        private float avgRating;
        private String thumbnail;
        private String title;
        private float price;
        private Integer good;
        private Integer bad;
        private float teaching_quality;
        private float reference;
        private float practice;
        private float rating;
        private float level;
        private String category;
        private String subcategory;
        private String topic;

    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OtherStudentsDto {

        private String name;
        private String category;
        private String subcategory;
        private String topic;

    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OtherLectureListDto {

        private String category;
        private String subcategory;
        private String topic;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StudentTopwordDto {

        private String category;
        private String subcategory;
        private String topic;
    }


}
