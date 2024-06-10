package khu.bigdata.infou.business;

import khu.bigdata.infou.domain.LectureTag;
import khu.bigdata.infou.domain.LectureUdemy;
import khu.bigdata.infou.web.dto.LectureResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class LectureConverter {


    public static LectureResponseDTO.CategoryRecommendLectureInfo toCategoryRecommendLectureInfo(LectureUdemy lectureUdemy) {

        LectureTag lectureTag = lectureUdemy.getLectureTag();

        return LectureResponseDTO.CategoryRecommendLectureInfo.builder()
                .lectureId(lectureUdemy.getLectureId())
                .title(lectureUdemy.getTitle())
                .price(lectureUdemy.getPrice())
                .avgRating(lectureUdemy.getAvgRating())
                .thumbnail(lectureUdemy.getThumbnail())
                .topword1(lectureTag.getTopword1())
                .topword2(lectureTag.getTopword2())
                .topword3(lectureTag.getTopword3())
                .topword4(lectureTag.getTopword4())
                .topword5(lectureTag.getTopword5())
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
        LectureTag lectureTag = lectureUdemy.getLectureTag();

        return LectureResponseDTO.KeywordRecommendLectureInfo.builder()
                .lectureId(lectureUdemy.getLectureId())
                .title(lectureUdemy.getTitle())
                .price(lectureUdemy.getPrice())
                .avgRating(lectureUdemy.getAvgRating())
                .thumbnail(lectureUdemy.getThumbnail())
                .topword1(lectureTag.getTopword1())
                .topword2(lectureTag.getTopword2())
                .topword3(lectureTag.getTopword3())
                .topword4(lectureTag.getTopword4())
                .topword5(lectureTag.getTopword5())
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

}
