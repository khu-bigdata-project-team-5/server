package khu.bigdata.infou.implement;

import khu.bigdata.infou.business.LectureConverter;
import khu.bigdata.infou.domain.LectureDetail;
import khu.bigdata.infou.domain.LectureUdemy;
import khu.bigdata.infou.domain.PlatformStudent;
import khu.bigdata.infou.repository.LectureDetailRepository;
import khu.bigdata.infou.repository.LectureInflearnRepository;
import khu.bigdata.infou.repository.LectureUdemyRepository;
import khu.bigdata.infou.repository.PlatformStudentRepository;
import khu.bigdata.infou.web.dto.LectureResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class LectureService {

    private final PlatformStudentRepository platformStudentRepository;
    private final LectureInflearnRepository lectureInflearnRepository;
    private final LectureUdemyRepository lectureUdemyRepository;
    private final LectureDetailRepository lectureDetailRepository;


    // 카테고리별 추천 강좌 조회
    public LectureResponseDTO.CategoryRecommendLectureDto findRecommendedLectureByCategory(String category) {

        if (category == null) {
            // 카테고리 프로그래밍 언어로 고정해서 가져와라
            category = "Programming Languages";
        }
        List<LectureUdemy> lectureUdemyList = lectureUdemyRepository.findAllBySubcategory(category);

        // 일단 가져오고 상위 값 추출
        List<LectureUdemy> sortedList = lectureUdemyList.stream()
                .sorted(Comparator.comparingDouble((LectureUdemy lecture) -> lecture.getAvgRating() * lecture.getNumReviews()).reversed())
                .limit(1000) // 1000개로 제한
                .collect(Collectors.toList());

        return LectureConverter.toCategoryRecommendLectureDto(sortedList);
    }

    // 선택된 키워드별 추천 강좌 조회
    public LectureResponseDTO.KeywordRecommendLectureDto findRecommendedLectureByKeyword(String keyword) {
        // 키워드가 null이거나 빈 문자열인 경우에 대한 예외 처리
        if (keyword == null || keyword.trim().isEmpty()) {
            throw new IllegalArgumentException("Keyword must not be null or empty");
        }

        // 키워드를 포함하는 강의 목록을 조회(topic 데이터에 있는지 확인)
        List<LectureUdemy> lectureUdemyList = lectureUdemyRepository.findAllByTopic(keyword);

        // 조회된 강의 목록을 AvgRating과 NumReviews의 값을 곱한 값으로 내림차순 정렬
        List<LectureUdemy> sortedList = lectureUdemyList.stream()
                .sorted(Comparator.comparingDouble((LectureUdemy lecture) -> lecture.getAvgRating() * lecture.getNumReviews()).reversed())
                .limit(1000) // 1000개로 제한
                .collect(Collectors.toList());

        // 조회된 강의 목록을 DTO로 변환하여 반환
        return LectureConverter.toKeywordRecommendLectureDto(sortedList);
    }

    // 강좌 세부 조회
    public LectureResponseDTO.LectureDetailDto findLectureDetail(Integer lectureId) {

        // lectureId가 null인 경우에 대한 예외 처리
        if (lectureId == null) {
            throw new IllegalArgumentException("LectureId must not be null");
        }

        LectureUdemy lectureUdemy = lectureUdemyRepository.findByLectureId(Long.valueOf(lectureId))
                .orElseThrow(() -> new IllegalArgumentException("Lecture not found"));
        LectureDetail lectureDetail = lectureDetailRepository.findByLectureUdemyId(Long.valueOf(lectureId))
                .orElseThrow(() -> new IllegalArgumentException("Lecture detail not found"));

        return LectureConverter.toLectureDetailDto(lectureUdemy, lectureDetail);
    }

    public LectureResponseDTO.OtherStudentsDto findOtherStudents() {
        // inflearn_user_id의 개수를 세고 내림차순으로 정렬하여 상위 1000개 선택
        List<Object[]> inflearnUserCounts = platformStudentRepository.countInflearnUserId();
        List<PlatformStudent> topInflearnStudents = inflearnUserCounts.stream()
                .limit(1000)
                .map(obj -> (Long) obj[0])
                .flatMap(id -> platformStudentRepository.findByInflearnUserId(id).stream())
                .collect(Collectors.toList());

        // udemy_user_id의 개수를 세고 내림차순으로 정렬하여 상위 1000개 선택
        List<Object[]> udemyUserCounts = platformStudentRepository.countUdemyUserId();
        List<PlatformStudent> topUdemyStudents = udemyUserCounts.stream()
                .limit(1000)
                .map(obj -> (Long) obj[0])
                .flatMap(id -> platformStudentRepository.findByUdemyUserId(id).stream())
                .collect(Collectors.toList());

        // 두 리스트를 합치고 중복을 제거하여 최종 리스트 생성
        Set<PlatformStudent> topStudentsSet = new HashSet<>();
        topStudentsSet.addAll(topInflearnStudents);
        topStudentsSet.addAll(topUdemyStudents);

        List<PlatformStudent> topStudents = new ArrayList<>(topStudentsSet);

        return LectureConverter.toOtherStudentsDto(topStudents);
    }

    public LectureResponseDTO.OtherLectureListDto findOtherLectureList() {
        return null;
    }

}
