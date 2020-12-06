package controllers;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import javax.validation.Valid;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import entidades.User;
import services.UserService;
import  dto.UserRegistrationDto;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    @Autowired
    private UserService userService;

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        return "pages/registration";
    }
    public Optional<String> getExtensionByStringHandling(String filename) {
        return Optional.ofNullable(filename)
          .filter(f -> f.contains("."))
          .map(f -> f.substring(filename.lastIndexOf(".") + 1));
    }
    @PostMapping
    public String registerUserAccount(@RequestParam("file") File file ,@ModelAttribute("user") @Valid UserRegistrationDto userDto,
                                      BindingResult result, Model model){

        User existing = userService.findByEmail(userDto.getEmail());
        if (existing != null){
            result.rejectValue("email", null, "There is already an account registered with that email");
        }

       String path = file.getAbsolutePath();
        
        if (file.exists()) {
        	model.addAttribute("message", "Seleccione un archivo valido");
            return "pages/registration";
        } 
        List arrayImagesExtends =  new ArrayList();
        arrayImagesExtends.add("png");
        String nameFile = file.getName();
        //String extension = nameFile.spl; 
      //  String extension = FilenameUtils.getExtension(file.getPath());
        Optional<String> extension = this.getExtensionByStringHandling(nameFile);
         if(arrayImagesExtends.contains(extension.get())){
        	 String UPLOADED_FOLDER ="static/images/avatarsUsers/";
            /* try {
            	 String rutaCompleta = file.getAbsolutePath();
            	 
				//IOUtils.readFully(, buffer);.

             } catch (IOException e) {
                 e.printStackTrace();
             }*/
        	 
        	 
        }else {
        	model.addAttribute("errorArchivo", "Suba un archivo de imagen");
        	return "pages/registration";
        }
        	
        
        
 
        
        
        
        if (result.hasErrors()){
            return "pages/registration";
        }else {
        	model.addAttribute("exitoCrear", "Cliente creado con exito");
        	
        userService.save(userDto);
        }
         return "pages/crud/modalExitoGenerica";
    }

}
