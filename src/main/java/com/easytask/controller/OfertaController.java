package com.easytask.controller;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.easytask.model.Oferta;
import com.easytask.model.Pedido;
import com.easytask.model.Usuario;
import com.easytask.model.enumeracoes.Status;
import com.easytask.repository.OfertaRepository;
import com.easytask.repository.PedidoRepository;
import com.easytask.service.implementacao.UsuarioService;

@RestController
@RequestMapping("/oferta")
public class OfertaController {

    @Autowired
    private OfertaRepository ofertaRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PedidoRepository pedidoRepository;

    @RequestMapping("/nova")
    public String novaOferta (@RequestParam("valor") BigDecimal valor,
                              @RequestParam("username") String username,
                              @RequestParam("id") Long id) {
        Usuario usuario = usuarioService.findUserByUsername(username);
        Pedido pedido = pedidoRepository.findOne(id);
        Oferta oferta = new Oferta(valor, usuario, pedido);
        ofertaRepository.saveAndFlush(oferta);
        pedido.addOferta(oferta);
        pedidoRepository.save(pedido);
        return "Oferta adicionada com sucesso";
    }

    @RequestMapping("/aceitar")
    public String aceitarOferta (
            @RequestParam("oferta") Long oid,
            @RequestParam("pedido") Long pid) {
        Pedido pedido = pedidoRepository.findOne(pid);
        Oferta oferta = ofertaRepository.findOne(oid);
        Usuario usuario = usuarioService.findUserByUsername(oferta.getUsuario().getUsername());
        pedido.getOfertas().clear();
        pedido.setStatus(Status.ATIVO);
        pedido.setDataInicio(new Date());
        pedido.setValor(oferta.getValor());
        pedido.setPrevisaoFim();
        pedido.setProfissional(usuario);
        pedidoRepository.save(pedido);
        return "Oferta aceita com sucesso!";
    }

    @RequestMapping("/recusar")
    public String recusarOferta (
            @RequestParam("oferta") Long oid,
            @RequestParam("pedido") Long pid) {
        Oferta oferta = ofertaRepository.findOne(oid);
        Pedido pedido = pedidoRepository.findOne(pid);
        pedido.getOfertas().remove(oferta);
        ofertaRepository.delete(oid);
        pedidoRepository.save(pedido);
        return "Oferta recusada com sucesso";
    }
}
