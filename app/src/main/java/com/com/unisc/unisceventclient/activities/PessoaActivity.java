package com.com.unisc.unisceventclient.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.com.unisc.unisceventclient.classes.LoginMO;
import com.com.unisc.unisceventclient.classes.NavigationManager;
import com.com.unisc.unisceventclient.classes.PessoaMO;
import com.com.unisc.unisceventclient.methods.PessoaMT;
import com.com.unisc.unisceventclient.methods.PessoaWS;
import com.unisc.unisceventclient.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class PessoaActivity extends Activity {

    private Button m_btnSalvar;
    private EditText m_edtMatricula;
    private EditText m_edtNome;
    private EditText m_edtEmail;
    private EditText m_edtNomeUsuario;
    private EditText m_edtSenhaUsuario;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private ImageView m_ImageView;
    private Bundle m_BundleFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pessoa);

        // Set up action bar.
        final ActionBar actionBar = getActionBar();

        // Specify that the Home button should show an "Up" caret, indicating that touching the
        // button will take the user one step up in the application's hierarchy.
        actionBar.setDisplayHomeAsUpEnabled(true);

        m_edtMatricula = (EditText) this.findViewById(R.id.activity_pessoa_EdtMatricula);
        m_edtNome = (EditText) this.findViewById(R.id.activity_pessoa_EdtNome);
        m_edtEmail = (EditText) this.findViewById(R.id.activity_pessoa_EdtEmail);
        m_edtNomeUsuario = (EditText) this.findViewById(R.id.activity_pessoa_EdtNomeAcesso);
        m_edtSenhaUsuario = (EditText) this.findViewById(R.id.activity_pessoa_EdtSenhaAcesso);
        m_btnSalvar = (Button) this.findViewById(R.id.activity_pessoa_BtnSalvar);
        m_ImageView = (ImageView)this.findViewById(R.id.activity_pessoa_Image);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pessoa, menu);
        return true;
    }

    public void SalvarPessoaClick(View view){

        if (m_edtMatricula.getText().toString().equals("")){
            Toast.makeText(this, "Informe a Matrícula.", Toast.LENGTH_LONG).show();
        }
        else        if (m_edtMatricula.getText().toString().equals("")){
            Toast.makeText(this, "Informe o Nome.", Toast.LENGTH_LONG).show();
        }
        else        if (m_edtMatricula.getText().toString().equals("")){
            Toast.makeText(this, "Informe o Email.", Toast.LENGTH_LONG).show();
        }
        else        if (m_edtMatricula.getText().toString().equals("")){
            Toast.makeText(this, "Informe a usuário de acesso.", Toast.LENGTH_LONG).show();
        }
        else        if (m_edtMatricula.getText().toString().equals("")){
            Toast.makeText(this, "Informe a senha de acesso..", Toast.LENGTH_LONG).show();
        }
        else {

            PessoaMO _newPessoa = new PessoaMO();
            _newPessoa.setEmail(m_edtEmail.getText().toString());
            _newPessoa.setMatricula(Integer.valueOf(m_edtMatricula.getText().toString()));
            _newPessoa.setNome(m_edtNome.getText().toString());

            LoginMO _newLogin = new LoginMO();
            _newLogin.setNomeUsuario(m_edtNomeUsuario.getText().toString());
            _newLogin.setSenha(m_edtSenhaUsuario.getText().toString());

            _newPessoa.setLogin(_newLogin);


            //new PessoaMT(this).Salvar(_newPessoa);
            new PessoaWS(this).insertPessoa(_newPessoa);

            NavigationManager.PessoaLogada = _newPessoa;

            Intent _int = new Intent(this, ActionActivity.class);
            startActivity(_int);
        }
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
        }else
        {
            this.onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    public void TirarFotoClick(View view){
        dispatchTakePictureIntent();
    }

    private void dispatchTakePictureIntent()
    {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            m_BundleFoto = data.getExtras();
            Bitmap imageBitmap = (Bitmap) m_BundleFoto.get("data");

            m_ImageView.setImageBitmap(imageBitmap);

            try {

                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                imageBitmap.compress(Bitmap.CompressFormat.JPEG, 40, bytes);
/*
//you can create a new file name "test.jpg" in sdcard folder.
               /m_FileImage = new File(Environment.getExternalStorageDirectory()
                        + File.separator + "test.jpg");

                if (m_FileImage.exists())
                {
                    m_FileImage.delete();
                }

                m_FileImage.createNewFile();
//write the bytes in file
                FileOutputStream fo = new FileOutputStream(m_FileImage);
                fo.write(bytes.toByteArray());

// remember close de FileOutput
                fo.close();
*/

            } catch (Exception e) {
                e.printStackTrace();
            }


            //mImageView.setImageBitmap(imageBitmap);

            }
        }
}
