package com.oc.greenbean.spring.controller;

import com.oc.greenbean.config.GreenbeanConfig;
import com.oc.greenbean.domain.User;
import com.oc.greenbean.spring.service.MyBookService;
import com.oc.greenbean.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.UUID;


@Controller
public class HomeController {

    private MyBookService myBookService;
    private UserService userService;

    //XXX 解决与DispatcherServletConfig重复
    @Value("${picturesPath}")
    private String picturesPath;

    @Autowired
    public HomeController(MyBookService myBookService, UserService userService) {
        this.myBookService = myBookService;
        this.userService = userService;
    }

    @RequestMapping(value = "/home")
    public String home(Model model, Principal principal) {
        String username = principal.getName();
        User user = userService.getUserByUsername(username);
        Integer userId = user.getId();
        Integer readingCount = myBookService.getMyBookCount(GreenbeanConfig.READING_BOOK_TYPE, userId);
        Integer readCount = myBookService.getMyBookCount(GreenbeanConfig.READ_BOOK_TYPE, userId);
        model.addAttribute("readingCount", readingCount);
        model.addAttribute("readCount", readCount);

        List<String> readingPictures = myBookService.getMyBookPictures(GreenbeanConfig.READING_BOOK_TYPE, userId);
        List<String> readPictures = myBookService.getMyBookPictures(GreenbeanConfig.READ_BOOK_TYPE, userId);
        model.addAttribute("readingPictures", readingPictures);
        model.addAttribute("readPictures", readPictures);

        return "home";
    }

    @RequestMapping("/setting")
    public String setting() {
        return "setting";
    }

    @RequestMapping(value = "/updateUserSettings")
    @ResponseBody
    public String updateUserSettings(@RequestParam(value = "nickname")String nickname, @RequestParam(value = "avatar", required = false) MultipartFile avatar, Principal principal, HttpSession session) throws IOException {
        String username = principal.getName();
        userService.updateNickname(username, nickname);
        session.setAttribute("userNickname", nickname);
        String avatarFileName = null;
        if(avatar != null) {
            String avatarFileOriginalFileName = avatar.getOriginalFilename();
            String avatarFileExtension = avatarFileOriginalFileName.substring(avatarFileOriginalFileName.lastIndexOf('.'));
            avatarFileName = UUID.randomUUID().toString() + avatarFileExtension;
            String userHomePath = System.getProperty("user.home").replaceAll("\\\\", "/");
            String picturesPath = userHomePath + this.picturesPath;
            File avatarFolder = new File(picturesPath + "/avatars/");
            if(!avatarFolder.exists()) {
                avatarFolder.mkdir();
            }
            File avatarFile = new File(avatarFolder, avatarFileName);
            //TODO 删除旧头像
            avatar.transferTo(avatarFile.toPath());
            userService.updateAvatar(username, avatarFileName);
            session.setAttribute("userAvatar", avatarFileName);
        }
        return avatarFileName;
    }
}
