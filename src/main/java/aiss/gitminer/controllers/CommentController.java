package aiss.gitminer.controllers;

import aiss.gitminer.exception.CommentNotFoundException;
import aiss.gitminer.model.Comment;
import aiss.gitminer.repositories.CommentRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

@Tag(name = "Comment", description = "Comment management API")
@RestController
@RequestMapping("/api/comments")
public class CommentController {

    public List<Comment> findAll(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "10") int size,
                                 @RequestParam(required = false) String id,
                                 @RequestParam(required = false) String order)
    throws CommentNotFoundException {

        Optional<Comment> comment = CommentRepository.findById(id);
        if(!comment.isPresent()){
            throw new CommentNotFoundException();
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


        Page<Comment> pageComments;

        if(id!=null)
            pageComments = CommentRepository.findById(id, paging);
        else
            pageComments = CommentRepository.findAll(paging);
        return pageComments.getContent();
    }

}
