package StreamFilterTest;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class StreamFilterTest {
    public static void main(String[] args) {
        HashSet<String> totalVehicle = new HashSet<>();
        totalVehicle.add("localRegionA123");
        totalVehicle.add("remoteRegionB456");
        totalVehicle.add("localRegionC789");

        // 使用Stream API过滤出以"localRegion"开头的字符串
        Set<String> filteredVehicles = totalVehicle.stream()
                .filter(vehicle -> vehicle.startsWith("localRegion"))
                .collect(Collectors.toSet());
        Set<String> filteredVehicles2 = totalVehicle.stream()
                .filter(vehicle -> !vehicle.startsWith("localRegion"))
                .collect(Collectors.toSet());

        System.out.println("Filtered Vehicles: " + filteredVehicles);
        System.out.println("Filtered Vehicles: " + filteredVehicles2);
        System.out.println(Boolean.FALSE.equals(null));
    }
}
