package com.mehta.applications.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mehta.applications.common.vo.SystemVo;
import com.mehta.applications.model.System;
import com.mehta.applications.repository.SystemRepository;
import com.mehta.applications.service.SystemService;

@Service
public class SystemServiceImpl implements SystemService {

	@Autowired
	SystemRepository systemRepository;
	
	@Override
	public SystemVo readSystem(Integer id) {
		SystemVo systemVo= null;
		if (id != null) {
			System system = systemRepository.findOne(id);
			
			if (system != null) {
				systemVo = new SystemVo();
				systemVo.setId(system.getId());
				systemVo.setCreated(system.getCreated());
				systemVo.setCreatedBy(system.getCreatedBy());
				systemVo.setLastModified(system.getLastModified());
				systemVo.setLastModifiedBy(system.getLastModifiedBy());
				systemVo.setStorageFolderName(system.getStorageFolderName());
			}
		}
		return systemVo;
	}

	@Override
	public Integer createSystem(SystemVo systemVo) {
		Integer id = null;
		if (systemVo != null) {
			System system = new System();
			system.setCreated(systemVo.getCreated());
			system.setCreatedBy(systemVo.getCreatedBy());
			system.setLastModified(systemVo.getLastModified());
			system.setLastModifiedBy(systemVo.getLastModifiedBy());
			system.setStorageFolderName(systemVo.getStorageFolderName());
		
			system= systemRepository.save(system);
			if (system != null && system.getId()!= null)
				return system.getId();
		}
		return id;
	}

	@Override
	public Boolean updateSystem(SystemVo systemVo) {
		if (systemVo != null && systemVo.getId()!=null) {
			System system = systemRepository.findOne(systemVo.getId());
			if (system != null) {
				system.setCreated(systemVo.getCreated());
				system.setCreatedBy(systemVo.getCreatedBy());
				system.setLastModified(systemVo.getLastModified());
				system.setLastModifiedBy(systemVo.getLastModifiedBy());
				system.setStorageFolderName(systemVo.getStorageFolderName());
				
				systemRepository.save(system);
				return true;
			}
		}
		return false;
	}
	
	
	@Override
	public Boolean deleteSystem(Integer id) {
		if (id != null) {
			System system = systemRepository.findOne(id);
			if (system != null && system.getId() != null) {
				systemRepository.delete(id);
				return true;
			}
		}
		return false;
	}

	@Override
	public List<SystemVo> listSystem() {
		List<SystemVo> systemVoList = null;
		List<System> listSystem = (List<System>) systemRepository.findAll();
		if (listSystem != null) {
			systemVoList = new ArrayList<SystemVo>();
			for (System system : listSystem) {
				if (system != null) {
					SystemVo systemVo = new SystemVo();
					systemVo.setId(system.getId());
					systemVo.setCreated(system.getCreated());
					systemVo.setCreatedBy(system.getCreatedBy());
					systemVo.setLastModified(system.getLastModified());
					systemVo.setLastModifiedBy(system.getLastModifiedBy());
					systemVo.setStorageFolderName(system.getStorageFolderName());
					systemVoList.add(systemVo);
				}
			}
		}
		return systemVoList;
	}

}
