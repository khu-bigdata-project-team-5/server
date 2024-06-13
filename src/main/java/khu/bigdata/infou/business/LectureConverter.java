package khu.bigdata.infou.business;

import khu.bigdata.infou.domain.LectureDetail;
import khu.bigdata.infou.domain.LectureTag;
import khu.bigdata.infou.domain.LectureUdemy;
import khu.bigdata.infou.domain.PlatformStudent;
import khu.bigdata.infou.web.dto.LectureResponseDTO;

import java.util.List;
import java.util.Map;
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
    public static LectureResponseDTO.KeywordRecommendLectureInfo toKeywordRecommendLectureInfo(LectureUdemy lectureUdemy, LectureTag lectureTag) {

        return LectureResponseDTO.KeywordRecommendLectureInfo.builder()
                .lectureId(lectureUdemy.getLectureId())
                .title(lectureUdemy.getTitle())
                .price(lectureUdemy.getPrice())
                .avgRating(lectureUdemy.getAvgRating())
                .thumbnail(lectureUdemy.getThumbnail())
                .instructorName(lectureUdemy.getInstructorName())
                .topword1(lectureTag.getTopword1())
                .topword2(lectureTag.getTopword2())
                .topword3(lectureTag.getTopword3())
                .topword4(lectureTag.getTopword4())
                .topword5(lectureTag.getTopword5())
                .build();
    }

    public static LectureResponseDTO.KeywordRecommendLectureDto toKeywordRecommendLectureDto(
            List<LectureUdemy> lectureUdemyList, List<LectureTag> lectureTagList) {

        Map<Integer, LectureTag> lectureTagMap = lectureTagList.stream()
                .collect(Collectors.toMap(LectureTag::getLectureUdemyId, tag -> tag));

        List<LectureResponseDTO.KeywordRecommendLectureInfo> lectureInfos = lectureUdemyList.stream()
                .map(lectureUdemy -> {
                    LectureTag lectureTag = lectureTagMap.get(lectureUdemy.getLectureId());
                    if (lectureTag == null) {
                        throw new IllegalArgumentException("Matching LectureTag not found for lectureId: " + lectureUdemy.getLectureId());
                    }
                    return toKeywordRecommendLectureInfo(lectureUdemy, lectureTag);
                })
                .collect(Collectors.toList());

        return LectureResponseDTO.KeywordRecommendLectureDto.builder()
                .lectureList(lectureInfos)
                .build();
    }

    // 강좌 세부 조회
    public static LectureResponseDTO.LectureDetailDto toLectureDetailDto(LectureUdemy lectureUdemy, LectureDetail lectureDetail, LectureTag lectureTag) {

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
                .topword1(lectureTag.getTopword1())
                .topword2(lectureTag.getTopword2())
                .topword3(lectureTag.getTopword3())
                .topword4(lectureTag.getTopword4())
                .topword5(lectureTag.getTopword5())
                .build();
    }

    public static LectureResponseDTO.OtherStudentsListInfo toOtherStudentInfo(PlatformStudent student) {

        return LectureResponseDTO.OtherStudentsListInfo.builder()
                .inflearnUserId(student.getInflearnUserId() != null ? student.getInflearnUserId() : null)
                .udemyUserId(student.getUdemyUserId() != null ? student.getUdemyUserId() : null)
                .name(student.getName())
                .topword1(student.getTopword1())
                .topword2(student.getTopword2())
                .topword3(student.getTopword3())
                .topword4(student.getTopword4())
                .topword5(student.getTopword5())
                .build();
    }

    public static LectureResponseDTO.OtherStudentsDto toOtherStudentsDto(List<PlatformStudent> students) {

        List<LectureResponseDTO.OtherStudentsListInfo> studentInfos = students.stream()
                .map(LectureConverter::toOtherStudentInfo)
                .collect(Collectors.toList());

        return LectureResponseDTO.OtherStudentsDto.builder()
                .studentList(studentInfos)
                .build();
    }
}
