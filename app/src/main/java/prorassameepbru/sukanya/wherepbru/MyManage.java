package prorassameepbru.sukanya.wherepbru;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by android on 27/5/2559.
 */
public class MyManage {

    private MyOpenHelper myOpenHelper;
    private SQLiteDatabase sqLiteDatabase;

    public static final String room_table = "roomTABLE";
    public static final String colum_id = "_id";
    public static final String colum_Build = "Build";
    public static final String colum_Room = "Room";
    public static final String colum_Lat = "Lat";
    public static final String colum_Lng = "Lng";
    public static final String colum_Icon = "Icon";


    public MyManage(Context context) {
        myOpenHelper = new MyOpenHelper(context);
        sqLiteDatabase = myOpenHelper.getReadableDatabase();
    } //Constructor

    public long addRoom(String strBuild,
                        String strRoom,
                        String strLat,
                        String strLng,
                        String strIcon) {

        ContentValues contentValues=new ContentValues();
        contentValues.put(colum_Build,strBuild);
        contentValues.put(colum_Room,strRoom);
        contentValues.put(colum_Lat,strLat);
        contentValues.put(colum_Lng,strLng);
        contentValues.put(colum_Icon,strIcon);

        return sqLiteDatabase.insert(room_table,null,contentValues);
    }


} //Main Class
