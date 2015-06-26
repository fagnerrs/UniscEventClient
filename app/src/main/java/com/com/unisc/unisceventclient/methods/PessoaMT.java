package com.com.unisc.unisceventclient.methods;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.com.unisc.unisceventclient.classes.LoginMO;
import com.com.unisc.unisceventclient.classes.PessoaMO;
import com.com.unisc.unisceventclient.database.DataBaseEngine;

import java.util.ArrayList;
import java.util.Date;


/**
 * Created by FAGNER on 22/04/2015.
 */
public class PessoaMT {

    public SQLiteDatabase m_DataBase = null;
    public DataBaseEngine m_dbEngine = null;

    public PessoaMT(Context context)
    {
        m_dbEngine = new DataBaseEngine(context);
    }

    public long Salvar(PessoaMO value) {

        ContentValues _values = new ContentValues();

        //_values.put("id", value.getID());
        _values.put("nome", value.getNome());
        _values.put("email", value.getEmail());
        _values.put("matricula", value.getMatricula());

        m_DataBase = m_dbEngine.getWritableDatabase();

        long _insertedId = m_DataBase.insert("pessoa", null, _values);

        value.setID(_insertedId);

        value.getLogin().setPessoa(value);

        return salvarLogin(value.getLogin());
    }

    private long salvarLogin(LoginMO value) {

        ContentValues _values = new ContentValues();

        //_values.put("id", value.getID());
        _values.put("nome_usuario", value.getNomeUsuario());
        _values.put("pessoa_id", value.getPessoa().getID());
        _values.put("senha", value.getSenha());

        m_DataBase = m_dbEngine.getWritableDatabase();

        long _insertedId = m_DataBase.insert("login", null, _values);

        value.setID(_insertedId);

        return _insertedId;
    }



    public PessoaMO Login(String usuario, String senha)
    {
        PessoaMO _resp = null;

        StringBuilder _querySQL = new StringBuilder().
                append(" select pessoa.id, ")
                .append("  pessoa.nome,   ")
                .append("  pessoa.matricula,   ")
                .append("  pessoa.email   ")
                .append(" from login join pessoa ")
                .append("  on login.pessoa_id = pessoa.id ")
                .append(" where login.nome_usuario = '" + usuario + "' and login.senha = '" + senha + "'");

        m_DataBase = m_dbEngine.getReadableDatabase();

        Cursor _cursorPedidos = m_DataBase.
                rawQuery(_querySQL.toString(), null);

        while (_cursorPedidos.moveToNext()) {

            _resp = new PessoaMO();
            _resp.setID(_cursorPedidos.getLong(0));
            _resp.setNome(_cursorPedidos.getString(1));
            _resp.setMatricula(_cursorPedidos.getLong(2));
            _resp.setEmail(_cursorPedidos.getString(3));

            break;
        }

        m_DataBase.close();

        return _resp;
    }

}
