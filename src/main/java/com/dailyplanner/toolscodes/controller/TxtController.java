package com.dailyplanner.toolscodes.controller;

import com.dailyplanner.toolscodes.utils.ExcelUtil;
import com.dailyplanner.toolscodes.utils.TxtUtil;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/txt")
public class TxtController {

    @RequestMapping("/parse")
    public Boolean parse() throws Exception {
        String s1  = TxtUtil.readTxt(new File("F:\\study\\temp.txt"));
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(s1, JsonObject.class);
        Boolean result = ExcelUtil.saveJsonDataToExcel(jsonObject.getAsJsonArray("data"));
        return result;
    }
}
