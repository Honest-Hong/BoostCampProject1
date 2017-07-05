package com.project.boostcamp.firstminiproject;

import android.content.Context;
import android.content.Intent;
import android.preference.DialogPreference;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.project.boostcamp.firstminiproject.fragment.MainFragment;
import com.project.boostcamp.firstminiproject.fragment.SearchFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    // 툴바 메뉴 버튼들
    private ImageView buttonDirect, buttonReturn, buttonDelete, buttonMessenger;
    // 툴바 검색창
    private EditText editSearch;
    // 현재 검색 상태인지를 저장하는 변수
    private boolean searchMode;
    // 친구 추가 버튼
    private FloatingActionButton fabAdd;
    // 친구 추가 버튼 이스터 에그
    private int count = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar();
        fabAdd = (FloatingActionButton)findViewById(R.id.button_add);
        fabAdd.setOnClickListener(this);

        // 처음 프래그먼트 띄워주기
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        MainFragment mf = new MainFragment();
        // 메인 프래그먼트의 뷰페이저가 FAB을 제어할 수 있도록 넘겨준다
        mf.setFabAdd(fabAdd);
        ft.add(R.id.fragment, mf);
        ft.commit();
    }

    // 버튼 초기화
    private void initToolbar() {
        buttonDirect = (ImageView)findViewById(R.id.button_direct);
        buttonReturn = (ImageView)findViewById(R.id.button_return);
        buttonDelete = (ImageView)findViewById(R.id.button_search_delete);
        buttonMessenger = (ImageView)findViewById(R.id.button_messenger);

        editSearch = (EditText)findViewById(R.id.edit_search);
        // 검색창이 포커스를 받았을 경우 검색 모드로 변환한다
        editSearch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                    setSearchMode(hasFocus);
            }
        });
        // 글자가 1글자라도 입력되면 비우기 버튼이 나타나고
        // 글자가 없으면 비우기 버튼이 사라진다
        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() > 0)
                    buttonDelete.setVisibility(View.VISIBLE);
                else
                    buttonDelete.setVisibility(View.GONE);
            }
        });
        buttonDirect.setOnClickListener(this);
        buttonReturn.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);
        buttonMessenger.setOnClickListener(this);
    }

    // 검색 모드 On/Off
    private void setSearchMode(boolean on) {
        searchMode = on;
        if(on) {
            buttonDirect.setVisibility(View.GONE);
            buttonMessenger.setVisibility(View.GONE);
            buttonReturn.setVisibility(View.VISIBLE);
            // 친구 추가 버튼이 보이는 상태(친구 요청 탭)인 경우 숨긴다
            if(fabAdd.isShown()) fabAdd.setVisibility(View.GONE);

            // 검색 프로그먼트로 전환하기
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.addToBackStack("");
            ft.replace(R.id.fragment, new SearchFragment());
            ft.commit();
        } else {
            buttonDirect.setVisibility(View.VISIBLE);
            buttonMessenger.setVisibility(View.VISIBLE);
            buttonReturn.setVisibility(View.GONE);
            // 친구 추가 버튼이 고의로 숨겨진 상태(친구 요청 탭이었던 상태)인 경우 다시 보여준다
            if(fabAdd.getVisibility() == View.GONE) fabAdd.setVisibility(View.VISIBLE);
            // 검색 창을 비워준다
            editSearch.setText("");
            editSearch.clearFocus();
            // 키보드를 내린다
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(editSearch.getWindowToken(), 0);

            // 이전 프래그먼트로 돌아가기
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.button_direct:
                startActivity(new Intent(this, DirectActivity.class));
                break;
            case R.id.button_messenger:
                Toast.makeText(this, "메신저 버튼 클릭", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_search_delete:
                editSearch.setText("");
                break;
            case R.id.button_return:
                setSearchMode(false);
                break;
            case R.id.button_add:
                // 친구 추가 버튼 이스터 에그 부분 (버튼이 커짐)
                count *= 2;
                if(count > 512) count = 1;
                fabAdd.getLayoutParams().height = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 48 + count, getResources().getDisplayMetrics());
                fabAdd.getLayoutParams().width = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 48 + count, getResources().getDisplayMetrics());
                fabAdd.requestLayout();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        // 백버튼을 눌렀을 때 검색 모드일 경우 일반 모드로 전환시켜준다
        if(searchMode)
            setSearchMode(false);
        else
            super.onBackPressed();
    }
}
