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
    @Column(name = "lecture_inflearn_id")
    private int lectureInflearnId;

    @Column(name = "slug", columnDefinition = "TEXT")
    private String slug;

    @Column(name = "thumbnailUrl", columnDefinition = "TEXT")
    private String thumbnailUrl;

    @Column(name = "title", columnDefinition = "TEXT")
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "reviewCount")
    private int reviewCount;

    @Column(name = "studentCount")
    private int studentCount;

    @Column(name = "likeCount")
    private int likeCount;

    @Column(name = "star")
    private float star;

    @Column(name = "isExclusive")
    private boolean isExclusive;

    @Column(name = "isNew")
    private boolean isNew;

    @Column(name = "isUpdated")
    private boolean isUpdated;

    @Column(name = "updatedAt")
    @Temporal(TemporalType.DATE)
    private Date updatedAt;

    @Column(name = "publishedAt")
    @Temporal(TemporalType.DATE)
    private Date publishedAt;

    @Column(name = "metadata", columnDefinition = "JSON")
    private String metadata;

    @Column(name = "instructor_id")
    private int instructorId;

    @Column(name = "instructor_name", length = 50)
    private String instructorName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "platform_student_lecture_id")
    private PlatformStudentLecture platformStudentLecture;


    public void setPlatformStudentLecture(PlatformStudentLecture platformStudentLecture) {
        if (this.platformStudentLecture != null) {
            this.platformStudentLecture.getLectureInflearnList().remove(this);
        }
        this.platformStudentLecture = platformStudentLecture;
    }
    // getters and setters
}

