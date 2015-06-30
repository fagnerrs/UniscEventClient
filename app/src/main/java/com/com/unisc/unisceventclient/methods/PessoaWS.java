package com.com.unisc.unisceventclient.methods;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.com.unisc.unisceventclient.classes.LoginMO;
import com.com.unisc.unisceventclient.classes.PessoaMO;
import com.com.unisc.unisceventclient.database.DataBaseEngine;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;


/**
 * Created by FAGNER on 22/04/2015.
 */
public class PessoaWS {

    private static final String BASE_URL = "http://brunopdm.jossandro.com";
    private final Context m_Context;

    public SQLiteDatabase m_DataBase = null;
    public DataBaseEngine m_dbEngine = null;

    public PessoaWS(Context context)
    {
        m_dbEngine = new DataBaseEngine(context);
        m_Context = context;
    }




    public void insertPessoa(final PessoaMO p){

        AsyncTask<Void, Void, Void> _task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                StringBuilder _builderURL = new StringBuilder();

                _builderURL.append(BASE_URL);
                _builderURL.append("/pessoa/?acao=inserir");
                _builderURL.append("&nome_pessoa=").append(p.getNome());
                _builderURL.append("&email=").append(p.getEmail());
                _builderURL.append("&senha=").append(p.getLogin().getSenha());
                _builderURL.append("&matricula=").append(String.valueOf(p.getMatricula()));

                Log.d("WBS","URL: "+_builderURL.toString());

                URL url= null;
                URI uri = null;
                try {
                    url = new URL(_builderURL.toString());
                    uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }

                StringRequest myReq = new StringRequest(Request.Method.GET,
                        uri.toASCIIString(),
                        salvarPessoaSuccessListener(),
                        createMyReqErrorListener());

                RequestQueue queue = Volley.newRequestQueue(m_Context);
                queue.add(myReq);

                return null;
            }
        };

        _task.execute();
    }

    private Response.ErrorListener createMyReqErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        };
    }

    private Response.Listener<String> salvarPessoaSuccessListener() {
        return new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {



            }
        };
    }


}
