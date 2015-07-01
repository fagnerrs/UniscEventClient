package com.com.unisc.unisceventclient.methods;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.com.unisc.unisceventclient.classes.PessoaMO;
import com.com.unisc.unisceventclient.database.DataBaseEngine;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

    private IPessoaResult LoginResult;

    public PessoaWS(Context context)
    {
        m_dbEngine = new DataBaseEngine(context);
        m_Context = context;
    }

    public void Login(final String nome, final String senha){

        AsyncTask<Void, Void, Void> _task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                StringBuilder _builderURL = new StringBuilder();

                //http://brunopdm.jossandro.com/login/?email=br@hotmail.com&senha=furebsses

                _builderURL.append(BASE_URL);
                _builderURL.append("/login/?");
                _builderURL.append("email=").append(nome);
                _builderURL.append("&senha=").append(senha);

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

            JsonArrayRequest req = new JsonArrayRequest(
                        uri.toASCIIString(), new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response){

                        PessoaMO pessoa = new PessoaMO();

                        try {
                            for(int i =0; i < response.length(); i++) {
                                JSONObject jsonKeyValue = response.getJSONObject(i);
                                pessoa = jsobjToPessoa(jsonKeyValue);

                                break;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if (PessoaWS.this.LoginResult != null){
                            PessoaWS.this.LoginResult.LoginResult(pessoa);
                        }
                    }

                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        Toast.makeText(m_Context, "Problema ao buscar dados da web", Toast.LENGTH_SHORT).show();
                        Log.d("WBS", error.toString());
                    }
                }
                );


                RequestQueue queue = Volley.newRequestQueue(m_Context);
                queue.add(req);

                return null;
            }
        };

        _task.execute();
    }

    private PessoaMO jsobjToPessoa(JSONObject json){
        PessoaMO p = new PessoaMO();
        try{
            p.setCod_pessoa(json.getInt("cod_pessoa"));
            p.setNome_pessoa(json.getString("nome_pessoa"));
            p.setEmail(json.getString("email"));
            p.setMatricula(json.getInt("matricula"));
        }
        catch (JSONException e){
            e.printStackTrace();
        }
        return p;
    }

    private Response.Listener<String> loginPessoaSuccessListener() {
        return new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

            }
        };
    }

    public void Salvar(final PessoaMO p){

        AsyncTask<Void, Void, Void> _task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                StringBuilder _builderURL = new StringBuilder();

                _builderURL.append(BASE_URL);
                _builderURL.append("/pessoa/?acao=inserir");
                _builderURL.append("&nome_pessoa=").append(p.getNome_pessoa());
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

                JsonArrayRequest req = new JsonArrayRequest(
                        uri.toASCIIString(), new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response){



                        try {
                            for(int i =0; i < response.length(); i++) {
                                JSONObject jsonKeyValue = response.getJSONObject(i);

                                p.setCod_pessoa(jsonKeyValue.getInt("cod_pessoa"));


                                break;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if (PessoaWS.this.LoginResult != null){
                            PessoaWS.this.LoginResult.SalvarResult(p);
                        }
                    }

                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        Toast.makeText(m_Context, "Problema ao buscar dados da web", Toast.LENGTH_SHORT).show();
                        Log.d("WBS", error.toString());
                    }
                }
                );

                RequestQueue queue = Volley.newRequestQueue(m_Context);
                queue.add(req);

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

    public IPessoaResult getLoginResult() {
        return LoginResult;
    }

    public void setLoginResult(IPessoaResult loginResult) {
        LoginResult = loginResult;
    }

    public interface IPessoaResult {
        void LoginResult(PessoaMO pessoa);
        void SalvarResult(PessoaMO pessoa);
    }

}
