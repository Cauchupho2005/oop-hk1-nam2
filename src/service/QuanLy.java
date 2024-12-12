package service;

import java.util.List;
import java.util.Optional;

public interface QuanLy<T> {
    T timKiem(String ma);

    void them(T object);

    void sua(String ma, T object);

    void xoa(String ma);

    void getMenu();

    void hienThi();
}
