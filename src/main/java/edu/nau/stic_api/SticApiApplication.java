package edu.nau.stic_api;

import edu.nau.stic_api.DataRepos.*;
import edu.nau.stic_api.DataStructures.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SticApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SticApiApplication.class, args);
	}

	@Bean
	public CommandLineRunner setup(TermRepository term_repo, ApprovalStatusRepository approval_repo, RequirementStatusRepository req_repo, RequirementRepository requirement_repo,
											 RequirementInstanceRepository instance_repo, StudentRepository student_repo)
	{
		InitilizeDatabase.initilizeDatabase();

		return (args) -> {
			term_repo.save(new Term("Fall"));
			term_repo.save(new Term("Spring"));
			term_repo.save(new Term("Summer"));
			term_repo.save(new Term("Winter"));
			approval_repo.save(new ApprovalStatus("Pending Approval"));
			approval_repo.save(new ApprovalStatus("Approved"));
			approval_repo.save(new ApprovalStatus("Denied"));
			req_repo.save(new RequirementStatus("Complete"));
			req_repo.save(new RequirementStatus("Incomplete"));
			req_repo.save(new RequirementStatus("In Progress"));
			student_repo.save(new Student("6e8d6c69af58e73c7248364aa59b0c257f6ba1d19782eb9e38890a61ada948ef", "Computer Science", "Spring", 2023));
			requirement_repo.save(new Requirement(1, "Computer Science", "Submit Score of Arizona Teacher Proficiency Assessment", "You must upload a passing score for administrator review on the required subject knowledge portion of the Arizona Teacher Proficiency Assessment that corresponds to the teaching certificate that you are pursuing.", true));
			requirement_repo.save(new Requirement(2, "Computer Science", "Submit Degree Transcripts", "You must submit your official transcripts documenting a Bachelors or more advanced degree.", true));
			requirement_repo.save(new Requirement(3, "Computer Science", "Complete Capstone", "Get er' done.", false));
			instance_repo.save(new RequirementInstance(1, 1, "6e8d6c69af58e73c7248364aa59b0c257f6ba1d19782eb9e38890a61ada948ef", "Incomplete"));
			instance_repo.save(new RequirementInstance(2, 2, "6e8d6c69af58e73c7248364aa59b0c257f6ba1d19782eb9e38890a61ada948ef", "Incomplete"));
			instance_repo.save(new RequirementInstance(3, 3, "6e8d6c69af58e73c7248364aa59b0c257f6ba1d19782eb9e38890a61ada948ef", "Incomplete"));
		};
	}
}
