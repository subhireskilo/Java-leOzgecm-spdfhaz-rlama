public class JobExperience {

    private final String position;
    private final String company;
    private final String period;
    private final String description;

    public JobExperience(
            String position,
            String company,
            String period,
            String description) {

        this.position = position;
        this.company = company;
        this.period = period;
        this.description = description;
    }

    public String getPosition() {
        return position;
    }

    public String getCompany() {
        return company;
    }

    public String getPeriod() {
        return period;
    }

    public String getDescription() {
        return description;
    }
}