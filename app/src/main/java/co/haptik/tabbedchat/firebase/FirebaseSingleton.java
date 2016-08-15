package co.haptik.tabbedchat.firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import co.haptik.tabbedchat.models.MessageListObject;

/**
 * Created by vijayagnihotri on 16/08/16.
 */
public class FirebaseSingleton {

    public static FirebaseSingleton instance = null;
    private static DatabaseReference databaseRef = null;
    public static final String MESSAGE_REF_KEY = "data";

    public static synchronized FirebaseSingleton getInstance() {
        if(instance == null) {
            instance = new FirebaseSingleton();
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            database.setPersistenceEnabled(true);
            databaseRef = database.getReference();
        }
        return instance;
    }

    public DatabaseReference getDatabaseRef() {
        return databaseRef;
    }

    public void setValue(MessageListObject messageListObject) {
        databaseRef.child(MESSAGE_REF_KEY).setValue(messageListObject);
    }
}
