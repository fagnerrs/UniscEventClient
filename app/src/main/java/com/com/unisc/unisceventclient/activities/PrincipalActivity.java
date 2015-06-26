package com.com.unisc.unisceventclient.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.com.unisc.unisceventclient.classes.NavigationManager;
import com.com.unisc.unisceventclient.classes.PessoaMO;
import com.com.unisc.unisceventclient.methods.PessoaMT;
import com.unisc.unisceventclient.R;

public class PrincipalActivity extends Activity {

    private EditText m_EdtNomeUser;
    private EditText m_EdtNomeSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        m_EdtNomeUser = (EditText) this.findViewById(R.id.activity_principal_EdtNome);
        m_EdtNomeSenha = (EditText) this.findViewById(R.id.activity_principal_EdtSenha);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    public void MarcarPresencaClick(View view){


        if (m_EdtNomeUser.getText().toString().equals("")){
            Toast.makeText(this, "Informe o nome do usuário!", Toast.LENGTH_LONG).show();
        }else
        if (m_EdtNomeSenha.getText().toString().equals("")){
            Toast.makeText(this, "Informe a senha!", Toast.LENGTH_LONG).show();
        }else {

            PessoaMO _pessoaMO = new PessoaMT(this).Login(m_EdtNomeUser.getText().toString(), m_EdtNomeSenha.getText().toString());

            if (_pessoaMO != null) {

                NavigationManager.PessoaLogada = _pessoaMO;

                Intent _int = new Intent(this, ActionActivity.class);
                startActivity(_int);
            }
            else
            {
                Toast.makeText(this, "Usuário não encontrado!", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void CadastrarPessoaClick(View view){
        Intent _int = new Intent(this, PessoaActivity.class);
        startActivity(_int);

    }
}
