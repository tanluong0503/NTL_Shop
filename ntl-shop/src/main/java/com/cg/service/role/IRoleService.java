package com.cg.service.role;

import com.cg.model.Role;
import com.cg.service.IGeneralService;
import org.springframework.stereotype.Service;


public interface IRoleService extends IGeneralService<Role> {
    Role findByName(String name);
}
