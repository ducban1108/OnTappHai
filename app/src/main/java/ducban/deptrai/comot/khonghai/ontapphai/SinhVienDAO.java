package ducban.deptrai.comot.khonghai.ontapphai;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class SinhVienDAO implements Constant {
    private DatabaseManager databaseManager;

    public SinhVienDAO(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    public long insertSinhVien(SinhVien sinhVien){
        SQLiteDatabase sqLiteDatabase = databaseManager.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(CL_ID, sinhVien.id);
        contentValues.put(CL_NAME,sinhVien.name);
        contentValues.put(CL_POS,sinhVien.diaChi);

        long result = sqLiteDatabase.insert(TABLE_SINH_VIEN, null,contentValues);
        sqLiteDatabase.close();
        return result;
    }

    public long updateSinhVien(SinhVien sinhVien){
        SQLiteDatabase sqLiteDatabase = databaseManager.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(CL_ID,sinhVien.id);
        contentValues.put(CL_NAME,sinhVien.name);
        contentValues.put(CL_POS,sinhVien.diaChi);

        long ketqua = sqLiteDatabase.update(TABLE_SINH_VIEN,contentValues,CL_ID + "=?",
                new String[]{sinhVien.id});
        sqLiteDatabase.close();
        return ketqua;
    }

    public long deleteSinhVien(String sinhvienID){
        SQLiteDatabase sqLiteDatabase = databaseManager.getWritableDatabase();

        long kq = sqLiteDatabase.delete(TABLE_SINH_VIEN,CL_ID + "=?",
                new String[]{sinhvienID});

        sqLiteDatabase.close();
        return kq;
    }

    public List<SinhVien> getAllSinhVien(){
        List<SinhVien> sinhViens =new ArrayList<>();
        String SELECT_ALL_SINH_VIEN = "SELECT * FROM " + TABLE_SINH_VIEN;

        SQLiteDatabase sqLiteDatabase = databaseManager.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_ALL_SINH_VIEN,null);

        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            do {
                String id = cursor.getString(cursor.getColumnIndex(CL_ID));
                String name = cursor.getString(cursor.getColumnIndex(CL_NAME));
                String diachi = cursor.getString(cursor.getColumnIndex(CL_POS));

                SinhVien sinhVien = new SinhVien();
                sinhVien.id = id;
                sinhVien.name = name;
                sinhVien.diaChi = diachi;

                sinhViens.add(sinhVien);

            }while (cursor.moveToNext());
        }
        cursor.close();
        sqLiteDatabase.close();
        return sinhViens;
    }
}
