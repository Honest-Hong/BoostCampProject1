package com.project.boostcamp.thirdminiproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Hong Tae Joon on 2017-07-18.
 */

public class AddFragment extends Fragment {
    public static final int REQUEST_ADDRESS = 0x100;
    @BindView(R.id.edit_name) EditText editName;
    @BindView(R.id.text_address) EditText editAddress;
    @BindView(R.id.edit_tele) EditText editTele;
    @BindView(R.id.edit_desc) EditText editDesc;
    @BindView(R.id.text_count) TextView textCount;
    private OnButtonClickListener onButtonClickListener;
    private Restraurant rest;
    private Place place;

    public static AddFragment newInstance() {
        return new AddFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onButtonClickListener = (MainActivity)context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View cv = inflater.inflate(R.layout.fragment_add, container, false);
        ButterKnife.bind(this, cv);
        textCount.setText(getString(R.string.count_char, 0));
        return cv;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_add, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onButtonClickListener.onClick(this, MainActivity.BUTTON_CLOSE);
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.text_address)
    public void onAddressClick(TextView textView) {
        try {
            Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY)
                    .build(getActivity());
            startActivityForResult(intent, REQUEST_ADDRESS);
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.button_next, R.id.button_prev})
    public void onClick(View view) {
        if(view.getId() == R.id.button_next) {
            if(place != null) {
                rest = new Restraurant();
                rest.setName(editName.getText().toString());
                rest.setLatitude(place.getLatLng().latitude);
                rest.setLongitude(place.getLatLng().longitude);
                rest.setTele(editTele.getText().toString());
                rest.setDesc(editDesc.getText().toString());
                onButtonClickListener.onClick(this, MainActivity.BUTTON_NEXT);
            } else {
                Toast.makeText(getContext(), "주소를 입력하세요", Toast.LENGTH_SHORT).show();
            }
        } else {
            onButtonClickListener.onClick(this, MainActivity.BUTTON_PREV);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_ADDRESS) {
            if(resultCode == RESULT_OK) {
                place = PlaceAutocomplete.getPlace(getActivity(), data);
                editAddress.setText(place.getAddress());
            }
        }
    }

    @OnTextChanged(R.id.edit_desc)
    public void onChangeDesc(CharSequence str, int start, int end, int off) {
        textCount.setText(getString(R.string.count_char, str.length()));
    }

    public Restraurant getRest() {
        return rest;
    }
}
