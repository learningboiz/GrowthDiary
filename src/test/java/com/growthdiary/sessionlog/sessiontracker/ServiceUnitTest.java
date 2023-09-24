package com.growthdiary.sessionlog.sessiontracker;

import com.growthdiary.sessionlog.details.Details;
import com.growthdiary.sessionlog.details.DetailsService;
import com.growthdiary.sessionlog.feedback.Feedback;
import com.growthdiary.sessionlog.feedback.FeedbackService;
import com.growthdiary.sessionlog.session.Session;
import com.growthdiary.sessionlog.session.SessionDTO;
import com.growthdiary.sessionlog.session.SessionRepository;
import com.growthdiary.sessionlog.session.SessionService;
import com.growthdiary.sessionlog.time.Time;
import com.growthdiary.sessionlog.time.TimeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class ServiceUnitTest {

    @Mock
    private SessionRepository sessionRepository;

    @Mock
    private DetailsService detailsService;

    @Mock
    private TimeService timeService;

    @Mock
    private FeedbackService feedbackService;

    @InjectMocks
    private SessionService sessionService;


    public SessionDTO createSessionDTO() {
        String skill = "Spring Boot";
        String description = "Building web applications";

        LocalDateTime startPeriod = LocalDateTime.now();
        LocalDateTime endPeriod = LocalDateTime.now().plusMinutes(45);

        Integer productivity = 3;
        String distraction = "YouTube";

        return new SessionDTO(skill, description, startPeriod, endPeriod, productivity, distraction);
    }

    public Session createSession() {
        String skill = "Spring Boot";
        String description = "Building web applications";
        Details details = new Details(skill, description);

        LocalDateTime startPeriod = LocalDateTime.now();
        LocalDateTime endPeriod = LocalDateTime.now().plusMinutes(45);
        Time time = new Time(startPeriod, endPeriod);

        Integer productivity = 3;
        String distraction = "YouTube";
        Feedback feedback = new Feedback(productivity, distraction);

        return new Session(details, time, feedback);
    }

    @Test
    public void testSessionCreationService() {
        SessionDTO mockSessionDTO = createSessionDTO();
        Session expectedSession = createSession();

        Mockito.when(sessionRepository.save(Mockito.any(Session.class)))
                .thenReturn(expectedSession);

        Session createdSession = sessionService.createSession(mockSessionDTO);

        // Verify that the repo has been queried once
        Mockito.verify(sessionRepository, times(1))
                .save(Mockito.any(Session.class));
    }
}
