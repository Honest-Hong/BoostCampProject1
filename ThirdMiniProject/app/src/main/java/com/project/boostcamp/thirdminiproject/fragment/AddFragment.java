package com.project.boostcamp.thirdminiproject.fragment;

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
import com.project.boostcamp.thirdminiproject.MainActivity;
import com.project.boostcamp.thirdminiproject.onNextClickListener;
import com.project.boostcamp.thirdminiproject.R;
import com.project.boostcamp.thirdminiproject.Restraurant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Hong Tae Joon on 2017-07-18.
 */

public class AddFragment extends Fragment {
    public static final int REQUEST_ADDRESS = 0x100; // 주소 자동완성 요청 상수

    @BindView(R.id.edit_name) EditText editName;
    @BindView(R.id.edit_address) EditText editAddress;
    @BindView(R.id.edit_tele) EditText editTele;
    @BindView(R.id.edit_desc) EditText editDesc;
    @BindView(R.id.text_count) TextView textCount;

    private onNextClickListener onNextClickListener; // 다음 버튼 클릭 이벤트 처리

    public static AddFragment newInstance() {
        return new AddFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onNextClickListener = (MainActivity)context;
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
        // 글자 수 표시 초기화
        textCount.setText(getString(R.string.count_char, 0));
        return cv;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_add, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @OnClick(R.id.edit_address)
    public void onAddressClick(EditText ev) {
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

    @OnClick({R.id.button_next,R.id.button_prev})
    public void onClick(View view) {
        if(view.getId() == R.id.button_next) {
            String address = editAddress.getText().toString();

            // 주소가 입력되어 있어야만 다음으로 이동이 가능하다
            if (!address.equals("")) {
                // 맛집 정보 저장
                Restraurant rest = new Restraurant();
                rest.setName(editName.getText().toString());
                rest.setAddress(address);
                rest.setTele(editTele.getText().toString());
                rest.setDesc(editDesc.getText().toString());
                // 맛집 정보와 프래그먼트 전달
                onNextClickListener.onNext(this, rest);
            } else {
                // 경고 표시
                Toast.makeText(getContext(), "주소를 입력하세요", Toast.LENGTH_SHORT).show();
            }
        } else {
            getFragmentManager().popBackStack();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_ADDRESS) {
            if(resultCode == RESULT_OK) {
                // 주소 검색 결과 표시
                Place place = PlaceAutocomplete.getPlace(getActivity(), data);
                editAddress.setText(place.getAddress());
            }
        }
    }

    // 글자 수 갱신
    @OnTextChanged(R.id.edit_desc)
    public void onChangeDesc(CharSequence str, int start, int end, int off) {
        textCount.setText(getString(R.string.count_char, str.length()));
    }
}
