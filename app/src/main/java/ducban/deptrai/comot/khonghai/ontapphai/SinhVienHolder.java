package ducban.deptrai.comot.khonghai.ontapphai;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class SinhVienHolder extends RecyclerView.ViewHolder {
    public TextView edtID;
    public TextView edtName;
    public TextView btnEdit;
    public TextView btnDelete;

    public SinhVienHolder(View itemView) {
        super(itemView);

        edtID = itemView.findViewById(R.id.txtID);
        edtName = itemView.findViewById(R.id.txtTEN);
        btnEdit = itemView.findViewById(R.id.txtEDIT);
        btnDelete = itemView.findViewById(R.id.txtXOA);
    }
}
