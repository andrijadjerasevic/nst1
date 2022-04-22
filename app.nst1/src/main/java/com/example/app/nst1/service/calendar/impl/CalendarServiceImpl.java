package com.example.app.nst1.service.calendar.impl;

import com.example.app.nst1.model.Project;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.*;
import org.springframework.stereotype.Service;

import java.io.*;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class CalendarServiceImpl {

  //  getCredentials constants
  private static final String APPLICATION_NAME = "NST1";
  private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
  private static final String TOKENS_DIRECTORY_PATH = "tokens";
  private static final List<String> SCOPES =
      Collections.singletonList(CalendarScopes.CALENDAR_EVENTS);
  private static final String CREDENTIALS_FILE_PATH =
      "src/main/resources/credentials/credentials.json";

  //  createEvent constants
  private static final String TIME_ZONE_BELGRADE = "Europe/Belgrade";
  private static final String EVENT_RECURRENCE = "RRULE:FREQ=DAILY;COUNT=1";
  private static final String CALENDAR_ID = "primary";

  private static final String DEFAULT_MAIL = "andrija.djerasevic@gmail.com";

  public List<Event> listUpcomingEvents(Calendar service) throws IOException {
    DateTime now = new DateTime(System.currentTimeMillis());
    Events events =
        service
            .events()
            .list("primary")
            .setMaxResults(10)
            .setTimeMin(now)
            .setOrderBy("startTime")
            .setSingleEvents(true)
            .execute();
    return events.getItems();
  }

  public Event findEvent(Calendar service, Long projectId, List<Event> events) throws Exception {
    for (Event event : events) {
      if (event.getId().equals(projectId.toString())) {
        return event;
      }
    }
    throw new Exception("Event not found!");
  }

  public Event createEvent(Calendar service, Project project) throws Exception {

    Event event =
        new Event()
            .setSummary(project.getProjectName())
            .setSummary(project.getProjectName())
            .setLocation(project.getProjectLocation())
            .setDescription(project.getProjectDescription());

    DateTime startDate = new DateTime(project.getStartDate());
    EventDateTime start =
        new EventDateTime().setDateTime(startDate).setTimeZone(TIME_ZONE_BELGRADE);
    event.setStart(start);

    DateTime endDate = new DateTime(project.getEndDate());
    EventDateTime end = new EventDateTime().setDateTime(endDate).setTimeZone(TIME_ZONE_BELGRADE);
    event.setEnd(end);

    String[] recurrence = new String[] {EVENT_RECURRENCE};
    event.setRecurrence(Arrays.asList(recurrence));

    List<EventAttendee> attendees = new ArrayList<>();
    project
        .getEmployees()
        .forEach(
            e -> {
              attendees.add(
                  new EventAttendee()
                      .setEmail(
                          e.getEmployeeEmail() != null ? e.getEmployeeEmail() : DEFAULT_MAIL));
            });

    event.setAttendees(attendees);

    EventReminder[] reminderOverrides =
        new EventReminder[] {
          new EventReminder().setMethod("email").setMinutes(24 * 60),
          new EventReminder().setMethod("popup").setMinutes(10),
        };
    Event.Reminders reminders =
        new Event.Reminders().setUseDefault(false).setOverrides(Arrays.asList(reminderOverrides));
    event.setReminders(reminders);

    event = service.events().insert(CALENDAR_ID, event).execute();
    System.out.printf("Event created: %s\n", event.getHtmlLink());
    return event;
  }

  public Credential getCredentials(NetHttpTransport HTTP_TRANSPORT) throws IOException {
    InputStream in = new FileInputStream(CREDENTIALS_FILE_PATH);
    if (in == null) {
      throw new FileNotFoundException("Resources not found: " + CREDENTIALS_FILE_PATH);
    }
    GoogleClientSecrets clientSecrets =
        GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

    GoogleAuthorizationCodeFlow flow =
        new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
            .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
            .setAccessType("offline")
            .build();
    LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
    Credential credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    return credential;
  }

  public Calendar initializeNewAuthorization() throws IOException, GeneralSecurityException {
    final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
    return new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
        .setApplicationName(APPLICATION_NAME)
        .build();
  }
}
