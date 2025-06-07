-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 07-Jun-2025 às 21:55
-- Versão do servidor: 10.4.32-MariaDB
-- versão do PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `veiculos_db`
--

DELIMITER $$
--
-- Procedimentos
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `AtualizarVeiculo` (IN `p_placa` VARCHAR(20), IN `p_nome` VARCHAR(255), IN `p_modelo` VARCHAR(255))   BEGIN
    UPDATE veiculo
    SET nome = p_nome, modelo = p_modelo
    WHERE placa = p_placa;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `contar_veiculos` (OUT `total` INT)   BEGIN
    SELECT COUNT(*) INTO total FROM veiculo;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `listar_clientes` ()   BEGIN
    SELECT * FROM cliente;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `listar_vendedores` ()   BEGIN
    SELECT * FROM vendedor;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estrutura da tabela `cliente`
--

CREATE TABLE `cliente` (
  `cpf` varchar(14) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `telefone` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Extraindo dados da tabela `cliente`
--

INSERT INTO `cliente` (`cpf`, `nome`, `telefone`) VALUES
('05595829192', 'Eric Nobre Sanches de Souza', '+55 (61) 98206-4918');

--
-- Acionadores `cliente`
--
DELIMITER $$
CREATE TRIGGER `trg_log_insert_cliente` AFTER INSERT ON `cliente` FOR EACH ROW BEGIN
    INSERT INTO log_cliente (cpf, acao) VALUES (NEW.cpf, 'INSERIR');
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estrutura da tabela `log_cliente`
--

CREATE TABLE `log_cliente` (
  `id` int(11) NOT NULL,
  `cpf` varchar(14) DEFAULT NULL,
  `acao` varchar(10) DEFAULT NULL,
  `data` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Extraindo dados da tabela `log_cliente`
--

INSERT INTO `log_cliente` (`id`, `cpf`, `acao`, `data`) VALUES
(1, '05595829192', 'INSERIR', '2025-06-07 18:14:42');

-- --------------------------------------------------------

--
-- Estrutura da tabela `log_veiculo`
--

CREATE TABLE `log_veiculo` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `placa` int(11) DEFAULT NULL,
  `nome_antigo` varchar(255) DEFAULT NULL,
  `nome_novo` varchar(255) DEFAULT NULL,
  `modelo_antigo` varchar(255) DEFAULT NULL,
  `modelo_novo` varchar(255) DEFAULT NULL,
  `alterado_em` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Extraindo dados da tabela `log_veiculo`
--

INSERT INTO `log_veiculo` (`id`, `placa`, `nome_antigo`, `nome_novo`, `modelo_antigo`, `modelo_novo`, `alterado_em`) VALUES
(1, 34567, 'Onix', 'Onix Plus', '2023 ltz plus', '2024 premier', '2025-06-04 00:18:50'),
(2, 0, 'civic', 'civiccity', 'turbo 2014', ' 2014', '2025-06-04 02:41:04'),
(3, 567, 'onix', 'onix', '2023 premier turbo', '2023 premier', '2025-06-04 02:45:34'),
(4, 0, 'Vifinha', 'Vifinha', 'cutia', 'cutia fofinha', '2025-06-05 02:31:53'),
(5, 0, 'testando', 'testando', 'teste', 'testeeeeeeeeeeeeee', '2025-06-05 15:44:59'),
(6, 0, 'chevrolet onix', 'chevrolet onix', '2023 premiar turbo', '2023 premier', '2025-06-07 11:25:00');

-- --------------------------------------------------------

--
-- Estrutura da tabela `log_vendedor`
--

CREATE TABLE `log_vendedor` (
  `id` int(11) NOT NULL,
  `matricula` varchar(10) DEFAULT NULL,
  `acao` varchar(10) DEFAULT NULL,
  `data` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Extraindo dados da tabela `log_vendedor`
--

INSERT INTO `log_vendedor` (`id`, `matricula`, `acao`, `data`) VALUES
(1, '202410402', 'INSERIR', '2025-06-07 19:11:40');

-- --------------------------------------------------------

--
-- Estrutura da tabela `veiculo`
--

CREATE TABLE `veiculo` (
  `placa` varchar(8) NOT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `modelo` varchar(255) DEFAULT NULL,
  `valor` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Extraindo dados da tabela `veiculo`
--

INSERT INTO `veiculo` (`placa`, `nome`, `modelo`, `valor`) VALUES
('exe23662', 'chevrolet onix', '2023 premier', NULL),
('t121221r', 'tracker 2022', 'premier turbo SUV', NULL);

--
-- Acionadores `veiculo`
--
DELIMITER $$
CREATE TRIGGER `trigger_alteracao_veiculo` AFTER UPDATE ON `veiculo` FOR EACH ROW BEGIN
    IF NEW.nome <> OLD.nome OR NEW.modelo <> OLD.modelo THEN
        INSERT INTO log_veiculo (
            placa, nome_antigo, nome_novo, modelo_antigo, modelo_novo
        ) VALUES (
            OLD.placa, OLD.nome, NEW.nome, OLD.modelo, NEW.modelo
        );
    END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estrutura da tabela `venda`
--

CREATE TABLE `venda` (
  `id` int(11) NOT NULL,
  `placa_veiculo` varchar(8) DEFAULT NULL,
  `cpf_cliente` varchar(14) DEFAULT NULL,
  `matricula_vendedor` varchar(10) DEFAULT NULL,
  `data_venda` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Extraindo dados da tabela `venda`
--

INSERT INTO `venda` (`id`, `placa_veiculo`, `cpf_cliente`, `matricula_vendedor`, `data_venda`) VALUES
(1, 'exe23662', '05595829192', '202410402', '2025-06-07');

-- --------------------------------------------------------

--
-- Estrutura da tabela `vendedor`
--

CREATE TABLE `vendedor` (
  `matricula` varchar(10) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `setor` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Extraindo dados da tabela `vendedor`
--

INSERT INTO `vendedor` (`matricula`, `nome`, `setor`) VALUES
('202410402', 'Carol Oliveira', 'Vendedora');

--
-- Acionadores `vendedor`
--
DELIMITER $$
CREATE TRIGGER `trg_log_insert_vendedor` AFTER INSERT ON `vendedor` FOR EACH ROW BEGIN
    INSERT INTO log_vendedor (matricula, acao) VALUES (NEW.matricula, 'INSERIR');
END
$$
DELIMITER ;

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`cpf`);

--
-- Índices para tabela `log_cliente`
--
ALTER TABLE `log_cliente`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `log_veiculo`
--
ALTER TABLE `log_veiculo`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `log_vendedor`
--
ALTER TABLE `log_vendedor`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `veiculo`
--
ALTER TABLE `veiculo`
  ADD PRIMARY KEY (`placa`);

--
-- Índices para tabela `venda`
--
ALTER TABLE `venda`
  ADD PRIMARY KEY (`id`),
  ADD KEY `cpf_cliente` (`cpf_cliente`),
  ADD KEY `matricula_vendedor` (`matricula_vendedor`),
  ADD KEY `venda_ibfk_1` (`placa_veiculo`);

--
-- Índices para tabela `vendedor`
--
ALTER TABLE `vendedor`
  ADD PRIMARY KEY (`matricula`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `log_cliente`
--
ALTER TABLE `log_cliente`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de tabela `log_veiculo`
--
ALTER TABLE `log_veiculo`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de tabela `log_vendedor`
--
ALTER TABLE `log_vendedor`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de tabela `venda`
--
ALTER TABLE `venda`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `venda`
--
ALTER TABLE `venda`
  ADD CONSTRAINT `venda_ibfk_1` FOREIGN KEY (`placa_veiculo`) REFERENCES `veiculo` (`placa`),
  ADD CONSTRAINT `venda_ibfk_2` FOREIGN KEY (`cpf_cliente`) REFERENCES `cliente` (`cpf`),
  ADD CONSTRAINT `venda_ibfk_3` FOREIGN KEY (`matricula_vendedor`) REFERENCES `vendedor` (`matricula`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
