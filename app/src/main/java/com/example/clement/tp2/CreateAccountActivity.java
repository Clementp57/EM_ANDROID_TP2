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


public class CreateAccountActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_account, menu);
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

    public void checkAndCreateAccount(View v) {
        EditText nameView = (EditText)this.findViewById(R.id.name);
        String name = nameView.getText().toString();
        EditText mailView = (EditText)findViewById(R.id.mail);
        String mail = mailView.getText().toString();
        EditText passView = (EditText)findViewById(R.id.pass);
        String pass = passView.getText().toString();

        if(name.isEmpty() || mail.isEmpty() || pass.isEmpty()) {
            Toast toast = Toast.makeText(this, R.string.pleaseFillAllFields, Toast.LENGTH_SHORT);
            toast.show();
        } else {
            System.out.println("Name: "+name+", mail: "+mail+", pass: "+pass);

            SharedPreferences settings = getSharedPreferences(mail, 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("name", name);
            editor.putString("mail", mail);
            editor.putString("pass", pass);

            SharedPreferences settings2 = getSharedPreferences("CURRENT_LOGIN", 0);
            SharedPreferences.Editor editor2 = settings2.edit();
            editor.putString("mail", mail);

            // Commit the edits!
            editor.commit();
            Toast toast = Toast.makeText(this, R.string.savedAccount, Toast.LENGTH_SHORT);
            toast.show();

            Intent intent = new Intent(getApplicationContext(), WelcomeUserActivity.class);
            intent.putExtra("mail", mail);
            startActivity(intent);
        }


    }

}
