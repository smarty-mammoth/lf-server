package lazyfarm.server;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class SheduledTasks {
    public static int CurrVal = 0;
    public static double TemperatureCPU;

    @Scheduled(fixedRate = 3000)
    public void incCurrentTime() {

        System.out.print(String.format("Curr val = %d", CurrVal++));
    }
    @Scheduled(fixedRate = 1000)
    public void readTemperature() {
        File file = new File("/sys/class/thermal/thermal_zone0/temp");
        if (file.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line = br.readLine().replace("\n", "").replace("\r", "").trim();
                TemperatureCPU = Double.parseDouble(line) / 1000.0;
            }
            catch (IOException exp) {
                System.out.println(String.format("Read file error: %s", file.getAbsolutePath()));

            }
        }

    }
}
