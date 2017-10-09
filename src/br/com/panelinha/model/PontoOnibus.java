package br.com.panelinha.model;



public class PontoOnibus {

    private String endereco;
    private long latitude;
    private long longitude;

    public PontoOnibus(String endereco, long latitude, long longitude) {
        this.endereco = endereco;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
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
        return "PontoOnibus{" + "endereco=" + endereco + ", latitude=" + latitude + ", longitude=" + longitude + '}';
    }

}
