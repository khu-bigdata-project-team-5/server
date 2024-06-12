package khu.bigdata.infou.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import java.util.Date;

@Entity
@Getter
@Builder
@DynamicInsert
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // 생성 로직 규정
@Table(name = "lecture_inflearn")
public class LectureInflearn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "lecture_id", nullable = false)
    private int lectureId;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "instructor_id")
    private int instructorId;

    @Column(name = "instructor_name", length = 50)
    private String instructorName;

    @Column(name = "review_count")
    private int reviewCount;

    @Column(name = "star")
    private float star;

    @Column(name = "level", length = 20)
    private String level;

    @Column(name = "first_category", length = 255)
    private String firstCategory;

    @Column(name = "second_category", length = 255)
    private String secondCategory;

    @Column(name = "skill_tag", length = 255)
    private String skillTag;

    @Column(name = "price")
    private int price;

    @Column(name = "student_count")
    private int studentCount;

    @Column(name = "thumbnail_url", columnDefinition = "TEXT")
    private String thumbnailUrl;

    @Column(name = "title", columnDefinition = "TEXT")
    private String title;

    @Column(name = "updated_at")
    @Temporal(TemporalType.DATE)
    private Date updatedAt;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "platform_student_lecture_id")
//    private PlatformStudentLecture platformStudentLecture;


//    public void setPlatformStudentLecture(PlatformStudentLecture platformStudentLecture) {
//        if (this.platformStudentLecture != null) {
//            this.platformStudentLecture.getLectureInflearnList().remove(this);
//        }
//        this.platformStudentLecture = platformStudentLecture;
//    }
    // getters and setters
}

