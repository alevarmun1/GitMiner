package aiss.gitminer.controllers;

import aiss.gitminer.exception.CommentNotFoundException;
import aiss.gitminer.exception.CommitNotFoundException;
import aiss.gitminer.model.Comment;
import aiss.gitminer.model.Commit;
import aiss.gitminer.repositories.CommentRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Tag(name = "Commit", description = "Commit management API")
@RestController
@RequestMapping("/api/commits")
public class CommitController {

    public List<Commit> findAll(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "10") int size,
                                @RequestParam(required = false) String authorEmail,
                                @RequestParam(required = false) String id,
                                @RequestParam(required = false) String order)
    throws CommitNotFoundException {

        Optional<Commit> commit = CommitRepository.findById(id);
        if(!commit.isPresent()){
            throw new CommitNotFoundException();
        }
        Optional<Commit> commit2 = CommitRepository.findByAuthorEmail(authorEmail);
        if(!commit2.isPresent()){
            throw new CommitNotFoundException();
        }

        Pageable paging;
        if(order != null) {
            if (order.startsWith("-"))
                paging = PageRequest.of(page, size, Sort.by(order.substring(1)).descending());
            else
                paging = PageRequest.of(page, size, Sort.by(order).ascending());
        }
        else
            paging = PageRequest.of(page, size);


        Page<Commit> pageCommits;

        if(authorEmail!=null)
            pageCommits = CommentRepository.findByAuthorEmail(authorEmail,paging);
        else
            pageCommits = CommentRepository.findAll(paging);

        if(id!=null)
            pageCommits = CommentRepository.findByAuthorEmail(id,paging);
        else
            pageCommits = CommentRepository.findAll(paging);

        return pageCommits.getContent();
    }
}
