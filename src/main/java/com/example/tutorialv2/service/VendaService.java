package com.example.tutorialv2.service;

import com.example.tutorialv2.data.vo.v1.VendaVo;
import com.example.tutorialv2.mapper.DozerMapper;
import com.example.tutorialv2.model.Venda;
import com.example.tutorialv2.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class VendaService {

    @Autowired
    VendaRepository vendaRepository;
    public Venda realizarVenda(VendaVo venda) throws Exception {

        var enity = DozerMapper.parseObject(venda, Venda.class);

        Date date = new Date();
        enity.setDataVenda(date);
        // MSG

        return DozerMapper.parseObject(vendaRepository.save(enity), Venda.class);
    }

}
