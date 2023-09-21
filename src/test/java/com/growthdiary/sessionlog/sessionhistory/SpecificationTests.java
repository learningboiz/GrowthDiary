package com.growthdiary.sessionlog.sessionhistory;

import com.growthdiary.sessionlog.details.Details;
import com.growthdiary.sessionlog.feedback.Feedback;
import com.growthdiary.sessionlog.history.HistoryService;
import com.growthdiary.sessionlog.session.Session;
import com.growthdiary.sessionlog.session.SessionRepository;
import com.growthdiary.sessionlog.session.SessionService;
import com.growthdiary.sessionlog.time.Time;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class SpecificationTests {

    @Mock
    private SessionRepository sessionRepository;

    @InjectMocks
    private HistoryService historyService;

    @Test
    public void testGetAllSessions() {
        // Initialise dummy sessions
        Details details1 = new Details("Java", "Programming Language");
        Details details2 = new Details("Python", "Programming Language");
        Time time1 = new Time(LocalDateTime.now(), LocalDateTime.now().plusMinutes(45));
        Time time2 = new Time(LocalDateTime.now().plusDays(1), LocalDateTime.now().plusMinutes(45));
        Feedback feedback1 = new Feedback(4, "YouTube");
        Feedback feedback2 = new Feedback(3, "Reddit");
        Session session1 = new Session(details1, time1, feedback1);
        Session session2 = new Session(details2, time2, feedback2);

        // Add sessions to list
        List<Session> sessionList = new ArrayList<>();
        sessionList.add(session1);
        sessionList.add(session2);

        Mockito.when(sessionRepository.findAll(Mockito.any(Specification.class)))
                .thenReturn(sessionList);

        List<Session> results = historyService.getAllSessions();

        // Verify that the repo has been queried once
        Mockito.verify(sessionRepository, times(1))
                .findAll(Mockito.any(Specification.class));
    }


}
