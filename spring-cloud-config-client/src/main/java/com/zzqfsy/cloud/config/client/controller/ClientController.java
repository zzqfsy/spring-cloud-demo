package com.zzqfsy.cloud.config.client.controller;

import com.zzqfsy.cloud.config.client.utils.HttpUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Created by john on 16-10-28.
 */
@RestController
public class ClientController {
    private static final String CREATE_COMPANY_URL = "http://localhost:8066/api/companys/";
    HttpUtils.ParamMap paramMap = new HttpUtils.ParamMap().putParam("companyId", "132");

    @RequestMapping("/createCompany")
    public String IAmClient() {
        try {
            return HttpUtils.post(CREATE_COMPANY_URL, paramMap);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "error";
    }
}
