package model;

public class TraSua extends Nuoc{
    private String viTraSua;

    public TraSua() {
    }

    public TraSua(String maVach, String ten, double giaBan, int soLuongTon, String viTraSua) {
        super(maVach, ten, giaBan, soLuongTon);
        this.viTraSua = viTraSua;
    }

    public String getViTraSua() {
        return viTraSua;
    }

    public void setViTraSua(String viTraSua) {
        this.viTraSua = viTraSua;
    }
}
