package com.shanian.libbase.api.impl;

import com.shanian.libbase.api.Api;

public class LoginConfig extends DefaultConfig {

    @Override
    protected String getHttpUrlStr() {
        if (Api.RELEASE.equals(environment)) {
            return "https://api.miguvideo.com";

        } else if (Api.DEV_RELEASE.equals(environment)) {
            return "https://api.miguvideo.com";

        } else if (Api.DEV_TEST.equals(environment)) {
            return "http://117.131.17.78:28080/";

        } else if (Api.DEV_CUSTOM.equals(environment)) {
            return "https://api.miguvideo.com";

        } else {
            return "https://api.miguvideo.com";
        }
    }
}
