package com.borsch.controller.web;

import com.borsch.domain.FacebookData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by oleh_kurpiak on 04.06.16.
 */
@Controller
public class IndexController {

    @RequestMapping(value = "/facebook", method = RequestMethod.GET)
    public String indexGet(
            HttpServletRequest request,
            Model model
    ){
        facebookPost(request, model);
        return "main/index";
    }

    @RequestMapping(value = "/facebook", method = RequestMethod.POST)
    public String indexPost(
            HttpServletRequest request,
            Model model
    ){
        facebookPost(request, model);
        return "main/index";
    }

    @RequestMapping(value = "/facebook_token", method = RequestMethod.POST)
    public String parseToken(
            HttpServletRequest request,
            Model model
    ) {
        String id = request.getParameter("id");
        String token = request.getParameter("token");
        String data = null;
        try {
            data = readResponseBody(
                    "https://graph.facebook.com/v2.10/"+id+"?access_token="+token+"&fields=email"
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

        model.addAttribute("data", data);
        return "main/parse_token";
    }

    private void facebookPost(HttpServletRequest request, Model model) {
        Map<String, Object> map = new HashMap<>();

        for (Object key : request.getParameterMap().keySet()) {
            map.put(key.toString(), request.getParameter(key.toString()));
        }

        map.put("query", request.getQueryString());
        map.put("method", request.getMethod());

        model.addAttribute("map", map);
    }

    private String readResponseBody(final String url) throws IOException {
        URL rest = new URL(url);
        URLConnection connection = rest.openConnection();
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
        String line;
        StringBuilder jsonBody = new StringBuilder();
        while ((line = br.readLine()) != null) {
            jsonBody.append(line);
        }
        br.close();
        return jsonBody.toString();
    }
}
