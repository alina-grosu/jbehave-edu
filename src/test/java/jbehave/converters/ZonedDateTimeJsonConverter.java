package jbehave.converters;

import com.google.gson.Gson;
import lombok.Data;
import lombok.ToString;
import org.jbehave.core.steps.ParameterConverters;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class ZonedDateTimeJsonConverter extends ParameterConverters.AbstractParameterConverter<ZonedDateTime> {

    private static final String DEFAULT_TIME_ZONE = "Europe/Zurich";
    private static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    @Override
    public boolean accept(Type type) {
        return super.accept(type);
    }

    @Override
    public ZonedDateTime convertValue(String value, Type type) {

        System.out.println("INCOMING" + value);
        DateTimeTemplate dtt = new Gson().fromJson(value, DateTimeTemplate.class);
        System.out.println(dtt);
        ZoneId zoneId = ZoneId.of(dtt.getTz() == null ? DEFAULT_TIME_ZONE : dtt.getTz());

        if (dtt.getDt() != null)
        {
            return ZonedDateTime.of(LocalDateTime.parse(dtt.getDt(), DateTimeFormatter.ofPattern(DATE_PATTERN)), zoneId);
        }
        else if (dtt.getSinceNow() != null && dtt.getSinceNow().size() != 0)
        {
            return SinceNowProcessor.process(dtt.getSinceNow(), zoneId);
        }

        return ZonedDateTime.now(ZoneId.of(DEFAULT_TIME_ZONE));
    }

    @Data
    @ToString
    private static class DateTimeTemplate
    {
        private String dt;
        private String tz;
        private List<String> sinceNow;
    }

    private static class SinceNowProcessor
    {

        private static final Map<String, BiFunction<String, ZonedDateTime, ZonedDateTime>> processors = new HashMap<>();
        static
        {
            processors.put("y", (delta, dateTime) -> dateTime.plusYears(Long.valueOf(delta.substring(0, delta.length() -1))));
            processors.put("M", (delta, dateTime) -> dateTime.plusMonths(Long.valueOf(delta.substring(0, delta.length() -1))));
            processors.put("d", (delta, dateTime) -> dateTime.plusDays(Long.valueOf(delta.substring(0, delta.length() -1))));
            processors.put("H", (delta, dateTime) -> dateTime.plusHours(Long.valueOf(delta.substring(0, delta.length() -1))));
            processors.put("m", (delta, dateTime) -> dateTime.plusMinutes(Long.valueOf(delta.substring(0, delta.length() -1))));
            processors.put("s", (delta, dateTime) -> dateTime.plusSeconds(Long.valueOf(delta.substring(0, delta.length() -1))));
            processors.put("ld", (delta, dateTime) -> dateTime.with(TemporalAdjusters.lastDayOfMonth()));
        }

        static ZonedDateTime process(List<String> dateMutators, ZoneId zoneId)
        {
            ZonedDateTime processed = ZonedDateTime.now(zoneId);
            for (String key : dateMutators)
            {
                processed = getProcessor(key).apply(key, processed);
            }

            return processed;
        }

        private static BiFunction<String, ZonedDateTime, ZonedDateTime> getProcessor(String key)
        {
            return processors.get(key) == null ? processors.get(String.valueOf(key.charAt(key.length() - 1))) : processors.get(key);
        }
    }

}
