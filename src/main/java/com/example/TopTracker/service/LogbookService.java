package com.example.TopTracker.service;

import com.example.TopTracker.dto.LogbookDto;
import com.example.TopTracker.models.Attempt;
import com.example.TopTracker.models.Logbook;
import com.example.TopTracker.models.User;
import com.example.TopTracker.repository.AttemptRepository;
import com.example.TopTracker.repository.LogbookRepository;
import com.example.TopTracker.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LogbookService {
    private final LogbookRepository logbookRepository;
    private final AttemptRepository attemptRepository;
    private final UserRepository userRepository;

    public LogbookService(LogbookRepository logbookRepository, AttemptRepository attemptRepository, UserRepository userRepository) {
        this.logbookRepository = logbookRepository;
        this.attemptRepository = attemptRepository;
        this.userRepository = userRepository;
    }

    public LogbookDto createLogbook(LogbookDto logbookDto) {
        Logbook l = new Logbook();
        LogbookDto logbookDTO = new LogbookDto();

        User user = userRepository.findById(logbookDto.user_id).orElseThrow(() -> new RuntimeException("Not found"));
        l.setUser(user);
        l.setAttempts(logbookDto.getAttempts());

        Logbook logbook = logbookRepository.save(l);

        logbookDTO.setId(logbook.getId());
        logbookDTO.setUser_id(logbook.getUser().getUserId());
        logbookDTO.setAttempts(logbook.getAttempts());

        return logbookDTO;
    }

    public List<LogbookDto> getAllLogbooks() {
        List<LogbookDto> logbooks = new ArrayList<>();
        List<Logbook> logbookList = logbookRepository.findAll();

        for (Logbook l : logbookList) {
            LogbookDto logbookDto = new LogbookDto();
            logbookDto.id = l.getId();
            if (l.getUser() != null) {
                logbookDto.user_id = l.getUser().getUserId();
            }
            if (l.getAttempts() != null) {
                logbookDto.attempts = l.getAttempts();
            }
            logbooks.add(logbookDto);
        }
        return logbooks;
    }
}
