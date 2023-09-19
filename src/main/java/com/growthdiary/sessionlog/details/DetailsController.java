package com.growthdiary.sessionlog.details;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/session")
public class DetailsController {

    private final DetailsService detailsService;

    @Autowired
    public DetailsController(DetailsService detailsService) {
        this.detailsService = detailsService;
    }

    @PostMapping("/details")
    public ResponseEntity<Details> details(@RequestParam String skill,
                                           @RequestParam String description) {
        Details details = detailsService.createDetails(skill, description);
        return new ResponseEntity<>(details, HttpStatus.CREATED);
    }
}
