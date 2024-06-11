package khu.bigdata.infou.business;

import khu.bigdata.infou.domain.LectureDetail;
import khu.bigdata.infou.domain.LectureUdemy;
import khu.bigdata.infou.web.dto.LectureResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class LectureConverter {


    public static LectureResponseDTO.CategoryRecommendLectureInfo toCategoryRecommendLectureInfo(LectureUdemy lectureUdemy) {

        return LectureResponseDTO.CategoryRecommendLectureInfo.builder()
                .lectureId(lectureUdemy.getLectureId())
                .title(lectureUdemy.getTitle())
                .price(lectureUdemy.getPrice())
                .avgRating(lectureUdemy.getAvgRating())
                .thumbnail(lectureUdemy.getThumbnail())
                .instructorName(lectureUdemy.getInstructorName())
                .topword1(lectureUdemy.getTopic())
                .topword2(lectureUdemy.getSubcategory())
                .build();
    }

    public static LectureResponseDTO.CategoryRecommendLectureDto toCategoryRecommendLectureDto(List<LectureUdemy> list) {

        List<LectureResponseDTO.CategoryRecommendLectureInfo> infoList = list.stream().map(lecture -> toCategoryRecommendLectureInfo(lecture)).toList();

        return LectureResponseDTO.CategoryRecommendLectureDto.builder()
                .lectureList(infoList)
                .build();
    }


    // 키워드별 추천 강좌 조회
    public static LectureResponseDTO.KeywordRecommendLectureInfo toKeywordRecommendLectureInfo(LectureUdemy lectureUdemy) {

        return LectureResponseDTO.KeywordRecommendLectureInfo.builder()
                .lectureId(lectureUdemy.getLectureId())
                .title(lectureUdemy.getTitle())
                .price(lectureUdemy.getPrice())
                .avgRating(lectureUdemy.getAvgRating())
                .thumbnail(lectureUdemy.getThumbnail())
                .instructorName(lectureUdemy.getInstructorName())
                .topword1(lectureUdemy.getTopic())
                .topword2(lectureUdemy.getSubcategory())
                .build();
    }

    public static LectureResponseDTO.KeywordRecommendLectureDto toKeywordRecommendLectureDto(List<LectureUdemy> lectureUdemyList) {

        List<LectureResponseDTO.KeywordRecommendLectureInfo> lectureInfos = lectureUdemyList.stream()
                .map(LectureConverter::toKeywordRecommendLectureInfo)
                .collect(Collectors.toList());

        return LectureResponseDTO.KeywordRecommendLectureDto.builder()
                .lectureList(lectureInfos)
                .build();
    }

    // 강좌 세부 조회
    public static LectureResponseDTO.LectureDetailDto toLectureDetailDto(LectureUdemy lectureUdemy, LectureDetail lectureDetail) {

        return LectureResponseDTO.LectureDetailDto.builder()
                .lectureId(lectureUdemy.getLectureId())
                .title(lectureUdemy.getTitle())
                .price(lectureUdemy.getPrice())
                .avgRating(lectureUdemy.getAvgRating())
                .thumbnail(lectureUdemy.getThumbnail())
                .instructorName(lectureUdemy.getInstructorName())
                .category(lectureUdemy.getCategory())
                .subcategory(lectureUdemy.getSubcategory())
                .good(lectureDetail.getGood())
                .bad(lectureDetail.getBad())
                .teaching_quality(lectureDetail.getTeachingQuality())
                .reference(lectureDetail.getReference())
                .practice(lectureDetail.getPractice())
                .rating(lectureDetail.getRating())
                .level(lectureDetail.getLevel())
                .topword1(lectureUdemy.getTopic())
                .topword2(lectureUdemy.getSubcategory())
                .build();
    }
}
