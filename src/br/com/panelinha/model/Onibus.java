package br.com.panelinha.model;

public class Onibus {

    private String linha;
    private long latitude;
    private long longitude;

    public Onibus() {
    }

    public Onibus(String linha, long latitude, long longitude) {
        this.linha = linha;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getLinha() {
        return linha;
    }

    public void setLinha(String linha) {
        this.linha = linha;
    }

    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Onibus{" + "linha=" + linha + ", latitude=" + latitude + ", longitude=" + longitude + '}';
    }
}
