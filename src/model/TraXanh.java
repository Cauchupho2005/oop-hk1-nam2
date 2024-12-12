package model;

public class TraXanh extends Nuoc{
    private String viTraXanh;

    public TraXanh() {
    }

    public TraXanh(String maVach, String ten, double giaBan, int soLuongTon, String viTraXanh) {
        super(maVach, ten, giaBan, soLuongTon);
        this.viTraXanh = viTraXanh;
    }

    public String getViTraXanh() {
        return viTraXanh;
    }

    public void setViTraXanh(String viTraXanh) {
        this.viTraXanh = viTraXanh;
    }
}
