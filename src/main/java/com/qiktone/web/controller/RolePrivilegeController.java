package com.qiktone.web.controller;


import com.qiktone.entity.RolePrivilege;
import com.qiktone.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tom on 16/3/5.
 */
@Controller
@RequestMapping("/privilege")
public class RolePrivilegeController extends BaseController{

    @Autowired
    private RolePrivilegeRepository privilegeRepository;

  /*  @RequestMapping(path = "/modules/{rid}")
    public@ResponseBody Map<String,List<RolePrivilege>> modules(@PathVariable Long rid){
        Map<String,List<RolePrivilege>> rs = new HashMap<String, List<RolePrivilege>>();
        List<RolePrivilege> modules = privilegeRepository.getModuleByRole(rid);
        List<RolePrivilege> sysModules = privilegeRepository.getModuleByRole(0L);
        rs.put("modules",modules);
        rs.put("sysModules",sysModules);
        return rs;
    }

    @RequestMapping(path = "/functions/{rid}/{mname}")
    public @ResponseBody  List<RolePrivilege> functions(@PathVariable Long rid,@PathVariable String mname){
        return privilegeRepository.getFunctionByModuleNameAndRole(mname,rid);
    }

    @RequestMapping(path = "/ops/{rid}/{oname}")
    public @ResponseBody List<RolePrivilege> ops(@PathVariable Long rid,@PathVariable String oname){
        return privilegeRepository.getFunctionByModuleNameAndRole(oname, rid);
    }*/
}
