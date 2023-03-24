package com.example.TopTracker.service;

import com.example.TopTracker.dto.AttemptDto;
import com.example.TopTracker.models.Attempt;
import com.example.TopTracker.repository.AttemptRepository;
import org.springframework.stereotype.Service;

@Service
public class AttemptService {
    private final AttemptRepository attemptRepo;

    public AttemptService(AttemptRepository attemptRepo) {
        this.attemptRepo = attemptRepo;
    }

    public Attempt addAttempt(AttemptDto attemptDto) {
        Attempt a = new Attempt();
        a.setSend(attemptDto.send);
        a.setNotes(attemptDto.notes);
        a.setVideo(attemptDto.video);

        attemptRepo.save(a);

        a.getId();

        attemptRepo.save(a);

        return a;
    }
}
