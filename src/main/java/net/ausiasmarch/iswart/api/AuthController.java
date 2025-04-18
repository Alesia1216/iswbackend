package net.ausiasmarch.iswart.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.ausiasmarch.iswart.bean.LogindataBean;
import net.ausiasmarch.iswart.service.AuthService;

@CrossOrigin (origins = "*", allowedHeaders = "*", maxAge = 3600) 
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService oAuthService;
    
    //TODO metodo registrar

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LogindataBean oLogindataBean) {  
        if(oAuthService.checkLogin(oLogindataBean)) {
            return ResponseEntity.ok("\"" + oAuthService.getToken(oLogindataBean.getEmail()) + "\"");
        }else{
            return ResponseEntity.status(401).body( "\"" + "Unauthorized" + "\"");
        }
    }
    
}
