package com.VLS.course.entity;



import javax.persistence.*;

@Entity
@Table(name = "COURSETABLE")
public class CourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CourseId")
    private Long courseId;

    @Column(name = "CourseName", nullable = false)
    private String courseName;

    @Column(name = "AuthorName", nullable = false)
    private String authorName;

    @Column(name = "CourseDurationHours", nullable = false)
    private int courseDuration;  // Duration in hours

    @Column(name = "Availability", nullable = false)
    private boolean availability; // true = available, false = not available

    // default Constructors
    public CourseEntity() {
    }

    public CourseEntity(String courseName, String authorName, int courseDuration, boolean availability) {
        this.courseName = courseName;
        this.authorName = authorName;
        this.courseDuration = courseDuration;
        this.availability = availability;
    }

    // Getters and Setters
    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getCourseDuration() {
        return courseDuration;
    }

    public void setCourseDuration(int courseDuration) {
        this.courseDuration = courseDuration;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}
