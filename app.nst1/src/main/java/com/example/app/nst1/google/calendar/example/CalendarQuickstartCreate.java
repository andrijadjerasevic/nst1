package com.example.app.nst1.google.calendar.example;

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
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.EventReminder;
import com.google.api.services.calendar.model.Events;

import java.io.*;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class CalendarQuickstartCreate {
  private static final String APPLICATION_NAME = "NST1";
  private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
  private static final String TOKENS_DIRECTORY_PATH = "tokens";
  private static final List<String> SCOPES =
      Collections.singletonList(CalendarScopes.CALENDAR_EVENTS);
  private static final String CREDENTIALS_FILE_PATH =
      "src/main/resources/credentials/credentials.json";

  private static final String COLOR_RED = "6";

  public CalendarQuickstartCreate() {}

  private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT)
      throws IOException {
    // Load client secrets.
    InputStream in = new FileInputStream(CREDENTIALS_FILE_PATH);
    if (in == null) {
      throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
    }
    GoogleClientSecrets clientSecrets =
        GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

    // Build flow and trigger user authorization request.
    GoogleAuthorizationCodeFlow flow =
        new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
            .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
            .setAccessType("offline")
            .build();
    LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
    Credential credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    // returns an authorized Credential object.
    return credential;
  }

  // List the next 10 events from the primary calendar.
  private static List<Event> listUpcomingEvents(Calendar service) throws IOException {
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
    List<Event> eventsItems = events.getItems();

    for (Event event : eventsItems) {
      System.out.println("Event Id -> " + event.getId());
      System.out.println("Event Summary -> " + event.getSummary());
    }
    return eventsItems;
  }

  public static void createEvent(Calendar service) {
    Date startDate = new org.joda.time.DateTime(new Date()).plusDays(1).toDate();
    Date endDate = new org.joda.time.DateTime(new Date()).plusDays(2).toDate();
    Event event =
        new Event()
            .setSummary("Google I/O 2022")
            .setColorId(COLOR_RED)
            .setLocation("Europe/Belgrade")
            .setDescription("A chance to hear more about Google's developer products.");

    DateTime startDateTime = new DateTime(startDate);
    EventDateTime start =
        new EventDateTime().setDateTime(startDateTime).setTimeZone("Europe/Belgrade");
    event.setStart(start);

    DateTime endDateTime = new DateTime(endDate);
    EventDateTime end = new EventDateTime().setDateTime(endDateTime).setTimeZone("Europe/Belgrade");
    event.setEnd(end);

    String[] recurrence = new String[] {"RRULE:FREQ=DAILY;COUNT=1"};
    event.setRecurrence(Arrays.asList(recurrence));

    //    EventAttendee[] attendees =
    //        new EventAttendee[] {
    //          new EventAttendee().setEmail("lpage@example.com"),
    //          new EventAttendee().setEmail("sbrin@example.com"),
    //        };
    //    event.setAttendees(Arrays.asList(attendees));

    EventReminder[] reminderOverrides =
        new EventReminder[] {
          new EventReminder().setMethod("email").setMinutes(24 * 60),
          new EventReminder().setMethod("popup").setMinutes(10),
        };
    Event.Reminders reminders =
        new Event.Reminders().setUseDefault(false).setOverrides(Arrays.asList(reminderOverrides));
    event.setReminders(reminders);

    String calendarId = "primary";
    try {
      event = service.events().insert(calendarId, event).execute();
    } catch (IOException e) {
      e.printStackTrace();
    }
    System.out.printf("Event created: %s\n", event.getHtmlLink());
  }

  public static void main(String... args) throws IOException, GeneralSecurityException {
    // Build a new authorized API client service.
    final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
    Calendar service =
        new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
            .setApplicationName(APPLICATION_NAME)
            .build();

    //    createEvent(service);
    listUpcomingEvents(service);
  }
}
