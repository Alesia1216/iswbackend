package net.ausiasmarch.iswart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.ausiasmarch.iswart.entity.CompraEntity;
import net.ausiasmarch.iswart.repository.CompraRepository;

@Service
public class CompraService implements ServiceInterface<CompraEntity>{

    @Autowired
    private CompraRepository oCompraRepository;
    
}
