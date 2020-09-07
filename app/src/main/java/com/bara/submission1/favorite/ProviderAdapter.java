//package com.bara.submission1.favorite;
//
//import android.database.Cursor;
//import android.net.Uri;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.constraintlayout.widget.ConstraintLayout;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.bara.submission1.R;
//
//public class ProviderAdapter extends RecyclerView.Adapter<ProviderAdapter.ViewHolder> {
//
//    private Cursor mCursor;
//
//    void setData(Cursor cursor) {
//        mCursor = cursor;
//        notifyDataSetChanged();
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorite, parent, false);
//        return new ViewHolder(v);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
//        if (mCursor.moveToPosition(position)) {
//            holder.name.setText(mCursor.getString(mCursor.getColumnIndexOrThrow("name")));
//            holder.type.setText(mCursor.getString(mCursor.getColumnIndexOrThrow("type")));
//            holder.avatar.setImageURI(Uri.parse(mCursor.getString(mCursor.getColumnIndexOrThrow("avatar"))));
//        }
//    }
//
//    @Override
//    public int getItemCount() {
//        return mCursor == null ? 0 : mCursor.getCount();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//
//        private TextView name, type;
//        private ImageView avatar;
//        private ConstraintLayout layout;
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//            name = itemView.findViewById(R.id.txt_name_fav);
//            type = itemView.findViewById(R.id.txt_type_fav);
//            avatar = itemView.findViewById(R.id.img_avatar_fav);
//            layout = itemView.findViewById(R.id.constraint_layout_fav);
//        }
//    }
//}
