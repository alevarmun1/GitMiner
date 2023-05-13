package aiss.gitminer.controllers;

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

@RestController
@RequestMapping("/api/issues")
public class IssueController {

    private final ProjectRepository projectRepository;
    private final IssueRepository issueRepository;
    private final CommentRepository commentRepository;
    private final CommitRepository commitRepository;

    public IssueController(ProjectRepository projectRepository, IssueRepository issueRepository, CommentRepository commentRepository, CommitRepository commitRepository)
    {
        this.projectRepository = projectRepository;
        this.commentRepository = commentRepository;
        this.commitRepository = commitRepository;
        this.issueRepository = issueRepository;
    }

    @GetMapping
    public List<Issue> findAll(@RequestParam(required = false) String id,
                                 @RequestParam(required = false) String state,
                                 @RequestParam(required = false) String refId,
                                 @RequestParam(required = false) String order,
                                 @RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "10") int size
    ) {
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
        Page<Issue> pageIssues;

        if(id == null){
            pageIssues = issueRepository.findAll(paging);
        } else {
            pageIssues = issueRepository.findById(id, paging);
        }

        if(state == null){
            pageIssues = issueRepository.findAll(paging);
        } else {
            pageIssues = issueRepository.findByState(id, paging);
        }

        if(refId == null){
            pageIssues = issueRepository.findAll(paging);
        } else {
            pageIssues = issueRepository.findByRefId(id, paging);
        }

        return pageIssues.getContent();


    }

}
