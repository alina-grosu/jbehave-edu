package jbehave.dates;

import org.jbehave.core.annotations.AsJson;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalUnit;

@AsJson
public class CustomDate {


    public static void main (String[] args)
    {
        LocalDateTime ldtLast = LocalDateTime.of(2018, Month.OCTOBER, 31, 23, 59, 59);
        LocalDateTime ldtFirst = LocalDateTime.of(2018, Month.OCTOBER, 01, 00, 00, 01);
        System.out.println(ldtLast);
        ZonedDateTime zdtK = ZonedDateTime.of(ldtLast, ZoneId.of("Europe/Kiev"));
        System.out.println(zdtK);
        ZonedDateTime zdtZ = ZonedDateTime.of(ldtLast, ZoneId.of("Europe/Zurich"));
        System.out.println(zdtZ);

        LocalDateTime ldt1 = LocalDateTime.of(2018, Month.OCTOBER, 15, 23, 59, 59);
        System.out.println(ldt1);
        ZonedDateTime zdtK1 = ZonedDateTime.of(ldt1, ZoneId.of("Europe/Kiev"));
        System.out.println(zdtK1);
        ZonedDateTime zdtZ1 = ZonedDateTime.of(ldt1, ZoneId.of("Europe/Zurich"));
        System.out.println(zdtZ1);

        //System.out.println(LocalDateTime.of(ZoneOffset.UTC));
        System.out.println(ldtLast.atOffset(ZoneOffset.UTC));

        ZonedDateTime zdt = ZonedDateTime.of(ldtLast, ZoneOffset.UTC);
        System.out.println(zdt);
        System.out.println(zdt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss Z")));
        System.out.println(zdt.toOffsetDateTime());

        System.out.println(ZonedDateTime.now());
        System.out.println(ZonedDateTime.now(ZoneId.of("Europe/Zurich")));
        System.out.println(ZonedDateTime.now(ZoneId.of("+0100")));
        System.out.println(ZonedDateTime.of(ldtLast, ZoneOffset.UTC));
        System.out.println(ZonedDateTime.of(ldtLast, ZoneOffset.UTC).withZoneSameInstant(ZoneId.of("Europe/Kiev")));
        System.out.println(ZonedDateTime.of(ldtLast, ZoneOffset.UTC).withZoneSameInstant(ZoneId.of("Europe/Zurich")));
        System.out.println(ZonedDateTime.of(ldtFirst, ZoneOffset.UTC).withZoneSameInstant(ZoneId.of("Europe/Kiev")));
        System.out.println(ZonedDateTime.of(ldtFirst, ZoneOffset.UTC).withZoneSameInstant(ZoneId.of("Europe/Zurich")));
        System.out.println(ZonedDateTime.of(ldtFirst, ZoneOffset.UTC).withZoneSameInstant(ZoneId.of("UTC")));
        System.out.println("foo");
        System.out.println(ZonedDateTime.of(ldtLast, ZoneId.of("Europe/Kiev")).withZoneSameInstant(ZoneId.of("UTC")));
        System.out.println(ZonedDateTime.of(ldtLast, ZoneId.of("Europe/Zurich")).withZoneSameInstant(ZoneId.of("UTC")));
        System.out.println(ZonedDateTime.of(ldtFirst, ZoneId.of("Europe/Kiev")).withZoneSameInstant(ZoneId.of("UTC")));
        System.out.println(ZonedDateTime.of(ldtFirst, ZoneId.of("Europe/Zurich")).withZoneSameInstant(ZoneId.of("UTC")));
        System.out.println(ZonedDateTime.of(ldtFirst, ZoneOffset.UTC).withZoneSameInstant(ZoneId.of("UTC")));


        System.out.println(LocalDateTime.now().plus(-1, ChronoUnit.HOURS));
        System.out.println(LocalDateTime.now().plus(Integer.valueOf("+1"), ChronoUnit.DAYS));

        LocalDateTime lastd = LocalDateTime.now().with(TemporalAdjusters.lastDayOfMonth());
        System.out.println(lastd);
        System.out.println(lastd.plusMonths(2));
    }
}
