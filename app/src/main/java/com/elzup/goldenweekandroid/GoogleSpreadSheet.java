package com.elzup.goldenweekandroid;

public class GoogleSpreadSheet {
    static GoogleSpreadSheetClient client;

    static public GoogleSpreadSheetClient client() {
        if (client == null) {
            client = new GoogleSpreadSheetClient();
        }
        return client;
    }
}
