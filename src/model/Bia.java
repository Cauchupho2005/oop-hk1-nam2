package model;

public class Bia extends Nuoc{
    private float nongDo;

    public Bia() {
    }

    public Bia(String maVach, String ten, double giaBan, int soLuongTon, float nongDo) {
        super(maVach, ten, giaBan, soLuongTon);
        this.nongDo = nongDo;
    }

    public float getNongDo() {
        return nongDo;
    }

    public void setNongDo(float nongDo) {
        this.nongDo = nongDo;
    }
}
