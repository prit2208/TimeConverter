import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TimeConverter {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/timezones_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password";

    public static void main(String[] args) {
        String country = "United States";
        String convertedTime = convertTimeByCountry(country);
        System.out.println("Converted time in " + country + ": " + convertedTime);
    }

    public static String convertTimeByCountry(String country) {
        String convertedTime = null;

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT time_zone FROM countries WHERE country_name = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, country);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String timeZone = resultSet.getString("time_zone");
                ZoneId zoneId = ZoneId.of(timeZone);
                ZonedDateTime currentTime = ZonedDateTime.now();
                ZonedDateTime convertedDateTime = currentTime.withZoneSameInstant(zoneId);

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                convertedTime = convertedDateTime.format(formatter);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return convertedTime;
    }
}
