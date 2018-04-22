package com.tiga.firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tiga.recview.model.Stok_Request;


/**
 * Created by adikwidiasmono on 20/11/17.
 */

public class FirebaseDB {

    public static final String REF_AGEN = "AGEN";
    public static final String REF_PENJUALAN = "PENJUALAN";
    public static final String REF_ITEM = "ITEM";
    public static final String REF_STOK_REQUEST = "STOK_REQUEST";
    public static final String REF_TRACK = "TRACK";

    private static FirebaseDB firebaseDB;
    private FirebaseDatabase fDB;

    public static FirebaseDB init() {
        if (firebaseDB == null) {
            firebaseDB = new FirebaseDB();
            firebaseDB.fDB = FirebaseDatabase.getInstance();
        }

        return firebaseDB;
    }

    public DatabaseReference getDBReference(String refName) {
        return fDB.getReference(refName);
    }

    public void addRequest(Stok_Request stok_request) {
        DatabaseReference fRef = fDB.getReference(REF_STOK_REQUEST);

        String requestId = fRef.push().getKey();
        stok_request.setInvoiceId(requestId);
        fRef.child(requestId).setValue(stok_request);
    }

    /*public void addAllergy(Allergy allergy) {
        DatabaseReference fRef = fDB.getReference(REF_ALLERGYS);

        String allergyId = fRef.push().getKey();
        allergy.setAllergyId(allergyId);
        fRef.child(allergyId).setValue(allergy);
    }

    public void addTimeline(TimelineContent content) {
        DatabaseReference fRef = fDB.getReference(REF_TIMELINE_MED_RECS);

        String timelineId = fRef.push().getKey();
        content.setTimelineId(timelineId);
        fRef.child(timelineId).setValue(content);
    }

    public void addHistories(History history) {
        DatabaseReference fRef = fDB.getReference(REF_HISTORIES);

        String historyId = fRef.push().getKey();
        history.setHistoryId(historyId);
        fRef.child(historyId).setValue(history);
    }

    public void addInsuranceProducts(InsuranceProduct product) {
        DatabaseReference fRef = fDB.getReference(REF_INSURANCE_PRODUCT);

        String insuranceProductId = fRef.push().getKey();
        product.setInsuranceProductId(insuranceProductId);
        fRef.child(insuranceProductId).setValue(product);
    }

    public void updateNotif() {
        DatabaseReference fRef = fDB.getReference(REF_NOTIFICATIONS);

        String notifId = "NOTIF_ID";
        fRef.child(notifId).setValue("Notif : " + System.currentTimeMillis());
    }*/

}
