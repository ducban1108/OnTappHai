package ducban.deptrai.comot.khonghai.ontapphai;

public interface Constant {

    String TABLE_SINH_VIEN = "sinhVien";

    String CL_ID = "MaSinhVien";
    String CL_NAME = "TenSinhVien";
    String CL_POS = "DiaChi";

    String CREATE_TABLE_SINH_VIEN = "CREATE TABLE " + TABLE_SINH_VIEN + "(" +
            "" + CL_ID + " CHAR(5) PRIMARY KEY NOT NULL," +
            "" + CL_NAME + " NVARCHAR(50) NOT NULL," +
            "" + CL_POS + " NVARCHAR(50) NOT NULL" +
            ")";


}
