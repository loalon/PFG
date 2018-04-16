package com.loalon.pfg.facepal;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Alonso on 09/04/2018.
 */

public class AsyncAddPerson extends AsyncTask<Context, Integer, String> {

    Context context;
    View view;
    Bitmap bitmap;
    String groupName;
    String faceName;

    public AsyncAddPerson(Context context, View view, String groupName, String faceName, Bitmap bitmap ) {
        super();
        this.context=context;
        this.bitmap=bitmap;
        this.view=view;
        this.groupName=groupName;
        this.faceName=faceName;
    }

    @Override
    protected void onPreExecute() {
        Toast.makeText(context, "Añadiendo persona. Espere por favor", Toast.LENGTH_LONG).show();
    }

    @Override
    protected String doInBackground(Context... params) {
        //String personaID = Util.identiFace(bitmap);
        //String result=Util.addFace(groupName, faceName, bitmap);
        //return result;

        String theName = Util.getPersonID(groupName, faceName);
        //si devuelve NO_ID es que no existe
        if (!theName.equals("NO_ID")) {
            return "ERROR: Persona ya en el sistema. Compruebe nombre";
            //Snackbar.make(view, "Persona ya en el sistema. Compruebe nombre", Snackbar.LENGTH_LONG)
            //      .setAction("Action", null).show();
            //si no cuadro de dialogo y añadir
        }
        String addedPerson=Util.addPerson(groupName, faceName);
            if (addedPerson.startsWith("ERROR:")){
                return addedPerson;
            }
            //System.out.println("en actividadTrain " + addedPerson);
            //if (!addedPerson.equals("ERROR")) {
        String result=Util.addFace(groupName, faceName, bitmap);
        return result;
            //}


    }

    @Override
    protected void onPostExecute(String result) {

        if (result.startsWith("ERROR:")){
            new MiniSnack(view, result);
        } else {
            new MiniSnack(view, "Persona añadida con exito");
        }
    }
}