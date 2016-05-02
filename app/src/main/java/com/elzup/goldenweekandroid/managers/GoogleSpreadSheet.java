package com.elzup.goldenweekandroid.managers;

import com.elzup.goldenweekandroid.network.GoogleSpreadSheetClient;

public class GoogleSpreadSheet {
    // Client の Singleton
    static GoogleSpreadSheetClient client;

    static public GoogleSpreadSheetClient client() {
        if (client == null) {
            client = new GoogleSpreadSheetClient();
        }
        return client;
    }
}
