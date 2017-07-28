package com.project.boostcamp.staffdinner.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.project.boostcamp.staffdinner.R;
import com.project.boostcamp.publiclibrary.model.Contact;
import com.project.boostcamp.publiclibrary.model.OnContactClickListener;

import java.util.ArrayList;

/**
 * Created by Hong Tae Joon on 2017-07-25.
 */

public class ContactRecyclerAdapter extends RecyclerView.Adapter<ContactVH> {
    private ArrayList<Contact> contacts;
    private OnContactClickListener contactClickListener;

    public ContactRecyclerAdapter(OnContactClickListener contactClickListener) {
        contacts = new ArrayList<>();
        this.contactClickListener = contactClickListener;
    }

    public void setContacts(ArrayList<Contact> contacts) {
        this.contacts = contacts;
        notifyDataSetChanged();
    }

    @Override
    public ContactVH onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        return new ContactVH(LayoutInflater.from(context).inflate(R.layout.layout_contact_item, parent, false), context, contactClickListener);
    }

    @Override
    public void onBindViewHolder(ContactVH holder, int position) {
        holder.setupView(contacts.get(position));
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }
}
