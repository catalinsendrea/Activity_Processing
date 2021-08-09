import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MonitoredData {

	LocalDateTime start_time;
	LocalDateTime end_time;
	String activity_label;

	MonitoredData(LocalDateTime start_timeArg, LocalDateTime end_timeArg, String activity_labelArg) {
		start_time = start_timeArg;
		end_time = end_timeArg;
		activity_label = activity_labelArg;
	}

	public LocalDateTime getstart_time() {
		return start_time;
	}

	public void setstart_time(LocalDateTime start_time) {
		this.start_time = start_time;
	}

	public LocalDateTime getend_time() {
		return end_time;
	}

	public void setend_time(LocalDateTime end_time) {
		this.end_time = end_time;
	}

	public String getactivity_label() {
		return activity_label;
	}

	public void setactivity_label(String activity_label) {
		this.activity_label = activity_label;
	}

	public static List<MonitoredData> createList() {

		List<MonitoredData> Lista = new ArrayList<>();

		String path_fisier = "C://Users/Qbec/Desktop/Activities.txt";

		try (Stream<String> stream = Files.lines(Paths.get(path_fisier))) {

			Lista = stream.map(e -> MonitoredData.transforma(e)).collect(Collectors.toList());

		} catch (IOException e) {
			e.printStackTrace();
		}

		return Lista;
	}

	public static MonitoredData transforma(String line) {

		String[] objects = line.split("\t\t");

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		return new MonitoredData(LocalDateTime.parse(objects[0], formatter), LocalDateTime.parse(objects[1], formatter),
				objects[2]);
	}

	public void Printare() {

		int size = createList().size();

		for (int i = 0; i < size; i++) {

			System.out.print(createList().get(i).getstart_time() + " ");
			System.out.print(createList().get(i).getend_time() + " ");
			System.out.print(createList().get(i).getactivity_label());
			System.out.println();
		}
	}

	public void numara_zile(List<MonitoredData> Lista) {

		int x = 0;
		x = (int) Lista.stream().map(e -> e.getstart_time().getDayOfMonth()).distinct().count();

		System.out.print("Numarul de zile diferite este: " + x);
	}

	public void numara_activitati(List<MonitoredData> Lista) {

		Map<String, Long> mapping_activity;

		mapping_activity = Lista.stream().map(MonitoredData::getactivity_label)
				.collect(Collectors.groupingBy(e -> e, Collectors.counting()));

		System.out.println(mapping_activity);
	}

}
