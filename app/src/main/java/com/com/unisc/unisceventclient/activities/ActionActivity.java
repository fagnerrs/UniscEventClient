package com.com.unisc.unisceventclient.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.com.unisc.unisceventclient.classes.NavigationManager;
import com.unisc.unisceventclient.R;

public class ActionActivity extends Activity {

    private TextView m_tvUsuarioLogado;
    private TextView m_tvResultQrCode;
    public static final int REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);

        // Set up action bar.
        final ActionBar actionBar = getActionBar();

        // Specify that the Home button should show an "Up" caret, indicating that touching the
        // button will take the user one step up in the application's hierarchy.
        actionBar.setDisplayHomeAsUpEnabled(true);

        m_tvUsuarioLogado = (TextView) this.findViewById(R.id.activity_action_TvUsuario);
        m_tvResultQrCode = (TextView) this.findViewById(R.id.activity_action_TvResultQrode);

        m_tvUsuarioLogado.setText(String.valueOf(NavigationManager.PessoaLogada.getMatricula()) + " - " +
                NavigationManager.PessoaLogada.getNome_pessoa());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_action, menu);
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
        }else
        {
            this.onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    public void LerQrCodeClick(View view)
    {
        Intent it = new Intent(this, com.google.zxing.client.android.CaptureActivity.class);
        startActivityForResult(it, REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(REQUEST_CODE == requestCode && RESULT_OK == resultCode){
            m_tvResultQrCode.setText("RESULTADO: "+data.getStringExtra("SCAN_RESULT")+" ("+data.getStringExtra("SCAN_FORMAT")+")");
        }
    }
}
