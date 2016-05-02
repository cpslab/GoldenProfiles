package com.elzup.goldenprofile.managers;

import com.elzup.goldenprofile.network.GoogleSpreadSheetClient;

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
