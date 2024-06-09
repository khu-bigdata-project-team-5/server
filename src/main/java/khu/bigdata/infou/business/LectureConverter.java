package khu.bigdata.infou.business;

import khu.bigdata.infou.domain.LectureTag;
import khu.bigdata.infou.domain.LectureUdemy;
import khu.bigdata.infou.web.dto.LectureResponseDTO;

import java.util.List;

public class LectureConverter {


    public static LectureResponseDTO.CategoryRecommendLectureInfo toCategoryRecommandLectureInfo(LectureUdemy lectureUdemy) {

        // 옵션 커맨드 v 누르면 lectureUdemy.getLectureTag() 여기서 타입 알아서 찾아줌
        LectureTag lectureTag = lectureUdemy.getLectureTag();

        return LectureResponseDTO.CategoryRecommendLectureInfo.builder()
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

    public static LectureResponseDTO.CategoryRecommendLectureDto toCategoryRecommandLectureDto(List<LectureUdemy> list) {

        List<LectureResponseDTO.CategoryRecommendLectureInfo> infoList = list.stream().map(lecture -> toCategoryRecommandLectureInfo(lecture)).toList();

        return LectureResponseDTO.CategoryRecommendLectureDto.builder()
                .lectureList(infoList)
                .build();
    }

    // 컨버터는 아까 dto를 만드는 것임 근데 다만 빌더패턴을 쓰는거임!

    // 먼저 list dto를 만들어야 함!

    // 컨버터의 역할은 엔티티를 가지고 dto로 바꾸는 것임 즉 함수의 파라미터는 엔티티 리스트임!


    public static LectureResponseDTO.KeywordRecommendLectureInfo toHardCodingLectureInfo(LectureUdemy lectureUdemy) {
        return LectureResponseDTO.KeywordRecommendLectureInfo.builder()
                .title(lectureUdemy.getTitle())
                .build();
    }

    public static LectureResponseDTO.KeywordRecommendLectureDto toKeywordRecommendLectureDto(List<LectureUdemy> lectureUdemyList) {

        // 자, 아래의 빌더 만드는걸 보면 list()에서 저 괄호 안에 들어가는게 info의 리스트임
        // 그러면 내가 info의 리스트를 만들어야 타입이 맞음

        // 그냥 무지성으로 for 돌려도 되는데 스트림씀

        List<LectureResponseDTO.KeywordRecommendLectureInfo> lectureInfos = lectureUdemyList.stream().map(lectureUdemy -> toHardCodingLectureInfo(lectureUdemy)).toList();

        // 생각해보면 저 리스트 안에 들어가는게 또 하나의 dto임 그러니까 만들러 가야함
        return LectureResponseDTO.KeywordRecommendLectureDto.builder()
                .list(lectureInfos)
                .build();
    }


}
