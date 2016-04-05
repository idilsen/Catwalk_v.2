package com.parse.starter;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditFragment extends Fragment {
    protected EditText mConfirmed;
    protected Button mUpdate;
    protected  EditText mNewPassword;
    protected  EditText mNewEmail;
    protected Button mUpdateEmail;


    public EditFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_edit, container, false);
        //initialize
        mNewPassword = (EditText)view.findViewById(R.id.newPassword);
        mConfirmed = (EditText)view.findViewById(R.id.confirmPassword);
        mNewEmail = (EditText) view.findViewById(R.id.newEmail);

        mUpdateEmail = (Button) view.findViewById(R.id.updateEmail);
        mUpdate = (Button) view.findViewById(R.id.updateButton);

        //Update Email Process
        mUpdateEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser currentUser = ParseUser.getCurrentUser();
                ParseQuery<ParseUser> query = ParseUser.getQuery();
                query.whereEqualTo("username", currentUser);
                query.findInBackground(new FindCallback<ParseUser>() {
                    public void done(List<ParseUser> objects, ParseException e) {
                        if (e == null) {
                            ParseUser currentUser = ParseUser.getCurrentUser();
                            String newEmail = mNewEmail.getText().toString().trim();
                            currentUser.setEmail(newEmail);
                            currentUser.saveInBackground(new SaveCallback() {
                                @Override
                                public void done(ParseException e) {
                                    if (e == null) {
                                        //successfully updated the password
                                        Toast.makeText(getActivity(), "You have successfully changed your email address!", Toast.LENGTH_LONG).show();
                                        //take user to homepage
                                        Intent discover = new Intent(getActivity(), NavigationActivity.class);
                                        startActivity(discover);

                                    } else {
                                        //there was a problem. advice user.
                                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                        builder.setMessage(e.getMessage());
                                        builder.setTitle("Oops!");
                                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();

                                            }
                                        });
                                        AlertDialog dialog = builder.create();
                                        dialog.show();
                                    }
                                }
                            });
                        } else {
                            // Something went wrong.
                            //there was a problem. advice user.
                            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                            builder.setMessage(e.getMessage());
                            builder.setTitle("Oops!");
                            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();

                                }
                            });
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }
                    }
                });
            }
        });

        //Update Password Process
        mUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newpass = mNewPassword.getText().toString().trim();
                String confirmed = mConfirmed.getText().toString().trim();

                if(newpass.equals(confirmed)) {
                    ParseUser currentUser = ParseUser.getCurrentUser();
                    ParseQuery<ParseUser> query = ParseUser.getQuery();
                    query.whereEqualTo("username", currentUser);
                    query.findInBackground(new FindCallback<ParseUser>() {
                        public void done(List<ParseUser> objects, ParseException e) {
                            if (e == null) {
                                ParseUser currentUser = ParseUser.getCurrentUser();
                                String newPass = mNewPassword.getText().toString();
                                currentUser.setPassword(newPass);
                                currentUser.saveInBackground(new SaveCallback() {
                                    @Override
                                    public void done(ParseException e) {
                                        if (e == null) {
                                            //successfully updated the password
                                            Toast.makeText(getActivity(), "You have successfully changed your password!", Toast.LENGTH_LONG).show();
                                            Intent discover = new Intent(getActivity(), NavigationActivity.class);
                                            //take user to homepage
                                            startActivity(discover);

                                        } else {
                                            //there was a problem. advice user.
                                            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                            builder.setMessage(e.getMessage());
                                            builder.setTitle("Oops!");
                                            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    dialog.dismiss();

                                                }
                                            });
                                            AlertDialog dialog = builder.create();
                                            dialog.show();
                                        }
                                    }
                                });
                            } else {
                                // Something went wrong.
                                //there was a problem. advice user.
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                builder.setMessage(e.getMessage());
                                builder.setTitle("Oops!");
                                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();

                                    }
                                });
                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }
                        }
                    });
                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("You did not enter the same password!!");
                    builder.setTitle("Oops!");
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();

                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();

                }
            }
        });
    return view;

    }

}
