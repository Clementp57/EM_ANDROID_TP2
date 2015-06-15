package com.example.clement.tp2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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

    public void login(View v) {
        EditText mailView = (EditText)findViewById(R.id.mailLogin);
        String mail = mailView.getText().toString();
        EditText passView = (EditText)findViewById(R.id.passLogin);
        String pass = passView.getText().toString();

        SharedPreferences settings = getSharedPreferences(mail, 0);
        String prefPass = settings.getString("pass", "");

        if(settings != null && pass.equals(prefPass)) {
            Intent intent = new Intent(this, WelcomeUserActivity.class);
            intent.putExtra("mail", mail);
            startActivity(intent);
        } else {
            System.out.println("Settings: "+settings+" , pass:"+prefPass );
            Toast toast = Toast.makeText(this, R.string.invalidMailOrPass, Toast.LENGTH_SHORT);
            toast.show();
        }

    }
}
