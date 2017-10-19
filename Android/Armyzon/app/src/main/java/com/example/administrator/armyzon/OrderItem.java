package com.example.administrator.armyzon;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;


public class OrderItem extends AppCompatActivity implements  OnClickListener {

    private InputMethodManager inputMethodManager;
    private EditText order_ItemName;
    private EditText order_ItemStock;
    private Button buttonOrder;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_item);
        // inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);

        buttonOrder = (Button) findViewById(R.id.order_Button);
        buttonOrder.setOnClickListener(this);

        order_ItemName = (EditText) findViewById(R.id.order_ItemName);
        order_ItemStock = (EditText) findViewById(R.id.order_ItemStock);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.order_Button:
                progressDialog = ProgressDialog.show(OrderItem.this, "","업로드 중 입니다.");

                Item item = new Item(
                        0,                                          //ItemNumber
                        order_ItemName.getText().toString(),        //ItemName
                        order_ItemStock.getText().toString(),       //ItemStock
                        "img01.jpg".toString()                      //임의의 img파일
                );

                ProxyUp.orderingItem(item, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        Log.e("test", "up on Success: ");
                        progressDialog.cancel();
                        Toast.makeText(getApplicationContext(), order_ItemName.getText().toString() + "상품이\n" + order_ItemStock.getText().toString() + "개\n" + "주문이 완료되었습니다.",Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                       Log.e("test", "up on Failed: ");

                        progressDialog.cancel();
                        Toast.makeText(getApplicationContext(), order_ItemName.getText().toString() + "상품이\n" + order_ItemStock.getText().toString() + "개\n" + "주문이 실패했습니다.",Toast.LENGTH_SHORT).show();
                    }
                });
                break;
        }
    }

}

