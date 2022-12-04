package main.java.ParserMoscowMetro;

public class DownloadConnections {
    private final String lineIn;
    private final String nameIn;
    private final String lineTo;
    private final String nameTo;

    public DownloadConnections(String lineIn, String nameIn, String lineTo, String nameTo){
        this.lineIn = lineIn;
        this.nameIn = nameIn;
        this.lineTo = lineTo;
        this.nameTo = nameTo;
    }

    public String getLineIn() {
        return lineIn;
    }

    public String getNameIn() {
        return nameIn;
    }

    public String getLineTo() {
        return lineTo;
    }

    public String getNameTo() {
        return nameTo;
    }
}
