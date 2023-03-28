package com.example.TopTracker.service;

import com.example.TopTracker.dto.AreaDto;
import com.example.TopTracker.dto.AttemptDto;
import com.example.TopTracker.models.Area;
import com.example.TopTracker.models.Attempt;
import com.example.TopTracker.repository.AttemptRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

        attemptDto.id = a.getId();

        attemptRepo.save(a);

        return a;
    }

    public List<AttemptDto> getAllAttempts() {
        List<AttemptDto> attempts = new ArrayList<>();
        List<Attempt> attemptList = attemptRepo.findAll();
        AttemptDto attemptDto = new AttemptDto();
        for (Attempt a : attemptList) {

            attemptDto.id = a.getId();
            attemptDto.notes = a.getNotes();
            attemptDto.send = a.isSend();
            attempts.add(attemptDto);
//            attemptDto.video = a.getVideo();
        }


        return attempts;
    }
}
