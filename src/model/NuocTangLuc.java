package model;

public class NuocTangLuc extends Nuoc {
    private float doCaffein;

    public NuocTangLuc() {
    }

    public NuocTangLuc(String maVach, String ten, double giaBan, int soLuongTon, float doCaffein) {
        super(maVach, ten, giaBan, soLuongTon);
        this.doCaffein = doCaffein;
    }

    public float getDoCaffein() {
        return doCaffein;
    }

    public void setDoCaffein(float doCaffein) {
        this.doCaffein = doCaffein;
    }
}
