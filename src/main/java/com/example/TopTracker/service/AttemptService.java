package com.example.TopTracker.service;

import com.example.TopTracker.dto.AttemptDto;

import com.example.TopTracker.models.Attempt;
import com.example.TopTracker.models.Block;
import com.example.TopTracker.models.User;
import com.example.TopTracker.repository.AttemptRepository;
import com.example.TopTracker.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AttemptService {
    private final AttemptRepository attemptRepository;
    private final UserRepository userRepository;

    public AttemptService(AttemptRepository attemptRepository, UserRepository userRepository) {
        this.attemptRepository = attemptRepository;
        this.userRepository = userRepository;
    }

    public AttemptDto addAttempt(AttemptDto attemptDto) {
        Attempt a = new Attempt();
        AttemptDto attemptDTO = new AttemptDto();

        a.setSend(attemptDto.send);
        a.setNotes(attemptDto.notes);
        a.setVideo(attemptDto.video);

        if (attemptDto.user_id != null ) {
            Optional<User> userOptional = userRepository.findById(attemptDto.user_id);

            if (userOptional.isPresent()) {
                a.setUser_id(userOptional.get().getId());
            }
        }

        Attempt attempt = attemptRepository.save(a);
        attemptDTO.setSend(attempt.isSend());
        attemptDTO.setNotes(attemptDto.notes);
        attemptDTO.setVideo(attemptDto.getVideo());
        attemptDTO.setUser_id(attemptDto.getUser_id());

        attempt.setId(attempt.getId());

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
            attempts.add(attemptDto);
//            attemptDto.video = a.getVideo();
            attemptDto.setUser_id(a.getUser_id());

            attempts.add(attemptDto);
        }


        return attempts;
    }
}
