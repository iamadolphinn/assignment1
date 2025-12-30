import java.util.ArrayList;

public class ContentDemo {

    public static void main(String[] args) {

        ArrayList<ContentItem> items = new ArrayList<>();

        items.add(new VideoLecture("Java Basics", 2022, 45, "HD"));
        items.add(new VideoLecture("OOP Explained", 2023, 60, "4K"));

        items.add(new PodcastEpisode("Tech Talk", 2021, 30, "Alice"));
        items.add(new PodcastEpisode("History Hour", 2020, 50, "Bob"));

        int currentYear = java.time.Year.now().getValue();

        for (ContentItem item : items) {
            System.out.println(item.toString()
                    + " | licenseCost=" + item.getLicenseCost(currentYear));

            if (item instanceof Downloadable) {
                Downloadable d = (Downloadable) item;
                d.download();
                System.out.println("Max downloads/day = " + d.getMaxDownloadsPerDay());
            }

            System.out.println();
        }
    }
}
