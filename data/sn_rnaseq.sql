-- Adminer 4.7.7 MySQL dump

SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;

SET NAMES utf8mb4;

DROP TABLE IF EXISTS `sn_rnaseq`;
CREATE TABLE `sn_rnaseq` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tissue_type` varchar(45) DEFAULT NULL,
  `gene` varchar(45) NOT NULL,
  `p_val` double NOT NULL,
  `p_val_adj` double NOT NULL,
  `fold_change` double NOT NULL,
  `pct_1` double NOT NULL,
  `pct_2` double NOT NULL,
  `avg_exp` double NOT NULL,
  `cluster` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `sn_rnaseq_gene` (`gene`),
  KEY `sn_rnaseq_tissue_type` (`tissue_type`),
  KEY `sn_rnaseq_cluster` (`cluster`)
) ENGINE=InnoDB AUTO_INCREMENT=2228191 DEFAULT CHARSET=utf8mb4;


-- 2021-03-02 16:53:24
