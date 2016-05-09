package com.shashank.chatapp;

import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import rx.Observable;
import rx.functions.Func2;
import rx.subjects.BehaviorSubject;

/**
 * Created by shashank on 09/05/16.
 */
public class ChatWindowViewModel {


    public final RxObservableField<Boolean> isLoading;

    public final RxObservableField<Boolean> isError;

//    public final RxObservableField<Boolean> dataVisibility;

    public final RequestQueue mRequestQueue;

    public final BehaviorSubject<ResponseModel> msgData;

    public ChatWindowViewModel() {

        isLoading = new RxObservableField<Boolean>(false);
        isError = new RxObservableField<Boolean>(false);
//        dataVisibility = new RxObservableField<Boolean>(Observable.combineLatest(isLoading.getObservable(), isError.getObservable(), new Func2<Boolean, Boolean, Boolean>() {
//            @Override
//            public Boolean call(Boolean aBoolean, Boolean aBoolean2) {
//                return !aBoolean&& !aBoolean2;
//            }
//        }));

        mRequestQueue = ChatApplication.getInstance().getRequestQueue();
        msgData = BehaviorSubject.create();

        callApi();
    }


    public void callApi() {

        mRequestQueue.add(new JsonObjectRequest(Request.Method.GET , "http://haptik.co/android/test_data/", new JSONObject(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                isLoading.set(false);
                isError.set(false);

                ResponseModel data = (ResponseModel) (new Gson().fromJson(response.toString(), new TypeToken<ResponseModel>() {}.getType()));

                msgData.onNext(data);
            }
        },
        new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                isLoading.set(false);
                isError.set(true);
            }
        }));

    }


    @BindingAdapter("android:visibility")
    public static void bindboolean(View view, Boolean val) {
        if(val==null ||  !val) {
            view.setVisibility(View.GONE);
        } else {
            view.setVisibility(View.VISIBLE);
        }
    }

    @BindingAdapter("app:imageUrl")
    public static void bindboolean(ImageView view, String url) {
        if(url!=null)
            Picasso.with(view.getContext()).load(Uri.parse(url)).into(view);
        else
            view.setVisibility(View.GONE);
    }
}
