package com.songmho.saylog;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

/**
 * Created by songmho on 2015-02-17.
 */
public class SignupActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        getSupportActionBar().setTitle("회원가입");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xff3598db));
        getSupportActionBar().setHomeButtonEnabled(true);

        final EditText email=(EditText)findViewById(R.id.email);
        final EditText name=(EditText)findViewById(R.id.name);
        final EditText password=(EditText)findViewById(R.id.password);
        final EditText pass_check=(EditText)findViewById(R.id.pass_check);
        Button signup=(Button)findViewById(R.id.signup_button);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email_str= String.valueOf(email.getText());
                ParseUser user=new ParseUser();
                user.setUsername(email_str);
                user.setEmail(email_str);
                user.put("name",String.valueOf(name.getText()));
                if(String.valueOf(password.getText()).equals(String.valueOf(pass_check.getText())))
                    user.setPassword(String.valueOf(password.getText()));
                else
                    Toast.makeText(getApplicationContext(), "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e==null) {
                            Toast.makeText(getApplicationContext(), "가입 되었습니다.", Toast.LENGTH_SHORT).show();
                            String [] result=email_str.split("\\@");
                            ParseObject object=ParseObject.create(result[0]);
                            SharedPreferences pref_login=getSharedPreferences("login_info",MODE_PRIVATE);
                            SharedPreferences.Editor editor=pref_login.edit();
                            editor.putString("email",email_str);
                            editor.putString("password",String.valueOf(password.getText()));
                            editor.putString("classname",result[0]);
                            editor.putString("name",String.valueOf(name.getText()));
                            editor.commit();
                            object.saveInBackground();
                            startActivity(new Intent(SignupActivity.this, MainActivity.class));
                            finish();
                        }
                    }
                });
            }
        });

    }
}
