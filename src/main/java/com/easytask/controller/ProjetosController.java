package com.easytask.controller;

import com.easytask.model.Oferta;
import com.easytask.model.Pedido;
import com.easytask.model.enumeracoes.CategoriaServico;
import com.easytask.model.enumeracoes.Status;
import com.easytask.repository.OfertaRepository;
import com.easytask.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.HashMap;

@Controller
@RequestMapping("/projetos")
public class ProjetosController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private OfertaRepository ofertaRepository;

    @GetMapping("/show")
    public ModelAndView showProjetos(Authentication auth, HashMap<String, Object> map) {
        map.put("pedidos", pedidoRepository.findAllByStatusAndUsuario_UsernameNotOrderByDataInicio(Status.PENDENTE, auth.getName()));
        map.put("categorias", CategoriaServico.values());

        return new ModelAndView("pedidos/show-projetos", map);
    }

    @PostMapping("/{pedido_id}/oferta/{oferta_id}/aceitar")
    public ModelAndView aceitar(@PathVariable("pedido_id") Long pid, @PathVariable("oferta_id") Long oid) {
        Pedido pedido = pedidoRepository.findOne(pid);
        Oferta oferta = ofertaRepository.findOne(oid);

        pedido.setValor(oferta.getValor());
        pedido.setDataInicio(new Date());
        pedido.setProfissional(oferta.getUsuario());
        pedido.setPrevisaoFim();
        pedido.setStatus(Status.ATIVO);
        pedido.getOfertas().clear();

        for (Oferta of : pedido.getOfertas())
            ofertaRepository.delete(of);
        pedidoRepository.saveAndFlush(pedido);

        return new ModelAndView("redirect:/servicos/user#minhas_ordens");
    }

    @PostMapping("/{pedido_id}/oferta/{oferta_id}/recusar/")
    public ModelAndView recusar(@PathVariable("pedido_id") Long pid, @PathVariable("oferta_id") Long oid) {
        Pedido pedido = pedidoRepository.findOne(pid);
        Oferta oferta = ofertaRepository.findOne(oid);

        pedido.remOferta(oferta);
        pedidoRepository.saveAndFlush(pedido);
        ofertaRepository.delete(oferta);

        return new ModelAndView("redirect:/pedidos/user/" + pid);
    }
}
