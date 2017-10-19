package com.example.administrator.armyzon;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by Administrator on 2017-10-20.
 */

public class ProxyUp {

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void orderingItem(Item item, AsyncHttpResponseHandler responseHandler){
        RequestParams params = new RequestParams();
        params.put("ItemNumber",item.getItemNumber());
        params.put("ItemName",item.getItemName());
        params.put("ItemStock",item.getItemStock());
        params.put("ImgName", item.getImgName());

        client.post("http://10.53.128.152:5050/", params, responseHandler);
        }
    }

