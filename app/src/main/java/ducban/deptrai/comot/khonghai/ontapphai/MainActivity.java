package ducban.deptrai.comot.khonghai.ontapphai;

import android.app.Dialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private RecyclerView lvListSinhVien;
    private LinearLayoutManager linearLayoutManager;
    private SinhVienAdapter sinhVienAdapter;
    private List<SinhVien> sinhViens;

    private DatabaseManager databaseManager;
    private SinhVienDAO sinhVienDAO;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });

    }
    private void initView(){
        floatingActionButton = findViewById(R.id.floatingButtonAdd);
        lvListSinhVien = findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        lvListSinhVien.setLayoutManager(linearLayoutManager);
        lvListSinhVien.setHasFixedSize(true);
    }

    private void initData(){
        sinhViens = new ArrayList<>();
        sinhViens.clear();
        databaseManager = new DatabaseManager(this);
        sinhVienDAO = new SinhVienDAO(databaseManager);
        sinhViens = sinhVienDAO.getAllSinhVien();

        for (int i = 0; i < 20; i++) {
            SinhVien typeBook = new SinhVien();
            typeBook.id = new Random().nextInt() + "";
            typeBook.name =  "Hip Hop";
            typeBook.diaChi = i + "";

            sinhViens.add(typeBook);
        }

        sinhVienAdapter = new SinhVienAdapter(this,sinhViens,sinhVienDAO);
        lvListSinhVien.setAdapter(sinhVienAdapter);

    }

    public void addSV(
            
    ){
        final Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.dialog_add);
    }


}
