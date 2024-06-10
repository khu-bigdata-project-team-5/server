package khu.bigdata.infou.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import khu.bigdata.infou.implement.LectureService;
import khu.bigdata.infou.web.dto.LectureResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class MainController {

    private final LectureService lectureService;

    @GetMapping("/lectures/categories")
    @Operation(summary = "카테고리별 추천 강좌 조회 API", description = "카테고리: 프로그래밍 언어, 웹 개발, 게임 개발, 모바일 앱 개발, 데이터 사이언스, 인공지능")
    public ResponseEntity<LectureResponseDTO.CategoryRecommendLectureDto> getRecommendedLectureByCategory(
            @RequestParam(name = "category") String category
    ) {
        return ResponseEntity.ok(lectureService.findRecommendedLectureByCategory(category));
    }


    @GetMapping("/curriculum/recommendation")
    @Operation(summary = "선택된 키워드별 추천 강좌 조회 API", description = "유저로부터 입력받은 키워드 4개를 입력받습니다.")
    public ResponseEntity<LectureResponseDTO.KeywordRecommendLectureDto> getRecommendedLectureByKeyword(
            @RequestParam(name = "keyword") String keyword
    ) {
        return ResponseEntity.ok(lectureService.findRecommendedLectureByKeyword(keyword));
    }


    @GetMapping("/lectures/{lectureId}")
    @Operation(summary = "강좌 세부 조회 API", description = "특정 강좌의 lecture_id를 입력받습니다.")
    public ResponseEntity<LectureResponseDTO.LectureDetailDto> getLectureDetail(
            @PathVariable(name = "lectureId") Integer lectureId
    ) {
        return ResponseEntity.ok(lectureService.findLectureDetail());
    }

    @GetMapping("/others")
    @Operation(summary = "다른 수강생들의 커리큘럼 전체 조회 API")
    public ResponseEntity<LectureResponseDTO.OtherStudentsDto> getOtherStudents() {
        return ResponseEntity.ok(lectureService.findOtherStudents());
    }

    @GetMapping("/others/{userId}/lectures")
    @Operation(summary = "특정 수강생이 들은 강좌 목록 조회 API", description = "특정 유저의 user_id를 입력받습니다.")
    public ResponseEntity<LectureResponseDTO.OtherLectureListDto> getOtherLectureList(
            @PathVariable(name = "userId") Integer userId
    ) {
        return ResponseEntity.ok(lectureService.findOtherLectureList());
    }

    @GetMapping("/others/{userId}")
    @Operation(summary = "특정 수강생의 topword 조회 API", description = "유저로부터 입력받은 키워드 4개를 입력받습니다.")
    public ResponseEntity<LectureResponseDTO.StudentTopwordDto> getStudentTopword(
            @PathVariable(name = "userId") Integer userId
    ) {
        return ResponseEntity.ok(lectureService.findStudentTopword());
    }
}
