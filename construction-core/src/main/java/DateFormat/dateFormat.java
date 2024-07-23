package DateFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class dateFormat {
    public static void main(String[] args) {
        System.out.println(reFormatDateWithYMDV2("2022-07-12 14:22:16","yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd"));
    }

    private static String reFormatDateWithYMD(String queryTime, String sourceDateFormat, String destDateFormat) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(sourceDateFormat);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(destDateFormat);
        // 将字符串转换为LocalDateTime
        LocalDateTime localDateTime = LocalDateTime.parse(queryTime, dateTimeFormatter);
        // 将LocalDateTime转换为LocalDate
        LocalDate localDate = localDateTime.toLocalDate();
        // 将LocalDate转换回字符串
        return localDate.format(dateFormatter);
    }

    private static String reFormatDateWithYMDV2(String queryTime, String sourceDateFormat, String destDateFormat) {
        DateTimeFormatter sourceFormatter = DateTimeFormatter.ofPattern(sourceDateFormat);
        DateTimeFormatter destFormatter = DateTimeFormatter.ofPattern(destDateFormat);

        // 将字符串转换为LocalDateTime
        LocalDateTime localDateTime = LocalDateTime.parse(queryTime, sourceFormatter);

        // 将LocalDateTime转换回字符串
        return localDateTime.format(destFormatter);
    }
}
