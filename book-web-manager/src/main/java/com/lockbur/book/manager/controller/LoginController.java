package com.lockbur.book.manager.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Created by Administrator on 2016/10/21.
 */
@Controller
@RequestMapping("/")
public class LoginController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String displayLogin() {
        logger.info("displayLogin");
        return "list";
    }
}
