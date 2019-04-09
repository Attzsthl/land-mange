package com.lete.land.landcontroller.endPoint;

import com.lete.land.landdal.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by WJ on 2019/4/2 0002
 */
@Controller
public class FileController {
    @RequestMapping("/api/fileUpload")
    @ResponseBody
    public Map fileUpload(HttpServletRequest servletRequest) {
        MultipartHttpServletRequest request = (MultipartHttpServletRequest) servletRequest;
        Iterator<String> itr = request.getFileNames();
        MultipartFile proFile = null;
        while (itr.hasNext()) {
            String str = itr.next();
            proFile = request.getFile(str);
        }
        String fileDir = request.getSession().getServletContext().getRealPath("/tmp");
        File dir = new File(fileDir);
        Map resMap = null;
        File file = null;
        try {
            file = new File(fileDir, proFile.getOriginalFilename());
            if (!dir.exists()) {
                dir.mkdir();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            proFile.transferTo(file);
            Date a = new Date();
            resMap = new HashMap();
            Date b = new Date();
            return resMap;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (file != null && file.exists()) {
                file.delete();
            }
        }
        resMap.put("returncode", "999");
        resMap.put("msg", "程序异常，请联系管理员");
        return resMap;
    }

}
