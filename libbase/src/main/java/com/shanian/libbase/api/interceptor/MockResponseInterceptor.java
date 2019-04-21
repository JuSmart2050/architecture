package com.shanian.libbase.api.interceptor;

import com.shanian.libbase.utils.FileUtil;

import java.io.File;
import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MockResponseInterceptor implements Interceptor {


    public MockResponseInterceptor() {

    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request().newBuilder().build();
        Response response = chain.proceed(request);
        String url = response.request().url().toString();
        int index = url.contains("?") ? url.indexOf("?") : url.length();
        url = url.substring(0, index)
                .replace(":", "")
                .replace("/", "");


        File file = new File(FileUtil.getSDPath() + File.separator + "mock");
        if (file != null && file.isDirectory() && file.exists()) {
            File[] files = file.listFiles();
            for (File mockFile : files) {
                if (mockFile.isFile()) {
                    String fileName = mockFile.getName();
                    fileName = fileName.substring(0, fileName.indexOf("."));
                    if (url.contains(fileName)) {
                        String responseString = FileUtil.getJsonData(mockFile);
                        Response mockResponse = new Response.Builder()
                                .code(200)
                                .message(responseString)
                                .request(chain.request())
                                .protocol(Protocol.HTTP_1_1)
                                .body(ResponseBody.create(MediaType.parse("application/json"), responseString.getBytes()))
                                .addHeader("content-type", "application/json")
                                .build();
                        return mockResponse;
                    }
                }
            }
        }
        return response;
    }
}
