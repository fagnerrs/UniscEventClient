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
import com.com.unisc.unisceventclient.methods.PessoaWS;
import com.unisc.unisceventclient.R;

public class PrincipalActivity extends Activity {

    private EditText m_EdtNomeUser;
    private EditText m_EdtNomeSenha;
    private PessoaWS _pessoaWS;

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
            _pessoaWS = new PessoaWS(this);
            _pessoaWS.Login(m_EdtNomeUser.getText().toString(), m_EdtNomeSenha.getText().toString());
            _pessoaWS.setLoginResult(new PessoaWS.IPessoaResult() {
                @Override
                public void SalvarResult(PessoaMO pessoa) {
                }

                @Override
                public void LoginResult(PessoaMO pessoa) {
                    if (pessoa != null) {

                        NavigationManager.PessoaLogada = pessoa;

                        Intent _int = new Intent(PrincipalActivity.this, ActionActivity.class);
                        startActivity(_int);
                    }
                    else
                    {
                        Toast.makeText(PrincipalActivity.this, "Usuário não encontrado!", Toast.LENGTH_LONG).show();
                    }
                }
            });

        }
    }

    public void CadastrarPessoaClick(View view){
        Intent _int = new Intent(this, PessoaActivity.class);
        startActivity(_int);

    }
}
