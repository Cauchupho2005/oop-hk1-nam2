package service;

import model.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class QuanLyNuoc implements QuanLy<Nuoc> {
    private static List<Nuoc> danhSachNuoc;

    public QuanLyNuoc() {
        danhSachNuoc = new ArrayList<>();
    }

    public static List<Nuoc> getDanhSach() {
        return danhSachNuoc;
    }

    public static boolean checkMaVach(String maVach) {
        for (Nuoc nuoc : danhSachNuoc) {
            if (nuoc.getMaVach().equals(maVach)) {
                return true;
            }
        }
        return false;
    }

    public static double getGiaBan(String maVach) {
        for (Nuoc nuoc : danhSachNuoc) {
            if (nuoc.getMaVach().equals(maVach)) {
                return nuoc.getGiaBan();
            }
        }
        return 0;
    }

    public static boolean checkSLT(String maVach, int soLuongTon) {
        for (Nuoc nuoc : danhSachNuoc) {
            if (nuoc.getMaVach().equals(maVach)) {
                if (nuoc.getSoLuongTon() < soLuongTon) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void updateSLT(String maVach, int soLuongTon) {
        for (Nuoc nuoc : danhSachNuoc) {
            if (nuoc.getMaVach().equals(maVach)) {
                nuoc.setSoLuongTon(nuoc.getSoLuongTon() - soLuongTon);
            }
        }
    }

    @Override
    public Nuoc timKiem(String ma) {
        Optional<Nuoc> nuoc = danhSachNuoc.stream().filter(n -> n.getMaVach().equals(ma)).findFirst();
        return nuoc.orElse(null);
    }

    @Override
    public void them(Nuoc nuoc) {
        switch (nuoc.getClass().getSimpleName()) {
            case "TraSua":
                danhSachNuoc.add((TraSua) nuoc);
                break;
            case "TraXanh":
                danhSachNuoc.add((TraXanh) nuoc);
                break;
            case "NuocTangLuc":
                danhSachNuoc.add((NuocTangLuc) nuoc);
                break;
            case "Bia":
                danhSachNuoc.add((Bia) nuoc);
                break;
            default:
                break;
        }

       System.out.println("Thêm thành công");
    }

    @Override
    public void sua(String ma, Nuoc dataUpdate) {
        Nuoc nuoc = timKiem(ma);

        switch (dataUpdate.getClass().getSimpleName()) {
            case "TraSua":
                nuoc = (TraSua) dataUpdate;
                nuoc.setTen(dataUpdate.getTen());
                nuoc.setGiaBan(dataUpdate.getGiaBan());
                nuoc.setSoLuongTon(dataUpdate.getSoLuongTon());
                ((TraSua) nuoc).setViTraSua(((TraSua) dataUpdate).getViTraSua());
                break;
            case "TraXanh":
                nuoc = (TraXanh) dataUpdate;
                nuoc.setTen(dataUpdate.getTen());
                nuoc.setGiaBan(dataUpdate.getGiaBan());
                nuoc.setSoLuongTon(dataUpdate.getSoLuongTon());
                ((TraXanh) nuoc).setViTraXanh(((TraXanh) dataUpdate).getViTraXanh());
                break;
            case "NuocTangLuc":
                nuoc = (NuocTangLuc) dataUpdate;
                nuoc.setTen(dataUpdate.getTen());
                nuoc.setGiaBan(dataUpdate.getGiaBan());
                nuoc.setSoLuongTon(dataUpdate.getSoLuongTon());
                ((NuocTangLuc) nuoc).setDoCaffein(((NuocTangLuc) dataUpdate).getDoCaffein());
                break;
            case "Bia":
                nuoc = (Bia) dataUpdate;
                nuoc.setTen(dataUpdate.getTen());
                nuoc.setGiaBan(dataUpdate.getGiaBan());
                nuoc.setSoLuongTon(dataUpdate.getSoLuongTon());
                ((Bia) nuoc).setNongDo(((Bia) dataUpdate).getNongDo());
                break;
            default:
                break;
        }
        nuoc.setMaVach(ma);
        danhSachNuoc.set(danhSachNuoc.indexOf(timKiem(ma)), nuoc);

        System.out.println("Sửa thành công");
    }

    @Override
    public void xoa(String ma) {
        Nuoc nuoc = timKiem(ma);

        if (nuoc == null) {
            System.out.println("Không tìm thấy nước uống");
            return;
        }

        danhSachNuoc.remove(nuoc);
        System.out.println("Xóa thành công");
    }

    @Override
    public void getMenu() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Quản lý nước uống");
        while (true) {
            Utils.clearConsole();
            System.out.println("1. Thêm nước uống");
            System.out.println("2. Sửa thông tin nước uống");
            System.out.println("3. Xóa nước uống");
            System.out.println("4. Hiển thị danh sách nước uống");
            System.out.println("5. Thoát");
            System.out.print("Chọn chức năng: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    Nuoc nuoc = inputNuoc("them", null);
                    them(nuoc);
                    break;
                case 2:
                    System.out.print("Nhập mã vạch nước uống cần sửa: ");
                    String maVach = scanner.nextLine();
                    Nuoc nuocTimKiem = timKiem(maVach);
                    if (nuocTimKiem == null) {
                        System.out.println("Không tìm thấy nước uống");
                        break;
                    }
                    Nuoc nuocUpdate = inputNuoc("sua", nuocTimKiem.getClass().getSimpleName());
                    sua(maVach, nuocUpdate);
                    break;
                case 3:
                    System.out.print("Nhập mã vạch nước uống cần xóa: ");
                    String maVachXoa = scanner.nextLine();
                    xoa(maVachXoa);
                    break;
                case 4:
                    hienThi();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Chọn chức năng không hợp lệ");
                    break;
            }

            System.out.println("Nhấn Enter để tiếp tục...");
            scanner.nextLine();
        }
    }

    @Override
    public void hienThi() {
        System.out.println("Danh sách nước uống");
        System.out.printf("%-15s %-20s %-10s %-15s %-20s%n",
                "Mã vạch", "Tên", "Giá bán", "Số lượng tồn", "Chi tiết");
        System.out.println("========================================================================================");

        // In dữ liệu từng dòng
        for (Nuoc nuoc : danhSachNuoc) {
            String chiTiet = "";
            if (nuoc instanceof TraSua) {
                chiTiet = "Vị trà sữa: " + ((TraSua) nuoc).getViTraSua();
            } else if (nuoc instanceof TraXanh) {
                chiTiet = "Vị trà xanh: " + ((TraXanh) nuoc).getViTraXanh();
            } else if (nuoc instanceof NuocTangLuc) {
                chiTiet = "Độ caffeine: " + ((NuocTangLuc) nuoc).getDoCaffein();
            } else if (nuoc instanceof Bia) {
                chiTiet = "Nồng độ: " + ((Bia) nuoc).getNongDo();
            }

            System.out.printf("%-15s %-20s %-10.2f %-15d %-20s%n",
                    nuoc.getMaVach(), nuoc.getTen(), nuoc.getGiaBan(), nuoc.getSoLuongTon(), chiTiet);
        }
    }

    // Thêm nước uống
   private Nuoc inputNuoc(String type, String className) {
        Scanner scanner = new Scanner(System.in);

        String maVach = "";
        int choice = 0;
        if (type.equals("them") ) {
            System.out.println("1. Trà sữa");
            System.out.println("2. Trà xanh");
            System.out.println("3. Nước tăng lực");
            System.out.println("4. Bia");
            System.out.print("Chọn loại nước uống: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Nhập mã vạch: ");
            maVach = scanner.nextLine();
        } else if (type.equals("sua")) {
            switch (className) {
                case "TraSua":
                    choice = 1;
                    break;
                case "TraXanh":
                    choice = 2;
                    break;
                case "NuocTangLuc":
                    choice = 3;
                    break;
                case "Bia":
                    choice = 4;
                    break;
                default:
                    break;
            }
        }
        System.out.print("Nhập tên: ");
        String ten = scanner.nextLine();
        System.out.print("Nhập giá bán: ");
        double giaBan = 0;
        try {
            giaBan = scanner.nextDouble();
            System.out.print("Nhập số lượng tồn: ");
        } catch (Exception e) {
            System.out.println("Giá bán phải là số");
            return null;
        }
        int soLuongTon = 0;
        try {
            soLuongTon = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Số lượng tồn phải là số");
            return null;
        }

        switch (choice) {
            case 1:
                System.out.print("Nhập vị trà sữa: ");
                scanner.nextLine();
                String viTraSua = scanner.nextLine();
                return new TraSua(maVach, ten, giaBan, soLuongTon, viTraSua);
            case 2:
                System.out.print("Nhập vị trà xanh: ");
                scanner.nextLine();
                String viTraXanh = scanner.nextLine();
                return new TraXanh(maVach, ten, giaBan, soLuongTon, viTraXanh);
            case 3:
                System.out.print("Nhập độ caffeine: ");
                float doCaffein = 0;
                try {
                    doCaffein = scanner.nextFloat();
                } catch (Exception e) {
                    System.out.println("Độ caffeine phải là số");
                    return null;
                }
                return new NuocTangLuc(maVach, ten, giaBan, soLuongTon, doCaffein);
            case 4:
                System.out.print("Nhập nồng độ: ");
                float nongDo = 0;
                try {
                    nongDo = scanner.nextFloat();
                } catch (Exception e) {
                    System.out.println("Nồng độ phải là số");
                    return null;
                }
                return new Bia(maVach, ten, giaBan, soLuongTon, nongDo);
            default:
                System.out.println("Chọn loại nước uống không hợp lệ");
                return null;
        }
    }

    public void importData(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 6) {
                    System.out.println("Dòng dữ liệu không hợp lệ: " + line);
                    continue;
                }

                String type = parts[0].trim();
                String maVach = parts[1].trim();
                String ten = parts[2].trim();
                double giaBan = Double.parseDouble(parts[3].trim());
                int soLuongTon = Integer.parseInt(parts[4].trim());
                Nuoc nuoc = null;

                switch (type) {
                    case "TraSua":
                        nuoc = new TraSua(maVach, ten, giaBan, soLuongTon, parts[5].trim());
                        break;
                    case "TraXanh":
                        nuoc = new TraXanh(maVach, ten, giaBan, soLuongTon, parts[5].trim());
                        break;
                    case "NuocTangLuc":
                        float doCaffein = Float.parseFloat(parts[5].trim());
                        nuoc = new NuocTangLuc(maVach, ten, giaBan, soLuongTon, doCaffein);
                        break;
                    case "Bia":
                        float nongDo = Float.parseFloat(parts[5].trim());
                        nuoc = new Bia(maVach, ten, giaBan, soLuongTon, nongDo);
                        break;
                    default:
                        System.out.println("Loại nước không hợp lệ: " + type);
                        continue;
                }

                if (nuoc != null) {
                    danhSachNuoc.add(nuoc);
                }
            }
            System.out.println("Dữ liệu đã được import thành công!");
        } catch (IOException e) {
            System.out.println("Lỗi đọc file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Lỗi định dạng số: " + e.getMessage());
        }
    }

    public void exportData(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Nuoc nuoc : danhSachNuoc) {
                String line = "";
                if (nuoc instanceof TraSua) {
                    line = String.format("TraSua,%s,%s,%.2f,%d,%s",
                            nuoc.getMaVach(),
                            nuoc.getTen(),
                            nuoc.getGiaBan(),
                            nuoc.getSoLuongTon(),
                            ((TraSua) nuoc).getViTraSua());
                } else if (nuoc instanceof TraXanh) {
                    line = String.format("TraXanh,%s,%s,%.2f,%d,%s",
                            nuoc.getMaVach(),
                            nuoc.getTen(),
                            nuoc.getGiaBan(),
                            nuoc.getSoLuongTon(),
                            ((TraXanh) nuoc).getViTraXanh());
                } else if (nuoc instanceof NuocTangLuc) {
                    line = String.format("NuocTangLuc,%s,%s,%.2f,%d,%.2f",
                            nuoc.getMaVach(),
                            nuoc.getTen(),
                            nuoc.getGiaBan(),
                            nuoc.getSoLuongTon(),
                            ((NuocTangLuc) nuoc).getDoCaffein());
                } else if (nuoc instanceof Bia) {
                    line = String.format("Bia,%s,%s,%.2f,%d,%.2f",
                            nuoc.getMaVach(),
                            nuoc.getTen(),
                            nuoc.getGiaBan(),
                            nuoc.getSoLuongTon(),
                            ((Bia) nuoc).getNongDo());
                }
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Dữ liệu nước uống đã được lưu thành công!");
        } catch (IOException e) {
            System.out.println("Lỗi ghi file: " + e.getMessage());
        }
    }

}
