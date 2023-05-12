package aiss.gitminer.controllers;

import aiss.gitminer.model.Comment;
import aiss.gitminer.model.Commit;
import aiss.gitminer.model.Issue;
import aiss.gitminer.model.Project;
import aiss.gitminer.repositories.CommentRepository;
import aiss.gitminer.repositories.CommitRepository;
import aiss.gitminer.repositories.IssueRepository;
import aiss.gitminer.repositories.ProjectRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectRepository projectRepository;
    private final IssueRepository issueRepository;
    private final CommentRepository commentRepository;
    private final CommitRepository commitRepository;

    public ProjectController(ProjectRepository projectRepository, IssueRepository issueRepository, CommentRepository commentRepository, CommitRepository commitRepository)
    {
        this.projectRepository = projectRepository;
        this.commentRepository = commentRepository;
        this.commitRepository = commitRepository;
        this.issueRepository = issueRepository;
    }

    @PostMapping("/{user}/{repo}")
    public Project fetchAllData(@PathVariable String user, @PathVariable String repo)
    {
        Project project = projectRepository.fetchGitLab(user,repo);
        Commit[] commits = commitRepository.fetchGitLab(user,repo);
        Issue[] issues = issueRepository.fetchGitLab(user,repo);
        for(Issue i: issues){
            Comment[] comments = commentRepository.fetchGitLab(user, repo, i.getRefId());
            i.setComments(comments);
        }
        project.setCommits(commits);
        project.setIssues(issues);
        projectRepository.postGitMiner(project);
        return project;
    }

}
