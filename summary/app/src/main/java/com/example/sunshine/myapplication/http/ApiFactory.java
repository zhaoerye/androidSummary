package com.example.sunshine.myapplication.http;


import com.example.sunshine.myapplication.http.Api.ApiTest;

/**
 * Created by liyu on 2016/8/25.
 */
public class ApiFactory {
    protected static final Object monitor = new Object();

    private static ApiTest commonController;
//    private static ServiceController serviceController;
//    private static DocumentController documentController;

    public static ApiTest getCommonController() {
        if (commonController == null) {
            synchronized (monitor) {
                commonController = RetrofitManager.getInstance().create(ApiTest.class);
            }
        }
        return commonController;
    }
//
//    public static ServiceController getServiceController() {
//        if (serviceController == null) {
//            synchronized (monitor) {
//                serviceController = RetrofitManager.getInstance().create(ServiceController.class);
//            }
//        }
//        return serviceController;
//    }
//
//    public static DocumentController getDocumentController() {
//        if (documentController == null) {
//            synchronized (monitor) {
//                documentController = RetrofitManager.getInstance().create(DocumentController.class);
//            }
//        }
//        return documentController;
//    }
//
//
//    public static void reset() {
//        commonController = null;
//        serviceController = null;
//        documentController = null;
//    }


}
