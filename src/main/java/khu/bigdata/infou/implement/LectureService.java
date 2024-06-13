package khu.bigdata.infou.implement;

import khu.bigdata.infou.business.LectureConverter;
import khu.bigdata.infou.domain.*;
import khu.bigdata.infou.repository.*;
import khu.bigdata.infou.web.dto.LectureResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class LectureService {

    private final PlatformStudentRepository platformStudentRepository;
    private final LectureInflearnRepository lectureInflearnRepository;
    private final LectureUdemyRepository lectureUdemyRepository;
    private final LectureDetailRepository lectureDetailRepository;
    private final LectureTagRepository lectureTagRepository;


    /**
     * 카테고리별 추천 강좌 조회
     */
    public LectureResponseDTO.CategoryRecommendLectureDto findRecommendedLectureByCategory(String category) {

        if (category == null) {
            // default category(udemy)
            category = "Programming Languages";
        }

        //inflearn 강좌 조회 추가

        // default category(inflearn)
        String categoryInflearn = "프로그래밍 언어";

        String firstCategory = null;
        String secondCategory = null;

        categoryInflearn = switch (category) {
            case "Programming Languages" -> "프로그래밍 언어";
            case "Web Development" -> "웹 개발";
            case "Game Development" -> "게임 개발";
            case "Mobile Development" -> "모바일 개발";
            case "Data Science" -> "데이터 사이언스";
            default -> categoryInflearn;
        };

        String[] parts = categoryInflearn.split(" ");
        if (parts.length == 2) {
            firstCategory = parts[0];
            secondCategory = parts[1];
        }

        List<LectureUdemy> lectureUdemyList = lectureUdemyRepository.findAllBySubcategory(category);

        List<LectureInflearn> lectureInflearnList = lectureInflearnRepository.findAllByFirstCategoryAndSecondCategory(firstCategory, secondCategory);

        // 일단 가져오고 상위 값 추출
        List<LectureUdemy> sortedList = lectureUdemyList.stream()
                .sorted(Comparator.comparingDouble((LectureUdemy lecture) -> lecture.getAvgRating() * lecture.getNumReviews()).reversed())
                .limit(1000) // 1000개로 제한
                .collect(Collectors.toList());

        return LectureConverter.toCategoryRecommendLectureDto(sortedList);
    }

    /**
     * 선택된 키워드별 추천 강좌 조회
     */
    public LectureResponseDTO.KeywordRecommendLectureDto findRecommendedLectureByKeyword(String keyword) {
        // 키워드가 null이거나 빈 문자열인 경우에 대한 예외 처리
        if (keyword == null || keyword.trim().isEmpty()) {
            throw new IllegalArgumentException("Keyword must not be null or empty");
        }

        // 키워드를 포함하는 LectureTag 목록 조회
        List<LectureTag> lectureTags = lectureTagRepository.findByTopword1ContainingOrTopword2ContainingOrTopword3ContainingOrTopword4ContainingOrTopword5Containing(
                keyword, keyword, keyword, keyword, keyword);

        // LectureTag 목록에서 lectureId 추출
        List<Integer> lectureIds = lectureTags.stream()
                .map(LectureTag::getLectureUdemyId)
                .collect(Collectors.toList());

        // lectureId 목록을 사용하여 LectureUdemy 목록 조회
        List<LectureUdemy> lectureUdemyList = lectureUdemyRepository.findByLectureIdIn(lectureIds);

        // 조회된 강의 목록을 AvgRating과 NumReviews의 값을 곱한 값으로 내림차순 정렬
        List<LectureUdemy> sortedList = lectureUdemyList.stream()
                .sorted(Comparator.comparingDouble((LectureUdemy lecture) -> lecture.getAvgRating() * lecture.getNumReviews()).reversed())
                .limit(1000) // 1000개로 제한
                .collect(Collectors.toList());

        // 조회된 강의 목록과 대응되는 LectureTag를 매핑하여 DTO로 변환
        return LectureConverter.toKeywordRecommendLectureDto(sortedList, lectureTags);
    }

    /**
     * 강좌 세부 조회
     */
    public LectureResponseDTO.LectureDetailDto findLectureDetail(Integer lectureId) {

        // lectureId가 null인 경우에 대한 예외 처리
        if (lectureId == null) {
            throw new IllegalArgumentException("LectureId must not be null");
        }

        LectureUdemy lectureUdemy = lectureUdemyRepository.findByLectureId(lectureId)
                .orElseThrow(() -> new IllegalArgumentException("Lecture not found"));
        LectureDetail lectureDetail = lectureDetailRepository.findByLectureUdemyId(lectureId)
                .orElseThrow(() -> new IllegalArgumentException("Lecture detail not found"));
        LectureTag lectureTag = lectureTagRepository.findByLectureUdemyId(lectureId)
                .orElseThrow(() -> new IllegalArgumentException("Lecture tag not found"));

        return LectureConverter.toLectureDetailDto(lectureUdemy, lectureDetail, lectureTag);
    }

    /**
     * 다른 수강생들의 커리큘럼 전체 조회
     */
    public LectureResponseDTO.OtherStudentsDto findOtherStudents() {

        List<PlatformStudent> platformStudents = platformStudentRepository.findTop1000ByUdemyUserIdIsNotNull();

        // 가져온 데이터를 List로 변환
        List<PlatformStudent> studentList = platformStudents.stream()
                .limit(1000) // 상위 1000개로 제한
                .collect(Collectors.toList());

        return LectureConverter.toOtherStudentsDto(studentList);
    }

    public LectureResponseDTO.OtherLectureListDto findOtherLectureList() {
        return null;
    }

    public LectureResponseDTO.TopProgrammingLanguageList findTopProgrammingLanguages() {
        return null;
    }
}
