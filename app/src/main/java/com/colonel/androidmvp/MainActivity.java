package com.colonel.androidmvp;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.colonel.androidmvp.bean.PhoneNumInfo;
import com.colonel.androidmvp.presenter.PhonePresenterImpl;
import com.colonel.androidmvp.view.PhoneNumInfoView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity implements PhoneNumInfoView {

    private static final String TAG = "MainActivity";

    @BindView(R.id.main_edit_text)
    EditText editText;

    @BindView(R.id.main_btn)
    Button btn;

    @BindView(R.id.main_text_view)
    TextView textView;

    private ProgressDialog progressDialog;
    private PhonePresenterImpl phonePresenter;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("正在加载");

        phonePresenter = new PhonePresenterImpl(this, this);
    }

    @Override
    public void showProgress() {
        if (progressDialog != null && !progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    @Override
    public void hideProgress() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void loadDataSuccess(PhoneNumInfo data) {
        textView.setText(data.getResult().getArea());
    }

    @Override
    public void loadDataError(String errorMsg) {
        textView.setText(errorMsg);
    }

    @OnClick(R.id.main_btn)
    public void loadPhoneNumInfo(View view) {
        String text = editText.getText().toString();
        if (!TextUtils.isEmpty(text)) {
            phonePresenter.getPhoneNumInfo(text);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
