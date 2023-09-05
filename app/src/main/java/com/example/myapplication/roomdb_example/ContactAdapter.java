package com.example.myapplication.roomdb_example;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.lang.ref.WeakReference;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
    private List<Contact> mList;
    private WeakReference<Context> mContext;

    public ContactAdapter(List list, Context context) {
        this.mList = list;
        this.mContext = new WeakReference<>(context);
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext.get()).inflate(R.layout.item_danhba, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Contact contact = mList.get(position);

        holder.mNameTextView.setText(contact.getName());
        holder.mEmailTextView.setText(contact.getEmail());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView mNameTextView;
        TextView mEmailTextView;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            mNameTextView = itemView.findViewById(R.id.tv_name);
            mEmailTextView = itemView.findViewById(R.id.tv_email);
        }
    }
}
