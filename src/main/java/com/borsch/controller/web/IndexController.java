package com.borsch.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
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

    private void facebookPost(HttpServletRequest request, Model model) {
        Map<String, Object> map = new HashMap<>();

        for (Object key : request.getParameterMap().keySet()) {
            map.put(key.toString(), request.getParameter(key.toString()));
        }

        map.put("query", request.getQueryString());

        model.addAttribute("map", map);
    }
}
