package kr.co.bit.controller;

import kr.co.bit.dao.GuestBookDAO;
import kr.co.bit.vo.GuestBookVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    GuestBookDAO guestBookDAO;

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(Model model){
        System.out.println("메인들어옴");
        model.addAttribute("list",guestBookDAO.searchAll());
        return "list";
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insert(@ModelAttribute GuestBookVO guestBookVO){
        System.out.println("삽입들어옴");
        guestBookDAO.insert(guestBookVO);
        return "redirect:/main";
    }

    @RequestMapping(value = "/deleteform/{contentNO}", method = RequestMethod.GET)
    public String deleteform(@PathVariable("contentNO") int no, Model model){
        System.out.println("삭제폼들어옴");
        System.out.println("삭제폼에 들어온 게시물번호는 "+no);
        model.addAttribute("contentNO",no);
        return "deleteform";
    }

    @RequestMapping(value = "/delete/{contentNO}", method = RequestMethod.POST)
    public String delete(@PathVariable("contentNO") int no, @RequestParam("password") String password){
        System.out.println("삭제들어옴");
        System.out.println("삭제하러 들어온 게시물번호는 "+no);
        System.out.println("삭제하러 들어온 비밀번호는 "+password);
        boolean flag = guestBookDAO.delete(no,password);
        if(flag == true){
            System.out.println("삭제 성공");
        }else{
            System.out.println("삭제 실패(비밀번호 불일치)");
            return "error";
        }
        return "redirect:/main";
    }
}
