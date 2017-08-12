package hsc.marketingmessager.view.addnumber.fragment;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import hsc.marketingmessager.R;
import hsc.marketingmessager.adapter.ReadFromContactAdapter;
import hsc.marketingmessager.data.model.NumberOnExcel;
import hsc.marketingmessager.view.base.BaseFragment;

/**
 * Created by Hoang Ha on 8/6/2017.
 */

public class AddNumberFromContact extends BaseFragment implements CompoundButton.OnCheckedChangeListener {
    @BindView(R.id.rl_open_contact)
    RelativeLayout rlOpenContact;
    @BindView(R.id.rv_contact)
    RecyclerView recyclerView;
    @BindView(R.id.cb_all)
    CheckBox cbAll;
    private boolean isLoadContact = false;
    private ArrayList<NumberOnExcel> numberOnExcels;
    ReadFromContactAdapter readFromContactAdapter;

    @Override
    protected int setContentView() {
        return R.layout.add_number_from_contact;
    }

    @Override
    protected void initVariable() {
        numberOnExcels = new ArrayList<>();
        readFromContactAdapter = new ReadFromContactAdapter(getContext(), numberOnExcels);
    }

    @Override
    protected void init() {
        recyclerView.setAdapter(readFromContactAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHorizontalScrollBarEnabled(true);
        cbAll.setOnCheckedChangeListener(this);
        if (isLoadContact)
            rlOpenContact.setVisibility(View.INVISIBLE);
    }

    @OnClick(R.id.rl_open_contact)
    public void setRlOpenContactClick(View view) {
        if (checkPermission(Manifest.permission.READ_CONTACTS)) {
            isLoadContact = true;
            rlOpenContact.setVisibility(View.INVISIBLE);
            ReadContactAsynTask readContactAsynTask = new ReadContactAsynTask(getContext());
            readContactAsynTask.execute();
        }
    }

    @Override
    protected boolean isRegisterBus() {
        return true;
    }


    public boolean checkPermission(String permission) {
        boolean isGranted = true;
        if (ContextCompat.checkSelfPermission(getContext(), permission) != PackageManager.PERMISSION_GRANTED) {
            isGranted = false;
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{permission},
                    READ_CONTACT);
        }
        return isGranted;
    }

    @Override
    public <T> void onEvent(T t) {
        super.onEvent(t);
        if (t instanceof String) {
            if (((String) t).equals("REQUEST_READ_CONTACT_GRANTED")) {
                isLoadContact = true;
                rlOpenContact.setVisibility(View.INVISIBLE);
                ReadContactAsynTask readContactAsynTask = new ReadContactAsynTask(getContext());
                readContactAsynTask.execute();
            }
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        for (int i = 0; i < numberOnExcels.size(); ++i)
            numberOnExcels.get(i).setCheck(isChecked);
        readFromContactAdapter.setNumberOnExcels(numberOnExcels);
    }

    public class ReadContactAsynTask extends AsyncTask<Void, NumberOnExcel, Void> {
        Context mContext;

        public ReadContactAsynTask(Context context) {
            mContext = context;
        }

        @Override
        protected Void doInBackground(Void... params) {
            Uri CONTENT_URI = ContactsContract.Contacts.CONTENT_URI;
            String _ID = ContactsContract.Contacts._ID;
            String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;
            String HAS_PHONE_NUMBER = ContactsContract.Contacts.HAS_PHONE_NUMBER;
            Uri PhoneCONTENT_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
            Uri AddCONTENT_URI = ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_URI;
            String AddCONTENT_URI_ID = ContactsContract.CommonDataKinds.StructuredPostal.CONTACT_ID;
            String Phone_CONTACT_ID = ContactsContract.CommonDataKinds.Phone.CONTACT_ID;
            String NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;
            Uri EmailCONTENT_URI = ContactsContract.CommonDataKinds.Email.CONTENT_URI;
            String EmailCONTACT_ID = ContactsContract.CommonDataKinds.Email.CONTACT_ID;
            String DATA = ContactsContract.CommonDataKinds.Email.DATA;
            ContentResolver contentResolver = getContext().getContentResolver();
            Cursor cursor = contentResolver.query(CONTENT_URI, null, null, null, null);
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    NumberOnExcel numberOnExcel = new NumberOnExcel();
                    String contact_id = cursor.getString(cursor.getColumnIndex(_ID));
                    String name = cursor.getString(cursor.getColumnIndex(DISPLAY_NAME));
                    numberOnExcel.setName(name);
                    int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex(HAS_PHONE_NUMBER)));
                    if (hasPhoneNumber > 0) {
                        Cursor phoneCursor = contentResolver.query(PhoneCONTENT_URI, null, Phone_CONTACT_ID + " = ?", new String[]{contact_id}, null);
                        while (phoneCursor.moveToNext()) {
                            numberOnExcel.setPhoneNumber(phoneCursor.getString(phoneCursor.getColumnIndex(NUMBER)));
                            break;
                        }
                        phoneCursor.close();
                        Cursor emailCursor = contentResolver.query(EmailCONTENT_URI, null, EmailCONTACT_ID + " = ?", new String[]{contact_id}, null);
                        while (emailCursor.moveToNext()) {
                            numberOnExcel.setEmail(emailCursor.getString(emailCursor.getColumnIndex(DATA)));
                            break;
                        }
                        emailCursor.close();

                        Uri postal_uri = ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_URI;
                        Cursor postal_cursor = contentResolver.query(postal_uri, null, ContactsContract.Data.CONTACT_ID + "=" + contact_id, null, null);
                        while (postal_cursor.moveToNext()) {
                            try {
                                String Strt = postal_cursor.getString(postal_cursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.STREET));
                                String Cty = postal_cursor.getString(postal_cursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.CITY));
                                String cntry = postal_cursor.getString(postal_cursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.COUNTRY));
                                String s = "";
                                if (Strt != null)
                                    s += Strt;
                                if (Cty != null)
                                    s += ", " + Cty;
                                if (cntry != null)
                                    s += ", " + cntry;
                                numberOnExcel.setAddress(s);
                            } catch (Exception e) {
                            }
                            break;
                        }
                        postal_cursor.close();

                        String columns[] = {
                                ContactsContract.CommonDataKinds.Event.START_DATE,
                                ContactsContract.CommonDataKinds.Event.TYPE,
                                ContactsContract.CommonDataKinds.Event.MIMETYPE,
                        };

                        String where = ContactsContract.CommonDataKinds.Event.TYPE + "=" + ContactsContract.CommonDataKinds.Event.TYPE_BIRTHDAY +
                                " and " + ContactsContract.CommonDataKinds.Event.MIMETYPE + " = '" + ContactsContract.CommonDataKinds.Event.CONTENT_ITEM_TYPE + "' and " + ContactsContract.Data.CONTACT_ID + " = " + contact_id;

                        String[] selectionArgs = null;
                        String sortOrder = ContactsContract.Contacts.DISPLAY_NAME;

                        Cursor birthdayCur = contentResolver.query(ContactsContract.Data.CONTENT_URI, columns, where, selectionArgs, sortOrder);
                        if (birthdayCur.getCount() > 0) {
                            while (birthdayCur.moveToNext()) {
                                String birthday = birthdayCur.getString(birthdayCur.getColumnIndex(ContactsContract.CommonDataKinds.Event.START_DATE));
//                            output.append("\nNgày sinh: " + birthday);
                                numberOnExcel.setBirthday(birthday);
                                break;
                            }
                        }
                        birthdayCur.close();
                        publishProgress(numberOnExcel);
                    }

                }

            }
            return null;
        }

        @Override
        protected void onProgressUpdate(NumberOnExcel... values) {
            super.onProgressUpdate(values);
            numberOnExcels.add(values[0]);
//            LogE(values[0].toString());
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            readFromContactAdapter.notifyDataSetChanged();
        }
    }
}
