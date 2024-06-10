package khu.bigdata.infou.domain;

import jakarta.persistence.*;
import khu.bigdata.infou.domain.enums.LectureType;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Getter
@Builder
@DynamicInsert
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // 생성 로직 규정
@Table(name = "lecture_detail")
public class LectureDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "lecture_id", nullable = false)
    private Integer lectureId;

    @Column(name = "good", nullable = false)
    private Integer good;

    @Column(name = "bad", nullable = false)
    private Integer bad;

    @Column(name = "teaching_quality", nullable = false)
    private Float teachingQuality;

    @Column(name = "reference", nullable = false)
    private Float reference;

    @Column(name = "practice", nullable = false)
    private Float practice;

    @Column(name = "rating", nullable = false)
    private Float rating;

    @Column(name = "level", nullable = false)
    private Float level;

    @Enumerated(EnumType.STRING)
    @Column(name = "lecture_type", nullable = false)
    private LectureType lectureType;

    @OneToOne(fetch = FetchType.LAZY)
    private LectureInflearn lectureInflearn;

    @OneToOne(fetch = FetchType.LAZY)
    private LectureUdemy lectureUdemy;
}
