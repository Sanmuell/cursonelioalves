package com.nelioalves.cursomc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.domain.Cidade;
import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.domain.Endereco;
import com.nelioalves.cursomc.domain.Estado;
import com.nelioalves.cursomc.domain.ItemPedido;
import com.nelioalves.cursomc.domain.Pagamento;
import com.nelioalves.cursomc.domain.PagamentoComBoleto;
import com.nelioalves.cursomc.domain.PagamentoComCartao;
import com.nelioalves.cursomc.domain.Pedido;
import com.nelioalves.cursomc.domain.Produto;
import com.nelioalves.cursomc.domain.enums.EstadoPagamento;
import com.nelioalves.cursomc.domain.enums.Perfil;
import com.nelioalves.cursomc.domain.enums.TipoCliente;
import com.nelioalves.cursomc.repositories.CategoriaRepository;
import com.nelioalves.cursomc.repositories.CidadeRepository;
import com.nelioalves.cursomc.repositories.ClienteRepository;
import com.nelioalves.cursomc.repositories.EnderecoRepository;
import com.nelioalves.cursomc.repositories.EstadoRepository;
import com.nelioalves.cursomc.repositories.ItemPedidoRepository;
import com.nelioalves.cursomc.repositories.PagamentoRepository;
import com.nelioalves.cursomc.repositories.PedidoRepository;
import com.nelioalves.cursomc.repositories.ProdutoRepository;

@Service
public class DbService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;


	public void instantiateTestDatabase () throws ParseException {

		
			Categoria cat1 = new Categoria(null, "INformatica");
			Categoria cat2 = new Categoria(null, "Escritorio");
			Categoria cat3 = new Categoria(null, "Cama mesa e banho");
			Categoria cat4 = new Categoria(null, "Eletrônios");
			Categoria cat5 = new Categoria(null, "Jardinagem");
			Categoria cat6 = new Categoria(null, "Decoracao");
			Categoria cat7 = new Categoria(null, "Perfumaria");
			

			Produto p1 = new Produto(null, "Computador", 2000.00);
			Produto p2 = new Produto(null, "Impressora", 800.00);
			Produto p3 = new Produto(null, "Mousa", 80.00);

			cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
			cat2.getProdutos().addAll(Arrays.asList(p2));

			p1.getCategorias().addAll(Arrays.asList(cat1));
			p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
			p3.getCategorias().addAll(Arrays.asList(cat1));

			categoriaRepository.saveAll(Arrays.asList(cat1, cat2,cat3,cat4,cat5,cat6,cat7));
			produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

			Estado est1 = new Estado(null, "Minas Gerais");
			Estado est2 = new Estado(null, "São Paulo");

			Cidade c1 = new Cidade(null, "Uberlandia", est1);
			Cidade c2 = new Cidade(null, "São Paulo", est2);
			Cidade c3 = new Cidade(null, "Campinas", est2);

			est1.getCidades().addAll(Arrays.asList(c1));
			est2.getCidades().addAll(Arrays.asList(c3));

			estadoRepository.saveAll(Arrays.asList(est1, est2));
			cidadeRepository.saveAll(Arrays.asList(c1, c2));

			Cliente cli1 = new Cliente(null, "Maria silva", "maria@gmail.com", "4534534534", TipoCliente.PESSOAFISICA, pe.encode("123"));

			Cliente cli2 = new Cliente(null, "Ana Costa", "sanmuell.i@gmail.com", "44834259005", TipoCliente.PESSOAFISICA, pe.encode("123"));
			cli2.addPerfil(Perfil.ADMIN);
			cli2.getTelefones().addAll(Arrays.asList("656756756","8567567"));

			
			cli1.getTelefones().addAll(Arrays.asList("435345345", "5234243"));

			Endereco e1 = new Endereco(null, "Rua flores", "300", "Apto 303", "Jardim", "34534", cli1, c1);
			Endereco e2 = new Endereco(null, "Rua Maria", "500", "Apto 103", "Ada", "44454", cli1, c2);
			Endereco e3 = new Endereco(null, "Avenida Floriano", "2099", null, "centro", "657567", cli2, c2);

			cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
			cli2.getEnderecos().addAll(Arrays.asList(e3));

			clienteRepository.saveAll(Arrays.asList(cli1, cli2));
			enderecoRepository.saveAll(Arrays.asList(e1, e2, e3));

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

			Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:37"), cli1, e1);
			Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);

			Pagamento pgto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
			ped1.setPagamento(pgto1);

			Pagamento pgto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"),
					null);
			ped2.setPagamento(pgto2);

			cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
			
			pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
			pagamentoRepository.saveAll(Arrays.asList(pgto1, pgto2));
			
			ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
			ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
			ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
			
			ped1.getItens().addAll(Arrays.asList(ip1,ip2));
			ped2.getItens().addAll(Arrays.asList(ip3));
			
			p1.getItens().addAll(Arrays.asList(ip1));
			p2.getItens().addAll(Arrays.asList(ip3));
			p3.getItens().addAll(Arrays.asList(ip2));

			itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2,ip3));

	}
	
}
