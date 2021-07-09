package com.example.ujianlima.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.ujianlima.entity.FormModel;
import com.example.ujianlima.interfaces.FormRepository;
import com.example.ujianlima.util.FileUploadUtil;

@Controller
public class HomePage {
	@Autowired
	FormRepository formRepo;

	@GetMapping("/")
	public String viewIndex(Model model) {
		model.addAttribute("formmodel", new FormModel());
		model.addAttribute("listdata", this.formRepo.findAll());
		
		return "index.html";
	}
	
	@PostMapping("/add")
	public String addContact(@RequestParam("name")String name,@RequestParam("email")String email,
			@RequestParam("platform")String platform,@RequestParam("cv") MultipartFile file,Model model) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		FormModel formModel = new FormModel(0,name,email,platform,fileName);
		formModel.setCv(fileName);
		this.formRepo.save(formModel);
		
		String uploadDir = "C:/cvupload/"+fileName;
		try {
			FileUploadUtil.saveFile(uploadDir, fileName, file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		return "redirect:/";
	}
}
