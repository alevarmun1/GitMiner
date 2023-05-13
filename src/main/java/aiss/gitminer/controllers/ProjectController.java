package aiss.gitminer.controllers;

import aiss.gitminer.exception.IssueNotFoundException;
import aiss.gitminer.exception.ProjectNotFoundException;
import aiss.gitminer.model.Comment;
import aiss.gitminer.model.Commit;
import aiss.gitminer.model.Issue;
import aiss.gitminer.model.Project;
import aiss.gitminer.repositories.CommentRepository;
import aiss.gitminer.repositories.CommitRepository;
import aiss.gitminer.repositories.IssueRepository;
import aiss.gitminer.repositories.ProjectRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping
    public List<Project> findAll(@RequestParam(required = false) String id,
                                 @RequestParam(required = false) String order,
                                 @RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "10") int size
    ) throws ProjectNotFoundException {

        Optional<Project> project = ProjectRepository.findById(id);
        if(!project.isPresent()){
            throw new ProjectNotFoundException();
        }

        Pageable paging;

        if(order != null){
            if(order.startsWith("-")){
                paging = PageRequest.of(page, size, Sort.by(order.substring(1)).descending());
            } else {
                paging = PageRequest.of(page, size, Sort.by(order).ascending());
            }

            }
        else {
            paging = PageRequest.of(page, size);

        }
        Page<Project> pageProjects;

        if(id == null){
            pageProjects = projectRepository.findAll(paging);
        } else {
            pageProjects = projectRepository.findById(id, paging);
        }
        return pageProjects.getContent();


    }

}
