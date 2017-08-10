package hsc.marketingmessager.data.presenter;

import android.content.Context;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * Created by Hoang Ha on 8/5/2017.
 */

public class LoadAdUnitId extends BasePresenter implements ValueEventListener {
    private DatabaseReference dbReference;

    public LoadAdUnitId(Context context) {
        super(context);
        dbReference = FirebaseDatabase.getInstance().getReference("AdUnit");

    }

    @Override
    public void load() {
        dbReference.addValueEventListener(this);
    }

    @Override
    public void loadCom() {

    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        for (DataSnapshot s : dataSnapshot.getChildren()) {
            for (DataSnapshot s1 : s.getChildren()) {

            }
        }
        loadCom();
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
}
