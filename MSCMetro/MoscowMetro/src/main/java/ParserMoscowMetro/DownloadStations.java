package main.java.ParserMoscowMetro;

public class DownloadStations {
    private final String station;
    private final String line;

    public DownloadStations(String station, String line){
        this.station = station;
        this.line = line;
    }

    public String getStation() {
        return station;
    }

    public String getLine() {
        return line;
    }
}
