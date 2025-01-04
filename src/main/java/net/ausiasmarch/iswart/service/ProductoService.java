package net.ausiasmarch.iswart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.ausiasmarch.iswart.entity.ProductoEntity;
import net.ausiasmarch.iswart.repository.ProductoRepository;

@Service
public class ProductoService implements ServiceInterface<ProductoEntity> {
    
    @Autowired
    private ProductoRepository oProductoRepository;
}
