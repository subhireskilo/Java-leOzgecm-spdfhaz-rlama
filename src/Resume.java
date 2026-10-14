import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Resume {

    private final String fullName;
    private final String jobTitle;
    private final String email;
    private final String phone;
    private final String location;
    private final String summary;
    private final String education;
    private final String skills;
    private final String languages;

    private final List<JobExperience> experiences;

    public Resume(
            String fullName,
            String jobTitle,
            String email,
            String phone,
            String location,
            String summary,
            String education,
            String skills,
            String languages,
            List<JobExperience> experiences) {

        this.fullName = fullName;
        this.jobTitle = jobTitle;
        this.email = email;
        this.phone = phone;
        this.location = location;
        this.summary = summary;
        this.education = education;
        this.skills = skills;
        this.languages = languages;

        this.experiences = Collections.unmodifiableList(
                new ArrayList<>(experiences)
        );
    }

    public String getFullName() {
        return fullName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getLocation() {
        return location;
    }

    public String getSummary() {
        return summary;
    }

    public String getEducation() {
        return education;
    }

    public String getSkills() {
        return skills;
    }

    public String getLanguages() {
        return languages;
    }

    public List<JobExperience> getExperiences() {
        return experiences;
    }
}