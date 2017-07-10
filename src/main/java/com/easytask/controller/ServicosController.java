package com.easytask.controller;

import com.easytask.model.Pedido;
import com.easytask.model.Servico;
import com.easytask.model.Usuario;
import com.easytask.model.enumeracoes.CategoriaServico;
import com.easytask.model.enumeracoes.Status;
import com.easytask.model.enumeracoes.TipoServico;
import com.easytask.repository.PedidoRepository;
import com.easytask.repository.ServicoRepository;
import com.easytask.service.implementacao.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.HashMap;

@Controller
@RequestMapping("servicos/user")
public class ServicosController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    ServicoRepository servicoRepository;

    @Autowired
    PedidoRepository pedidoRepository;

    @GetMapping
    public ModelAndView servicosUser (Authentication auth, HashMap<String, Object> map){
        map.put("tipos", TipoServico.values());
        map.put("categorias", CategoriaServico.values());
        map.put("pedidos", pedidoRepository.findAllByStatusNotAndUsuario_Username(Status.INATIVO, auth.getName()));
        return new ModelAndView("servico/servico", map);
    }

    @PostMapping("/new-order")
    public ModelAndView newOrder (Servico servico, HashMap<String, Object> map){
        map.put("tipos", TipoServico.values());
        map.put("categorias", CategoriaServico.values());
        return new ModelAndView("servico/order-finish", map);
    }

    @PostMapping("/finish-order")
    public ModelAndView newOrderFinish (Servico servico, Pedido pedido, Authentication auth) {
        Usuario usuario = usuarioService.findUserByUsername(auth.getName());
        pedido.setUsuario(usuario);
        pedido.setServico(servico);
        pedido.setNome(servico.getNome());
        pedido.setValor(pedido.getValorEstimado());
        pedido.setDataInicio(new Date());
        pedido.setStatus(Status.PENDENTE);
        servicoRepository.saveAndFlush(servico);
        pedidoRepository.save(pedido);
        return new ModelAndView("redirect:/servicos/user");
    }

}
