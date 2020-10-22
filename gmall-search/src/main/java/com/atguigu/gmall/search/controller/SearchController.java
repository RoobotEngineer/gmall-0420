package com.atguigu.gmall.search.controller;

import com.atguigu.gmall.search.entity.SearchParamVo;
import com.atguigu.gmall.search.entity.SearchResponseVo;
import com.atguigu.gmall.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author wh
 * @user wh
 * @create 2020-09-27
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @Autowired
    private SearchService searchService;
    @GetMapping
    public String search(SearchParamVo paramVo, Model model){
        SearchResponseVo responseVo=this.searchService.search(paramVo);
        model.addAttribute("response",responseVo);
        model.addAttribute("searchParam",paramVo);
        return "search";
    }

}
