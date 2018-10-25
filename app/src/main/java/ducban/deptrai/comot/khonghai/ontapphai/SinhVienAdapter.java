package ducban.deptrai.comot.khonghai.ontapphai;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class SinhVienAdapter extends RecyclerView.Adapter<SinhVienHolder> {
    private Context context;
    private List<SinhVien> arrayList;
    private SinhVienDAO sinhVienDAO;

    public SinhVienAdapter(Context context, List<SinhVien> arrayList, SinhVienDAO sinhVienDAO) {
        this.context = context;
        this.arrayList = arrayList;
        this.sinhVienDAO = sinhVienDAO;
    }

    @Override
    public SinhVienHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.card_view,parent,false);
        return new SinhVienHolder(view);
    }

    @Override
    public void onBindViewHolder(SinhVienHolder holder, final int position) {
        final SinhVien sinhVien = arrayList.get(position);
        holder.edtID.setText(sinhVien.id);
        holder.edtName.setText(sinhVien.name);
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long kq = sinhVienDAO.deleteSinhVien(sinhVien.id);

                if (kq< 0){
                    Toast.makeText(context, "Xóa không thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    arrayList.remove(position);

                    notifyDataSetChanged();
                }
            }
        });

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_edit);

                final EditText edtID;
                final EditText edtNAME, edtDIACHi;
                Button btnSave, btnCancel;

                edtID= dialog.findViewById(R.id.edtID);
                edtID.setVisibility(View.GONE);
                edtNAME = dialog.findViewById(R.id.edtTEN);
                edtDIACHi = dialog.findViewById(R.id.edtDIACHI);
                btnSave = dialog.findViewById(R.id.btnSave);
                btnCancel = dialog.findViewById(R.id.btnCancel);
                btnSave.setText("UPDATE");

                edtNAME.setText(sinhVien.name);
                edtDIACHi.setText(sinhVien.diaChi);

                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String id = sinhVien.id;
                        String name = edtNAME.getText().toString().trim();
                        String diachi = edtDIACHi.getText().toString().trim();
                        SinhVien sinhVien =new SinhVien(id,name,diachi);

                        long kq = sinhVienDAO.updateSinhVien(sinhVien);
                        if (kq < 0){
                            Toast.makeText(context, "Cập nhật không thành công!!!", Toast.LENGTH_SHORT).show();
                        } else {
                            arrayList.get(position).name = name;
                            arrayList.get(position).diaChi = diachi;

                            notifyDataSetChanged();
                            Toast.makeText(context, "Cập nhật loại sach thành công", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
                dialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
