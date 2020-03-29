package io.zipcoder.tc_spring_poll_application.controllers;
import io.zipcoder.tc_spring_poll_application.domain.Vote;
import io.zipcoder.tc_spring_poll_application.dtos.OptionCount;
import io.zipcoder.tc_spring_poll_application.dtos.VoteResult;
import io.zipcoder.tc_spring_poll_application.repositories.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
public class ComputeResultController {


    private VoteRepository voteRepository;

    @Autowired
    public ComputeResultController(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    @GetMapping("/computeresult")
    public ResponseEntity<?> computeResult(@RequestParam Long pollId) {
        VoteResult voteResult = new VoteResult();
        Iterable<Vote> allVotes = voteRepository.findVotesByPoll(pollId);
        //TODO: Implement algorithm to count votes
        int totalVotes = 0;
        Map<Long, OptionCount> tempMap = new HashMap<>();
        for(Vote v : allVotes) {
            totalVotes ++;
            Long key = v.getOption().getId();
            if(tempMap.get(key) == null) {
                OptionCount optionCount = new OptionCount();
                optionCount.setOptionId(key);
                optionCount.setCount(1);
                tempMap.put(key, optionCount);
            } else{
                tempMap.get(key).setCount(tempMap.get(key).getCount()+1);
            }
        }
        voteResult.setTotalVotes(totalVotes);
        voteResult.setResults(tempMap.values());

        return new ResponseEntity<VoteResult>(voteResult, HttpStatus.OK);
    }
}
