package jbehave.converters;

import com.google.gson.Gson;
import lombok.Data;
import lombok.ToString;
import org.jbehave.core.steps.ParameterConverters;

import java.lang.reflect.Type;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public class ZonedDateTimeJsonConverter2 extends ParameterConverters.AbstractParameterConverter<ZonedDateTime> {
    @Override
    public ZonedDateTime convertValue(String value, Type type) {

        ComputableDateTimeDTO incoming = new Gson().fromJson(value, ComputableDateTimeDTO.class);
        System.out.println(incoming);
        return DateTimeComputer.compute(incoming);
    }

    @Data
    @ToString
    private static class ComputableDateTimeDTO
    {
        private String d;
        private String t;
        private String dt;
        private String tz;
        private String y;
        private String mo;
        private String day;
        private String h;
        private String mi;
        private String s;
    }

    public static class DateTimeComputer
    {
        private static final String SUPPORTED_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
        private static final String SUPPORTED_DATE_FORMAT = "yyyy-MM-dd";
        private static final String SUPPORTED_TIME_FORMAT = "HH:mm:ss";
        private static final String DEFAULT_TIME_ZONE = "Europe/Zurich";
        private static final Map<String, Supplier<LocalDateTime>> dateTimeParsingStrategies = new HashMap<>();
        static
        {
            dateTimeParsingStrategies.put("now", LocalDateTime::now);
        }

        private static final Map<String, Supplier<LocalDate>> dateParsingStrategies = new HashMap<>();
        static
        {
            dateParsingStrategies.put("now", LocalDate::now);
        }

        private static final Map<String, Supplier<LocalTime>> timeParsingStrategies = new HashMap<>();
        static
        {
            timeParsingStrategies.put("now", LocalTime::now);
        }


        public static ZonedDateTime compute (ComputableDateTimeDTO dto)
        {

            System.out.println("COMPUTING");
            ZoneId tz = ZoneId.of(Optional.ofNullable(dto.getTz()).orElse(DEFAULT_TIME_ZONE));
            LocalDateTime proto =  Optional.ofNullable(dto.getDt())
                                            .map(DateTimeComputer::parseDateTime)
                                            .orElseGet(() -> parseDateTime(dto.getD(),
                                                    Optional.ofNullable(dto.getT()).orElse("00:00:00")));
            proto = adjust(proto, dto);
            return ZonedDateTime.of(proto, tz);
        }

        private static LocalDateTime adjust(LocalDateTime proto, ComputableDateTimeDTO dto)
        {
            LocalDateTime adjust = proto;

            adjust = dto.getS() == null ? adjust : adjust.plusSeconds(Long.valueOf(dto.getS()));
            adjust = dto.getMi() == null ? adjust : adjust.plusMinutes(Long.valueOf(dto.getMi()));
            adjust = dto.getH() == null ? adjust : adjust.plusHours(Long.valueOf(dto.getH()));
            adjust = dto.getDay() == null ? adjust : adjustDays(adjust, dto.getDay());
            adjust = dto.getMo() == null ? adjust : adjust.plusMonths(Long.valueOf(dto.getMo()));
            adjust = dto.getY() == null ? adjust : adjust.plusYears(Long.valueOf(dto.getY()));
            return adjust;
        }

        private static LocalDateTime adjustDays(LocalDateTime since, String days)
        {
            if ("last".equals(days)) return since.with(TemporalAdjusters.lastDayOfMonth());
            else if ("first".equals(days)) return since.with(TemporalAdjusters.firstDayOfMonth());
            else return since.plusDays(Long.valueOf(days));
        }

        private static LocalDateTime parseDateTime(final String date)
        {
            System.out.println("PARSE DATETIME");
            return Optional.ofNullable(dateTimeParsingStrategies.get(date))
                            .orElse(() -> LocalDateTime.parse(date, DateTimeFormatter.ofPattern(SUPPORTED_DATE_TIME_FORMAT)))
                            .get();

        }

        private static LocalDateTime parseDateTime(final String date, final String time)
        {
            System.out.println("PARSE DATE TIME");

            LocalDate ld = Optional.ofNullable(dateParsingStrategies.get(date))
                    .orElse(() -> LocalDate.parse(date, DateTimeFormatter.ofPattern(SUPPORTED_DATE_FORMAT)))
                    .get();
            LocalTime lt = Optional.ofNullable(timeParsingStrategies.get(time))
                    .orElse(() -> LocalTime.parse(time, DateTimeFormatter.ofPattern(SUPPORTED_TIME_FORMAT)))
                    .get();

            return LocalDateTime.of(ld, lt);
        }
    }
}
