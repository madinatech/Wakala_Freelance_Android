package com.os.paytzwakal.reg.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {RegistartionData.class}, version = 1)
public abstract class RegistrationDatabase extends RoomDatabase {

    private static final String DB_NAME = "registrationdata.db";

    private static volatile RegistrationDatabase instance;

    public static synchronized RegistrationDatabase getInstance(Context context) {
        if (instance == null) {
            instance = create(context);
        }
        return instance;
    }

    private static RegistrationDatabase create(final Context context) {
        return Room.databaseBuilder(
                context,
                RegistrationDatabase.class,
                DB_NAME).build();
    }

    public abstract RegistrationDao getRegistrationDao();
}
