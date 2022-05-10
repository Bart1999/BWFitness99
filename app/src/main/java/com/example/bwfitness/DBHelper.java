package com.example.bwfitness;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Date;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {

        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase myDB) {
        myDB.execSQL("create Table users(username Text primary key,password Text)");
        myDB.execSQL("create Table Calorietracking(target_calories TEXT primary key, current_calories TEXT, target_protein TEXT, current_protein TEXT, target_carbs TEXT, current_carbs TEXT, target_fats TEXT, current_fats TEXT)");
        myDB.execSQL("create Table Membership(membership_length TEXT primary key, payment_method TEXT)");
        myDB.execSQL("create Table Gymslot(which_day TEXT primary key, start_time TEXT , end_time TEXT )");
        myDB.execSQL("create Table Workouttracking(which_exercise TEXT primary key, target_weight TEXT , current_weight TEXT , target_sets TEXT , current_sets TEXT , target_reps TEXT , current_reps TEXT ) ");
        myDB.execSQL("create Table pt_table(target_performance TEXT primary key, pt_length TEXT , pt_payment TEXT )");
        myDB.execSQL("create Table miketable(start_pt TEXT primary key , end_pt TEXT , MikeSpinner1 TEXT ,MikeSpinner2 TEXT )");
        myDB.execSQL("create Table kyletable(start_pt2 TEXT primary key , end_pt2 TEXT , KyleSpinner1 TEXT , KyleSpinner2 TEXT )");
        myDB.execSQL("create Table petertable(start_pt3 TEXT primary key , end_pt3 TEXT , PeterSpinner1 TEXT , PeterSpinner2 TEXT )");
        myDB.execSQL("create Table jamestable(start_pt4 TEXT primary key , end_pt4 TEXT , JamesSpinner1 TEXT , JamesSpinner2 TEXT )");
        myDB.execSQL("create Table sarahtable(start_pt5 TEXT primary key , end_pt5 TEXT , SarahSpinner1 TEXT , SarahSpinner2 TEXT )");
        myDB.execSQL("create Table jessicatable(start_pt6 TEXT primary key , end_pt6 TEXT , JessicaSpinner1 TEXT , JessicaSpinner2 TEXT )");
        myDB.execSQL("create Table kylietable(start_pt7 TEXT primary key , end_pt7 TEXT , KylieSpinner1 TEXT , KylieSpinner2 TEXT )");
        myDB.execSQL("create Table chloetable(start_pt8 TEXT primary key , end_pt8 TEXT , ChloeSpinner1 TEXT , ChloeSpinner2 TEXT )");
        myDB.execSQL("create Table settings(email TEXT primary key , name TEXT , home TEXT , phonenumber TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase myDB, int oldVersion, int newVersion) {
        myDB.execSQL("drop Table if exists users");
        myDB.execSQL("drop table if exists Calorietracking");
        myDB.execSQL("drop table if exists Membership");
        myDB.execSQL("drop table if exists Gymslot");
        myDB.execSQL("drop table if exists Workouttracking");
        myDB.execSQL("drop table if exists pt_table");
        myDB.execSQL("drop table if exists miketable");
        myDB.execSQL("drop table if exists kyletable");
        myDB.execSQL("drop table if exists petertable");
        myDB.execSQL("drop table if exists jamestable");
        myDB.execSQL("drop table if exists sarahtable");
        myDB.execSQL("drop table if exists jessicatable");
        myDB.execSQL("drop table if exists kylietable");
        myDB.execSQL("drop table if exists chloetable");
        myDB.execSQL("drop table if exists settings");
        onCreate(myDB);
    }

    //----------------------------Registration & Login----------------------------------
    public Boolean insertData(String username, String password) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = myDB.insert("users", null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean insertUsername( String username ) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        long result = myDB.insert("users", null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean insertPassword( String password ) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(" password ", password);
        long result = myDB.insert("users", null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean updatedata( String username , String password )
    {

        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contents = new ContentValues();
        contents.put(" username ", username);
        contents.put(" password ", password);
        Cursor cursor = myDB.rawQuery("Select * from users where username=?",new String[]{username});
        if(cursor.getCount()>0)
        {
            long result = myDB.update("users", contents, "username=?", new String[]{username});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else{
            return false;
        }
    }

    public Cursor viewdata()
    {
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("Select * from users",null);
        return cursor;

    }


    public Boolean checkusername(String username) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from users where username = ?", new String[]{username});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean checkusernamePassword(String username, String password) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from users where username = ? and password = ?", new String[]{username, password});
        if (cursor.getCount() > 0) {
            return true;
        }
        else {
            return false;
        }
    }

    //--------------------------------Calorie Tracking----------------------------------------------
    public Boolean insertcaloriedata(String target_calories , String current_calories , String target_protein , String current_protein , String target_carbs , String current_carbs , String target_fats , String current_fats )
    {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(" target_calories ", target_calories);
        contentValues.put(" current_calories ", current_calories);
        contentValues.put(" target_protein ", target_protein);
        contentValues.put(" current_protein ", current_protein);
        contentValues.put(" target_carbs ", target_carbs);
        contentValues.put(" current_carbs ", current_carbs);
        contentValues.put(" target_fats ", target_fats);
        contentValues.put(" current_fats ", current_fats);
        long result = myDB.insert(" Calorietracking ",null,contentValues);
        if(result==-1){
            return false;
        }
        else
        {
            return true;
        }
    }


    public Boolean updatecaloriedata( String target_calories , String current_calories , String target_protein , String current_protein , String target_carbs , String current_carbs , String target_fats , String current_fats )
    {

        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(" target_calories ", target_calories);
        contentValues.put(" current_calories ", current_calories);
        contentValues.put(" target_protein ", target_protein);
        contentValues.put(" current_calories ", current_protein);
        contentValues.put(" target_carbs ", target_carbs);
        contentValues.put(" current_carbs ", current_carbs);
        contentValues.put(" target_fats ", target_fats);
        contentValues.put(" current_fats ", current_fats);
        Cursor cursor = myDB.rawQuery("Select * from CalorieTracking where target_calories=?",new String[]{target_calories});
        if(cursor.getCount()>0)
        {
            long result = myDB.update("Calorietracking", contentValues, "target_calories=?", new String[]{target_calories});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else{
            return false;
        }
    }

    public Boolean deletecaloriedata(String target_calories)
    {
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("Select * from CalorieTracking where target_calories=?",new String[]{target_calories});
        if(cursor.getCount()>0)
        {
            long result = myDB.delete("Calorietracking", "target_calories=?", new String[]{target_calories});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else{
            return false;
        }
    }

    public Cursor viewcaloriedata()
    {
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("Select * from CalorieTracking",null);
        return cursor;

    }

    //--------------------------------------Gym Membership------------------------------------
    public Boolean insertmembershipdata(String membership_length , String payment_method )
    {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put(" membership_length ", membership_length );
        content.put(" payment_method ", payment_method );

        long result = myDB.insert(" Membership ",null, content);
        if(result==-1){
            return false;
        }
        else
        {
            return true;
        }
    }

    public Boolean updatemembershipdata( String membership_length , String payment_method )
    {

        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put(" membership_length ", membership_length);
        content.put(" payment_method ", payment_method);
        Cursor cursor = myDB.rawQuery("Select * from Membership where membership_length=?",new String[]{membership_length});
        if(cursor.getCount()>0)
        {
            long result = myDB.update("Membership", content, "membership_length=?", new String[]{membership_length});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else{
            return false;
        }
    }

    public Boolean deletemembershipdata(String membership_length)
    {
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery(" Select * from Membership where membership_length=? ",new String[]{ membership_length });
        if(cursor.getCount()>0)
        {
            long result = myDB.delete(" Membership ", " membership_length=? ", new String[]{ membership_length });
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else{
            return false;
        }
    }

    public Cursor viewmembershipdata()
    {
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery(" Select * from Membership ",null);
        return cursor;
    }

    //------------------------------------Gym Slot-----------------------------------------
    public Boolean insertgymslotdata( String which_day , String start_time , String end_time )
    {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentslot = new ContentValues();
        contentslot.put(" which_day " , which_day );
        contentslot.put(" start_time " , start_time );
        contentslot.put(" end_time " , end_time);

        long result = myDB.insert(" Gymslot ",null, contentslot);
        if(result==-1){
            return false;
        }
        else
        {
            return true;
        }
    }

    public Boolean updategymslotdata( String which_day , String start_time , String end_time )
    {

        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put(" which_day ", which_day );
        content.put(" start_time ", start_time );
        content.put(" end_time ", end_time );
        Cursor cursor = myDB.rawQuery("Select * from Gymslot where which_day=?",new String[]{which_day});
        if(cursor.getCount()>0)
        {
            long result = myDB.update("Gymslot", content, "which_day=?", new String[]{which_day});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else{
            return false;
        }


    }
    public Boolean deletegymslotdata( String which_day )
    {
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("Select * from Gymslot where which_day=?",new String[]{ which_day });
        if(cursor.getCount()>0)
        {
            long result = myDB.delete(" Gymslot ", " which_day=? ", new String[]{ which_day });
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else{
            return false;
        }
    }

    public Cursor viewgymslotdata()
    {
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery(" Select * from Gymslot ",null);
        return cursor;
    }

    //------------------------------------Workout Tracking------------------------------------
    public Boolean insertworkoutdata(String which_exercise , String target_weight , String current_weight , String target_sets , String current_sets , String target_reps , String current_reps )
    {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contents = new ContentValues();
        contents.put(" which_exercise " , which_exercise );
        contents.put(" target_weight " , target_weight );
        contents.put(" current_weight " , current_weight);
        contents.put(" target_sets " , target_sets);
        contents.put(" current_sets " , current_sets);
        contents.put(" target_reps " , target_reps);
        contents.put(" current_reps " , current_reps);

        long result = myDB.insert(" Workouttracking ",null, contents);
        if(result==-1){
            return false;
        }
        else
        {
            return true;
        }
    }
    public Boolean updateworkoutdata( String which_exercise , String target_weight , String current_weight , String target_sets , String current_sets , String target_reps , String current_reps )
    {

        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put(" which_exercise ", which_exercise );
        content.put(" target_weight ", target_weight );
        content.put(" current_weight ", current_weight );
        content.put(" target_sets ", target_sets);
        content.put(" current_sets ", current_sets);
        content.put(" target_reps ", target_reps);
        content.put(" current_reps", current_reps);
        Cursor cursor = myDB.rawQuery("Select * from Workouttracking where which_exercise=?",new String[]{which_exercise});
        if(cursor.getCount()>0)
        {
            long result = myDB.update(" Workouttracking ", content, " which_exercise=? ", new String[]{which_exercise});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else{
            return false;
        }
    }

    public Boolean deleteworkoutdata( String which_exercise )
    {
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("Select * from Workouttracking where which_exercise=?",new String[]{ which_exercise });
        if(cursor.getCount()>0)
        {
            long result = myDB.delete(" Workouttracking ", " which_exercise=? ", new String[]{ which_exercise });
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else{
            return false;
        }
    }

    public Cursor viewworkoutdata()
    {
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery(" Select * from Workouttracking ",null);
        return cursor;
    }
    //------------------------------------Personal Trainer------------------------------------
    public Boolean inserttrainerdata(String target_performance , String pt_length , String pt_payment )
    {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put(" target_performance " , target_performance );
        content.put(" pt_length " , pt_length );
        content.put(" pt_payment " , pt_payment);


        long result = myDB.insert(" pt_table ",null, content);
        if(result==-1){
            return false;
        }
        else
        {
            return true;
        }
    }
    public Boolean updatetrainerdata( String target_performance , String pt_length , String pt_payment )
    {

        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put(" target_performance ", target_performance );
        content.put(" pt_length ", pt_length );
        content.put(" pt_payment ", pt_payment );

        Cursor cursor = myDB.rawQuery("Select * from pt_table where target_performance=?",new String[]{target_performance});
        if(cursor.getCount()>0)
        {
            long result = myDB.update(" pt_table ", content, " target_performance=? ", new String[]{target_performance});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else{
            return false;
        }
    }

    public Boolean deletetrainerdata( String target_performance )
    {
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("Select * from PersonalTrainer where target_performance=?",new String[]{ target_performance });
        if(cursor.getCount()>0)
        {
            long result = myDB.delete(" pt_table ", " target_performance=? ", new String[]{ target_performance });
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else{
            return false;
        }
    }

    public Cursor viewtrainerdata()
    {
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery(" Select * from pt_table ",null);
        return cursor;
    }
    //****************************** Mike Callis PT Booking *************************************
    public Boolean insertmikedata( String start_pt , String end_pt , String MikeSpinner1 , String MikeSpinner2 )
    {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contents = new ContentValues();
        contents.put(" start_pt " , start_pt);
        contents.put(" end_pt " , end_pt);
        contents.put(" MikeSpinner1 " , MikeSpinner1);
        contents.put(" MikeSpinner2 " , MikeSpinner2);


        long result = myDB.insert("miketable",null, contents);
        if(result==-1){
            return false;
        }
        else
        {
            return true;
        }
    }

    public Cursor viewmikedata()
    {
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery(" Select * from miketable ",null);
        return cursor;
    }

    //****************************** Kyle Vance PT Booking *************************************
    public Boolean insertkyledata(String start_pt2 , String end_pt2 , String KyleSpinner1, String KyleSpinner2 )
    {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contents = new ContentValues();
        contents.put(" start_pt2 " , start_pt2);
        contents.put(" end_pt2 " , end_pt2);
        contents.put(" KyleSpinner1 " , KyleSpinner1);
        contents.put(" KyleSpinner2 " , KyleSpinner2);


        long result = myDB.insert(" kyletable ",null, contents);
        if(result==-1){
            return false;
        }
        else
        {
            return true;
        }
    }

    public Cursor viewkyledata()
    {
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery(" Select * from kyletable ",null);
        return cursor;
    }

    //****************************** Peter Donnington PT Booking *************************************
    public Boolean insertpeterdata(String start_pt3 , String end_pt3 , String PeterSpinner1, String PeterSpinner2 )
    {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contents = new ContentValues();
        contents.put(" start_pt3 " , String.valueOf(start_pt3));
        contents.put(" end_pt3 " , String.valueOf(end_pt3));
        contents.put(" PeterSpinner1 " , PeterSpinner1);
        contents.put(" PeterSpinner2 " , PeterSpinner2);


        long result = myDB.insert(" petertable ",null, contents);
        if(result==-1){
            return false;
        }
        else
        {
            return true;
        }
    }

    public Cursor viewpeterdata()
    {
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery(" Select * from petertable ",null);
        return cursor;
    }

    //****************************** James Reid PT Booking *************************************
    public Boolean insertjamesdata(String start_pt4 , String end_pt4 , String JamesSpinner1, String JamesSpinner2 )
    {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contents = new ContentValues();
        contents.put(" start_pt4 " , start_pt4);
        contents.put(" end_pt4 " , end_pt4);
        contents.put(" JamesSpinner1 " , JamesSpinner1);
        contents.put(" JamesSpinner2 " , JamesSpinner2);


        long result = myDB.insert(" jamestable ",null, contents);
        if(result==-1){
            return false;
        }
        else
        {
            return true;
        }
    }

    public Cursor viewjamesdata()
    {
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery(" Select * from jamestable ",null);
        return cursor;
    }

    //****************************** Sarah Ream PT Booking *************************************
    public Boolean insertsarahdata(String start_pt5 , String end_pt5 , String SarahSpinner1, String SarahSpinner2 )
    {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contents = new ContentValues();
        contents.put(" start_pt5 " , start_pt5);
        contents.put(" end_pt5 " , end_pt5);
        contents.put(" SarahSpinner1 " , SarahSpinner1);
        contents.put(" SarahSpinner2 " , SarahSpinner2);


        long result = myDB.insert(" sarahtable ",null, contents);
        if(result==-1){
            return false;
        }
        else
        {
            return true;
        }
    }

    public Cursor viewsarahdata()
    {
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery(" Select * from sarahtable ",null);
        return cursor;
    }

    //****************************** Jessica Ryan PT Booking *************************************
    public Boolean insertjessicadata(String start_pt6 , String end_pt6 , String JessicaSpinner1, String JessicaSpinner2 )
    {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contents = new ContentValues();
        contents.put(" start_pt6 " , start_pt6);
        contents.put(" end_pt6 " , end_pt6);
        contents.put(" JessicaSpinner1 " , JessicaSpinner1);
        contents.put(" JessicaSpinner2 " , JessicaSpinner2);


        long result = myDB.insert(" jessicatable ",null, contents);
        if(result==-1){
            return false;
        }
        else
        {
            return true;
        }
    }

    public Cursor viewjessicadata()
    {
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery(" Select * from jessicatable ",null);
        return cursor;
    }

    //****************************** Kylie Wall PT Booking *************************************
    public Boolean insertkyliedata(String start_pt7 , String end_pt7 , String KylieSpinner1, String KylieSpinner2 )
    {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contents = new ContentValues();
        contents.put(" start_pt7 " , start_pt7);
        contents.put(" end_pt7 " , end_pt7);
        contents.put(" KylieSpinner1 " , KylieSpinner1);
        contents.put(" KylieSpinner2 " , KylieSpinner2);


        long result = myDB.insert(" kylietable ",null, contents);
        if(result==-1){
            return false;
        }
        else
        {
            return true;
        }
    }

    public Cursor viewkyliedata()
    {
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery(" Select * from kylietable ",null);
        return cursor;
    }

    //****************************** Chloe Wall PT Booking *************************************
    public Boolean insertchloedata(String start_pt8 , String end_pt8 , String ChloeSpinner1, String ChloeSpinner2 )
    {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contents = new ContentValues();
        contents.put(" start_pt8 " , start_pt8);
        contents.put(" end_pt8 " , end_pt8);
        contents.put(" ChloeSpinner1 " , ChloeSpinner1);
        contents.put(" ChloeSpinner2 " , ChloeSpinner2);


        long result = myDB.insert(" chloetable ",null, contents);
        if(result==-1){
            return false;
        }
        else
        {
            return true;
        }
    }

    public Cursor viewchloedata()
    {
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery(" Select * from chloetable ",null);
        return cursor;
    }
    //********************************* Settings **************************
    public Boolean insertemaildata( String email )
    {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentslot = new ContentValues();
        contentslot.put(" email " , email );


        long result = myDB.insert(" settings ",null, contentslot);
        if(result==-1){
            return false;
        }
        else
        {
            return true;
        }
    }

    public Boolean updateemaildata( String email )
    {

        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put(" email ", email );

        Cursor cursor = myDB.rawQuery("Select * from settings where email=?",new String[]{email});
        if(cursor.getCount()>0)
        {
            long result = myDB.update("settings", content, "email=?", new String[]{email});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else{
            return false;
        }
    }

    public Boolean insertnamedata( String name )
    {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put(" name " , name );


        long result = myDB.insert(" settings ",null, content);
        if(result==-1){
            return false;
        }
        else
        {
            return true;
        }
    }

    public Boolean updatenamedata( String name )
    {

        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put(" name ", name );

        Cursor cursor = myDB.rawQuery("Select * from settings where name=?",new String[]{name});
        if(cursor.getCount()>0)
        {
            long result = myDB.update("settings", content, "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else{
            return false;
        }
    }

    public Boolean inserthomedata( String home )
    {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put(" home " , home );


        long result = myDB.insert(" settings ",null, content);
        if(result==-1){
            return false;
        }
        else
        {
            return true;
        }
    }

    public Boolean updatehomedata( String home )
    {

        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contents = new ContentValues();
        contents.put(" home ", home );

        Cursor cursor = myDB.rawQuery("Select * from settings where home=?",new String[]{home});
        if(cursor.getCount()>0)
        {
            long result = myDB.update("settings", contents, "home=?", new String[]{home});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else{
            return false;
        }
    }

    public Boolean insertphonenumberdata(String phonenumber )
    {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put(" phonenumber " , phonenumber);


        long result = myDB.insert(" settings ",null, content);
        if(result==-1){
            return false;
        }
        else
        {
            return true;
        }
    }

    public Boolean updatephonenumberdata( String phonenumber )
    {

        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contents = new ContentValues();
        contents.put(" phonenumber ", phonenumber );

        Cursor cursor = myDB.rawQuery("Select * from settings where phonenumber=?",new String[]{phonenumber});
        if(cursor.getCount()>0)
        {
            long result = myDB.update("settings", contents, "phonenumber=?", new String[]{phonenumber});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else{
            return false;
        }
    }
    public Cursor viewsettingsdata()
    {
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery(" Select * from settings ",null);
        return cursor;
    }
}

