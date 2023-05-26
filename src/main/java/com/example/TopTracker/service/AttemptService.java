package com.example.TopTracker.service;

import com.example.TopTracker.dto.AttemptDto;

import com.example.TopTracker.exeption.ResourceNotFoundException;
import com.example.TopTracker.models.Attempt;
import com.example.TopTracker.models.Boulder;
import com.example.TopTracker.models.Logbook;
import com.example.TopTracker.models.User;
import com.example.TopTracker.repository.AttemptRepository;
import com.example.TopTracker.repository.BoulderRepository;
import com.example.TopTracker.repository.LogbookRepository;
import com.example.TopTracker.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AttemptService {
    private final AttemptRepository attemptRepository;
    private final UserRepository userRepository;

    private final BoulderRepository boulderRepository;

    private final LogbookRepository logbookRepository;


    public AttemptService(AttemptRepository attemptRepository, UserRepository userRepository, BoulderRepository boulderRepository, LogbookRepository logbookRepository) {
        this.attemptRepository = attemptRepository;
        this.userRepository = userRepository;
        this.boulderRepository = boulderRepository;
        this.logbookRepository = logbookRepository;
    }

    public AttemptDto addAttempt(AttemptDto attemptDto) {
        Attempt a = new Attempt();
        AttemptDto attemptDTO = new AttemptDto();

        a.setSend(attemptDto.send);
        a.setNotes(attemptDto.notes);
        a.setVideo(attemptDto.video);

        Boulder boulder = boulderRepository.findById(attemptDto.boulder_id).orElseThrow(() -> new RuntimeException("Not found"));
        a.setBoulder(boulder);

        User user = userRepository.findById(attemptDto.user_id).orElseThrow(() -> new RuntimeException("Not found"));
        a.setUser(user);

        Logbook logbook = logbookRepository.findById(attemptDto.logbook_id).orElseThrow(() -> new RuntimeException("Not found"));
        a.setLogbook(logbook);


        Attempt attempt = attemptRepository.save(a);
        attemptDTO.setSend(attempt.isSend());
        attemptDTO.setNotes(attemptDto.notes);
        attemptDTO.setVideo(attemptDto.getVideo());
        attemptDTO.setUser_id(attemptDto.getUser_id());
        attemptDTO.setId(attempt.getId());
        attemptDTO.setBoulder_id(attemptDto.boulder_id);
        attemptDTO.setLogbook_id(attemptDto.logbook_id);


        return attemptDTO;
    }

    public List<AttemptDto> getAllAttempts() {
        List<AttemptDto> attempts = new ArrayList<>();
        List<Attempt> attemptList = attemptRepository.findAll();

        for (Attempt a : attemptList) {
            AttemptDto attemptDto = new AttemptDto();
            attemptDto.id = a.getId();
            attemptDto.notes = a.getNotes();
            attemptDto.send = a.isSend();
            attemptDto.video = a.getVideo();

            if (a.getBoulder() != null) {
                attemptDto.setBoulder_id(a.getBoulder().getId());
            }

            if (a.getUser() != null) {
                attemptDto.setUser_id(a.getUser().getUserId());
            }

            if (a.getLogbook() != null) {
                attemptDto.setLogbook_id(a.getLogbook().getId());
            }

            attempts.add(attemptDto);
        }


        return attempts;
    }

    public AttemptDto updateAttempt(Long id, AttemptDto attemptDto) {

        Attempt a = attemptRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found"));

        a.setSend(attemptDto.send);
        a.setNotes(attemptDto.notes);
        a.setVideo(attemptDto.video);

        attemptRepository.save(a);

        return attemptDto;
    }

    public void deleteAttemptById(Long id) {
        attemptRepository.deleteById(id);
    }


}
