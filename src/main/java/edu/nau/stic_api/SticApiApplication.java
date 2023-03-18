package edu.nau.stic_api;

import edu.nau.stic_api.DataRepos.ApprovalStatusRepository;
import edu.nau.stic_api.DataRepos.RequirementStatusRepository;
import edu.nau.stic_api.DataRepos.TermRepository;
import edu.nau.stic_api.DataStructures.ApprovalStatus;
import edu.nau.stic_api.DataStructures.RequirementStatus;
import edu.nau.stic_api.DataStructures.Term;
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
	public CommandLineRunner setup(TermRepository term_repo, ApprovalStatusRepository approval_repo, RequirementStatusRepository req_repo)
	{
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
			req_repo.save(new RequirementStatus("Overdue"));


			req_repo.save(new Request("Education", "Submit Score of Arizona Teacher Proficiency Assessment", "You must upload a passing score for administrator review on the required subject knowledge portion of the Arizona Teacher Proficiency Assessment that corresponds to the teaching certificate that you are pursuing.", true));
			req_repo.save(new Request("Education", "Submit Degree Transcripts", "You must submit your official transcripts documenting a Bachelors or more advanced degree.", true));
			req_repo.save(new Request("Computer Science", "Complete Capstone", "Get er' done.", false));
		};
	}
}
