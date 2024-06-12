package khu.bigdata.infou.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Getter
@Builder
@DynamicInsert
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // 생성 로직 규정
@Table(name = "lecture_tag")
public class LectureTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "lecture_udemy_id", nullable = false)
    private Integer lectureUdemyId;

    @Column(name = "lecture_inflearn_id", nullable = false)
    private Integer lectureInflearnId;

    @Column(name = "topword1", length = 20)
    private String topword1;

    @Column(name = "topword2", length = 20)
    private String topword2;

    @Column(name = "topword3", length = 20)
    private String topword3;

    @Column(name = "topword4", length = 20)
    private String topword4;

    @Column(name = "topword5", length = 20)
    private String topword5;

//    @OneToOne(fetch = FetchType.LAZY)
//    private LectureInflearn lectureInflearn;
//
//    @OneToOne(fetch = FetchType.LAZY)
//    private LectureUdemy lectureUdemy;
}
